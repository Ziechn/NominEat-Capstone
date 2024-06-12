<!-- pulls and displays r data for a specific event from database used for voting  -->


<template> 
 <div class="event-restaurant-list">
    <RestaurantCardForVoting
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             :eventId="eventId"
             />
            
 </div>
</template>

<script>
import EventService from '@/services/EventService';
import RestaurantCardForVoting from '@/components/restaurant/RestaurantCardForVoting.vue';


export default {
    components: {
    RestaurantCardForVoting
},

props: 
        ['eventId'],
        
    data() {
        return {
            restaurants: []
        };
    },
    mounted() {
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