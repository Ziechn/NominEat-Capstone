<template>
    <div class="event-information">
        <div class="info-block">
            <h3 class="event-name">{{ event.eventName }} </h3>
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
            <div class="info-data">http://localhost:9000/link/{{ event.eventLink }} </div>
        </div>
        <div class="info-block">
            <div class="info-label">Restaurant List</div>
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
    padding-bottom: 0px;
    font-size: 18px;
}
.info-label {
    padding-bottom: 10px;
    font-size: 12px;
    font-weight: bold;
}
.info-block {
    /* padding-left: 30px; */
    margin-top: 10px;
    margin-bottom: 10px;
}
.event-name {
    padding-bottom: 0;
    padding-top: 50px;
}




</style>