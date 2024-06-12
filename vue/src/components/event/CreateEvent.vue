<template>
  <div>
    <!-- <h3>Create your NominEat event</h3>
    <eventCreationPopup @eventCreated="handleEventCreated" /> -->
    <form v-on:submit.prevent="createEvent">    
        <label for="eventName">Event Name: </label>
        <input type="text" name="eventName" id="eventName" v-model="event.eventName">
        <label for="decisionDate">Decision Date: </label>
        <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="event.date">
        <button type="submit">Submit</button>
    </form>
  </div>
</template>
                    <!-- create event should pull in info from the database and save it - main view for creating events, pulling initial r data from API and saving to database... -->
<script>
import RestaurantService from '@/services/RestaurantService';
import EventService from '@/services/EventService';

export default {
    data() {
        return {
            event: {
                eventName: '',
                date: ''
            }
        }
    },
    props: ['location', 'restaurants'],
    methods: {
        createEvent() {
            // Create a new event...
            let newEvent = {
                eventName: this.event.eventName,
                location: this.location,
                decisionDate: this.event.date
            }

            console.log(newEvent);
            console.log(this.restaurants);

            // Send the event to the API
            let eventId = 0;
            
            RestaurantService.create(newEvent).then(
                (response) => {
                    if (response.status === 201) {
                        eventId = response.data.eventId;
                    }

                EventService.addRestaurantsToEvent(eventId, this.restaurants).then(
                    (response) => {
                        if (response.status === 201) {
                            console.log("Restaurants added to the event.");
                            console.log(response.data);
                        }
                    }
                ).catch(
                    (error) => {
                        // this.handleErrorResponse(error, "Error associating restaurants with the event.");
                        console.log("Error associating restaurants with the event.")
                    }
                );
            }).catch(
                (error) => {
                    this.handleErrorResponse(error, "Error creating an event.");
                    console.log("Error creating an event.")
                }
            );
        }
    }
}
</script>

<style>

</style>