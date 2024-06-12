<!-- displays restaurants for event organizers pulling data from the database -->

<template>
    <div>
        <RestaurantCardForOrganzier v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" :eventId="eventId"/>
    </div>
</template>

<script>
import EventService from '@/services/EventService';
import RestaurantCardForOrganzier from '../restaurant/RestaurantCardForOrganzier.vue';



export default {
    components: {
    RestaurantCardForOrganzier
},

    props: ['eventId', 'restaurant'],

    data() {
      return {
        restaurants: []
      };
    },
    mounted() {
        this.fetchOrganizerRestaurants();
    },
    methods: {
        fetchOrganizerRestaurants() {

            EventService.getOrganizerRestaurants(this.eventId).then(response => {
                this.restaurants = response.data;
            });
        }
    }

}


</script>