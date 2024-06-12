<!-- displays restaurants for event organizers pulling data from the database -->

<template>
    <div>
        <RestaurantCardForOrganizer v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" :eventId="eventId" />
    </div>
</template>

<script>
import EventService from '@/services/EventService';
import RestaurantCardForOrganizer from '@/components/restaurant/RestaurantCardForOrganizer.vue';


export default {
    components: {
       RestaurantCardForOrganizer
    },
    props: 
        ['eventId'],
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