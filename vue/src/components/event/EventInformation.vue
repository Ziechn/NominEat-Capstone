<template>
  <div class="event-information">
    <p>  Temp Event ID: {{ event.eventId }} <br> </p>
    <p>  Event Name: {{ event.eventName }} <br> </p>
    <p>Event Location: {{ event.location }} <br></p>
    <p> Event Decision Date: {{  event.decisionDate }}</p>
    <p> Event Link: http://localhost:9000/{{ event.eventLink }} </p>
  </div>
</template>

<script>
import EventService from '@/services/EventService';

export default {
    // props: { eventId: String 
    // },
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
                console.log(response);
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



    //     getEventInformation(){
    //         EventService.getEvent(this.tempEventId).then(
    //             (response) ={
    //                 if (response.status === 200) {
    //                     this.eventName = response.data.eventName;
    //                     this.eventLocation = response.data.location;
    //                     this.decisionDate = response.data.decisionDate;
    //                     this.eventLink = response.data.eventLink; // Added after testing.
    //                 } else {
    //                     console.log("How did we get here?");
    //                 }
    //             }
    //         ).catch(
    //             (error) ={
    //                 // this.handleErrorResponse(error, "Error creating an event.");
    //                 console.log("Error getting the event.")
    //             }
    //         );
    //     }
    // 
    // created() 
    //     this.getEventInformation();
