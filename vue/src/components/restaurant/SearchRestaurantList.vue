<!-- reusing card component to display search results -->

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
import RestaurantService from '@/services/RestaurantService';
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';
import EventService from '@/services/EventService';

export default {
    components: {
        RestaurantCard
    },
    data() {
        return {
        zipcode: '',
        limit: 10,
        term: 10,
        restaurants: []
    };
    },
    methods: {
        searchRestaurants() {
        RestaurantService.searchRestaurants(this.zipcode, this.limit, this.term).then(response => {
            this.restaurants = response.data;
        }

        );
    },
    saveRestaurants() {
        const eventId = this.$route.params.eventId;
        EventService.addRestaurantsToEvent(eventId, this.restaurants.slice(0, 10)).then(() => {
            alert('Restaurants saved successfully');
        });
    }}
};
</script>

