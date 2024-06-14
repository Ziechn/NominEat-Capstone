<!-- pulls and displays r data for a specific event from database used for voting  -->


<template> 
 <div class="event-restaurant-list">
    <h2> Restaurants for Voting </h2>
    <div class="cards-container">
    <RestaurantCardForVoting
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             :eventId="eventId"
             />
            </div>      
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
            restaurants: [],
            eventLink: '28e2e4b3-2bca-40bc-a876-6eeff60a52b9'
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
        console.log(this.$route.params.eventLink);
        this.fetchEventLink();
    },
    methods: {

       fetchEventRestaurants(frog) { 
            EventService.getRestaurantsForEvent(frog).then(response => {

                this.restaurants = response.data
            }).catch (error => {
                console.error('error getting restaurants: ', error);
          });   
        },

        fetchEventLink() {
            EventService.getEventByLink(this.$route.params.eventLink).then(response => {
                this.eventId = response.data.eventId
                this.fetchEventRestaurants(this.eventId)
            }).catch (error => {
                console.error('error getting link: ', error);
            });
        }
    }


};
</script>

<style scoped>
.event-restaurant-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
    padding: 20px;
    justify-items: center;

}
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 0px;
    border-width: 3px;
}
</style>