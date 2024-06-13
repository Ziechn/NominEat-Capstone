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
import RestaurantCardForVoting from '@/components/restaurant/RestaurantCardForVoting.vue';
import { mapActions, mapState } from 'vuex';

export default {
    components: {
        RestaurantCardForVoting
    },
props: 
        ['eventId'],

computed: {
    ...mapState(['restaurants'])
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
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    border-width: 3px;
}
</style>