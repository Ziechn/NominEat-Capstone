<!-- pulls and displays r data for a specific event from database used for voting  -->


<template> 
 <div class="event-restaurant-list">
    <h2> Restaurants for Voting </h2>
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
import RestaurantCardForVoting from '@/components/restaurant/RestaurantCardForVoting.vue'
//import { mapActions, mapGetters } from 'vuex';

export default {
    components: {
        RestaurantCardForVoting
    },

// props: 
//         ['eventId'],

            data() {
        return {
            eventId: 1,
            restaurants: []
        };

    },
    // mounted() {
    //     this.fetchEventRestaurants();
    // },

// computed: {
//     ...mapGetters(['getRestaurants']),
//     restaurants() {
//     return this.getRestaurants;
//     }
// },
    
        

    // },
    created() {
        this.fetchEventRestaurants();
    },
    methods: {
       fetchEventRestaurants() { 
            EventService.getRestaurantsForEvent(this.eventId).then(response => {
                this.restaurants = response.data
            }).catch (error => {
                console.error('error getting restaurants: ', error);
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
    border-width: 3px;
}
</style>