<template>
    <form v-on:submit.prevent="getEventInformation">
    <input type="text" v-model="tempEventId">
    <button type="submit">Submit</button>
    </form>
  <div>
    Temp Event ID: {{ eventId }} <br>
    Event Name: {{ eventName }} <br>
    Event Location: {{ eventLocation }} <br>
    Event Decision Date: {{  decisionDate }}
  </div>
</template>

<script>
import EventService from '@/services/EventService';

export default {
    props: { eventId: String 
    },
    data() {
        return {
            event: {}
            // tempEventId: '',
            // eventName: '',
            // eventLocation: '',
            // decisionDate: '',
            // eventLink: ''
        };
    },
    mounted() {
        this.fetchEvent();
    },
    methods: {
        fetchEvent() {
            EventService.getEvent(this.eventId).then(response => {
                this.event = response.data;
            });
        }
    //     getEventInformation(){
    //         EventService.getEvent(this.tempEventId).then(
    //             (response) => {
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
    //             (error) => {
    //                 // this.handleErrorResponse(error, "Error creating an event.");
    //                 console.log("Error getting the event.")
    //             }
    //         );
    //     }
    // },
    // created() {
    //     this.getEventInformation();
}
};
</script>

<style>

</style>