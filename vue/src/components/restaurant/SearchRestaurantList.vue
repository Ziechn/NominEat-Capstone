<!-- reusing card component to display search results -->
<!-- fetches restaurant data from API displays it and allows saving of ten to database -->

<template> 
<div class="search-restaurant-list">
        <input v-model="zipcode" placeholder="Enter Zipcode" />
        <input v-model="limit" type="number" placeholder="Limit" />
        <input v-model="term" placeholder="Term" />
        <button @click="searchRestaurants">Search</button>

        <div v-if="restaurants.length">
    <RestaurantCard v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" />
    <button @click="saveRestaurants">Save Restaurants</button>
    </div>
</div>
</template>

<script>
//import RestaurantService from '@/services/RestaurantService';
//import EventService from '@/services/EventService';
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';
import { mapActions, mapGetters } from 'vuex';


export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
        zipcode: '',
        limit: 10,
        term: ''
       // restaurants: []
    };
    },
    computed: {
        ...mapGetters(['getRestaurants']),
        restaurants() {
            return this.getRestaurants;
        }
    },
    methods: {
        ...mapActions(['searchRestaurants', 'saveRestaurantsToEvent']),
        searchRestaurants() {
            this.searchRestaurants({ 
            zipcode: this.zipcode, 
            limit: this.limit, 
            term: this.term
        });
    },
    saveRestaurants() {
        const eventId = this.$route.params.eventId;
      this.fetchRestaurants({ eventId, restaurants: this.restaurant.slice(0, 10) }).then(() => {
            alert('Restaurants saved successfully');
        });
    }}
};
</script>

