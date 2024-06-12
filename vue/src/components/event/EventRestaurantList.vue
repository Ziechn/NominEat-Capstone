<template> 
 <div class="event-restaurant-list">
    <!-- <RestaurantList
             v-for="restaurants in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             /> -->
            
 </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import RestaurantList from './RestaurantList.vue';



export default {
    components: {
    RestaurantList
},

    props: {
        eventId: this.Number
    },
    computed : {
        ...mapState(['events']), 
        restaurants() {
            const event = this.events.find(event => event.id === this.eventId);
            return event ? event.restaurants : [];
        }

        },
    methods: {
        ...mapActions(['fetchRestaurants']), 
},
    mounted() {
        this.fetchEventRestaurants({ this.eventId });
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