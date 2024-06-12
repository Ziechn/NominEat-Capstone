<!-- displays restaurants for event organizers pulling data from the database -->

<template>
    <div>
        <VotingStats v-for="restaurant in restaurants" v-bind:key="restaurant.id" v-bind:restaurantId="restaurant.id" v-bind:eventId="eventId" />
    </div>
</template>

<script>
import EventService from '@/services/EventService';
import VotingStats from '../restaurant/VotingStats.vue';

export default {
    components: {
        VotingStats
    },
    data() {
      return {
        eventId: -420,
        restaurants: []
      };
    },
    created() {
        this.getEventId();
    },
    methods: {
        getEventId(){
            EventService.getEventByUserId().then(response => {
                this.eventId = response.data.eventId;
                this.fetchOrganizerRestaurants();
            }).catch(error => {
                console.error('Error fetching event data:', error)
            })
        },
        fetchOrganizerRestaurants() {
            EventService.getOrganizerRestaurants(this.eventId).then(
                (response) => {
                    console.log(response.data);
                    this.restaurants = response.data;
            }).catch(
                (error) => {
                    console.log("Problem getting restaurants for event ID: " + this.eventId + " Error: " + error);
                }
            );
        }
    }
}
</script>