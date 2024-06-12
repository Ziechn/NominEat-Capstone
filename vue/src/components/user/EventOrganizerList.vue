<template>
  <div>
    Event Organizer List
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.restaurantId" v-bind:restaurantId="restaurant.restaurantId">
        <EventOrganizerRestaurantStats />
    </div>
  </div>
</template>

<script>
import EventService from '../services/EventService';
import EventOrganizerRestaurantStats from './EventOrganizerRestaurantStats.vue';

export default {
    data() {
        return {
            restaurants: []
        }
    },
    props: ['eventId'],
    methods: {
        getRestaurantsByEventId(){
            EventService.getRestaurantsForEvent(this.eventId).then(
                (response) => {
                    if (response.status === 200){
                        this.restaurants = response.data;
                    }
                }
            ).catch(
                (error) => {
                    console.log("Problem getting restaurants for event id: " + this.eventId);
                }
            );
        }
    },
    created() {
        this.getRestaurantsByEventId();
    },
    components: {
        EventOrganizerRestaurantStats
    }
}
</script>

<style>

</style>