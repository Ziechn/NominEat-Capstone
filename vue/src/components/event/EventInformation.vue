<template>
  <div class="event-information">
    <p> Temp Event ID: {{ event.eventId }} <br> </p>
    <p> Event Name: {{ event.eventName }} <br> </p>
    <p> Event Location: {{ event.location }} <br></p>
    <p> Event Decision Date: {{  event.decisionDate }}</p>
    <p> Event Link: http://localhost:9000/{{ event.eventLink }} </p>
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
    created(){
        this.getEventInfo();
    }
};
</script>