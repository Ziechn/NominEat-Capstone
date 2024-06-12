<template> 
 <div class="event-restaurant-list">
    <RestaurantList
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             :eventId="eventId"
             />
            
 </div>
</template>

<script>
import EventService from '@/services/EventService';

export default {
    components: {
},

    props: {
        eventId: String
    },
    data() {
        return {
            restaurants: []
        };
    },
    created() {
        this.fetchRestaurants();
    },
    methods: {
        fetchRestaurants() {
            EventService.getRestaurantsForEvent(this.eventId).then(response => {
                this.restaurants = response.data;
            });
        }
    }
};
</script>

<style scoped>
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}
</style>