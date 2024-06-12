<template>
    <div class="event-information">
        <div class="info-block">
            <h3 class="info-data">{{ event.eventName }} </h3>
        </div>
        <div class="info-block">
            <div class="info-label">Event Location</div>
            <div class="info-data">{{ event.location }}</div>
        </div>
        <div class="info-block">
            <div class="info-label">Decision Date</div>
            <div class="info-data">{{ event.decisionDate }}</div>
        </div>
        <div class="info-block">
            <div class="info-label">Voting Link</div>
            <div class="info-data">http://localhost:9000/{{ event.eventLink }} </div>
        </div>
    </div>
</template>

<script>
import EventService from '@/services/EventService';

export default {
    data() {
        return {
            event: {
                eventId: 1,
                eventName: '',
                location: '',
                decisionDate: '',
                eventLink: ''
            }
        };
    },
    methods: {
        getEventInfo() {
            EventService.getEventByUserId().then(response => {
                this.event = response.data;
            }).catch(error => {
                console.error('Error fetching event data:', error)
            })
        }
    },
    created() {
        this.getEventInfo();
    }
};
</script>

<style scoped>
.event-information {
    display: flex;
    justify-content: center;
    flex-direction: column;
    
}
.info-data {
    color: #5c5c5c;
}
.info-label {
    padding-bottom: 10px;
    font-size: 10px;
    font-weight: bold;
}
.info-block {
    /* padding-left: 30px; */
    margin-top: 10px;
    margin-bottom: 10px;
}



</style>