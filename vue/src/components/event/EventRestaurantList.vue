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
//import EventService from '@/services/EventService';
import RestaurantCardForVoting from '../restaurant/RestaurantCardForVoting.vue';
import { mapActions, mapGetters } from 'vuex';

export default {
    components: {
        RestaurantCardForVoting
    },
props: 
        ['eventId'],

computed: {
    ...mapGetters(['getRestaurants']),
    restaurants() {
    return this.getRestaurants;
    }
},
    mounted() {
        this.fetchRestaurants();
    },
        
    // data() {
    //     return {
    //         restaurants: []
    //     };
    // },
    // created() {
    //     this.fetchRestaurants();
    // },
    methods: {
        ...mapActions(['fetchEventRestaurants']),
        fetchRestaurants() {
            this.fetchEventRestaurants(this.eventId);
            }
        }
    


};
</script>

<style scoped>
<<<<<<< HEAD

=======
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    border-width: 3px;
}
>>>>>>> 1efa0e0e474c0677efa9bdb64f8ab2c21fbbc92e
</style>