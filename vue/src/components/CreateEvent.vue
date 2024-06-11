<template>
  <div>
    <h3>Create your NominEat event</h3>
    <form v-on:submit.prevent="createEvent">
        <label for="eventName">Event Name: </label>
        <input type="text" name="eventName" id="eventName" v-model="event.eventName">
        <label for="decisionDate">Decision Date: </label>
        <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="event.date">
        <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import RestaurantService from '../services/RestaurantService';
import EventService from '../services/EventService';

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
            // let eventId = 0;

            
            RestaurantService.create(newEvent).then(
                (response) => {
                    if (response.status === )
                }
            );
            let createdEventId = createdEvent.eventId;

            EventService.addRestaurantsToEvent(this.restaurants, createdEventId);
            // RestaurantService.createRestaurants(this.restaurants);

            // Send the list of restaurants to teh API with the event id...
        }
    }
}
</script>

<style>

</style>