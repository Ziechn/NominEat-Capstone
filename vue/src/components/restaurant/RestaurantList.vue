<!-- search and display a list of restaurants using R...Card

<template>
<div class="restaurant-list">
    <input v-model="zipcode" placeholder="Enter Zipcode" />
    <input v-model="limit" type="number" placeholder="limit" />
    <input v-model="term" placeholder="limit" />
    <button @click="searchRestaurants">Search</button>


    <div v-if="restaurants.length">
        <div class="cards-container">
            <RestaurantCard
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             />
    </div>
    </div>
</div>
</template>

<script>
import RestaurantCard from './RestaurantCard.vue';
import RestaurantService from '../../services/RestaurantService';

export default {
    components: {
        RestaurantCard
    },
        data() {
                return {
                    zipcode: '',
                    limit: 10,
                    term: '',
                    restaurants: []
              };
    },
    methods: {
        searchRestaurants() {
            RestaurantService.searchRestaurants(this.zipcode, this.limit, this.term).then(response => {
                    this.restaurants = response.data;
                }
                );
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
</style> -->