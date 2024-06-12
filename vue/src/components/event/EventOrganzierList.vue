<!-- displays restaurants for event organizers pulling data from the database -->

<template>
    <div>
        <RestaurantCard v-for="restaurant in restaurants" :key="restaurant.id" :eventId="eventId" />
    </div>
</template>

<script>
import EventService from '@/services/EventService';
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';

export default {
    components: {
        RestaurantCard
    },
    props:  {
        eventId: String
    },
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

            EventService.fetchOrganizerRestaurants(this.eventId).then(response => {
                this.restaurants = response.data;
            });
        }
    }

}


</script>