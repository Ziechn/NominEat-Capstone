displays restaurants for event organizers pulling data from the database

<template>
    <div>
        <h2>Restaurants for organizer- here for testing</h2> 
        <RestaurantCardForOrganizer v-for="restaurant in restaurants" v-bind:key="restaurant.id" v-bind:restaurantId="restaurant.id" v-bind:eventId="eventId" />
    </div>
</template>

<script>
import EventService from '@/services/EventService';
//import { mapActions, mapState } from 'vuex';
import RestaurantCardForOrganizer from '../restaurant/RestaurantCardForOrganizer.vue';

export default {
    components: {
        RestaurantCardForOrganizer
    },
    props: ['eventId'],
    // computed: {
    //     ...mapState(['restaurants'])
    // },
    // mounted() {
    //     this.fetchOrganizerRestaurants();
    // },
    data() {
      return {
        //eventId: -420,
        restaurants: []
      };
    },
    created() {
        this.getEventId();
    },
    methods: {
        // ...mapActions(['fetchEventRestaurants']),
        // fetchOrganizerRestaurants() {
        //     this.fetchEventRestaurants(this.eventId);
        // }
        getEventId(){
            EventService.getEventByUserId().then(response => {
                //this.eventId = response.data.eventId;
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
};
</script>