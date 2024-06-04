<template>
    <div class="restaurant-cards">
        <div class="restaurant-card" v-for="restaurant in $store.state.restaurants" v-bind:key="restaurant.id">
            <h2 class="restaurant-name">{{ restaurant.name }}</h2>
            <p class="restaurant-category">{{ restaurant.category }}</p>
            <p class="restaurant-price">{{ restaurant.price }}</p>
            <!-- <router-link v-bind:to="{ name: 'details', params: { id: restaurant.id } }">View Details</router-link> -->
            <RestaurantDetails v-bind:curRestaurant="restaurant"/>
        </div>
    </div>
</template>

<script>
import RestaurantDetails from './RestaurantDetails.vue';

export default {
    components: { 
        RestaurantDetails 
    }
}
</script>

<style scoped>

    .restaurant-cards {
        display: flex;
    }
    .restaurant-card {
        border: 2px solid black;
        border-radius: 10px;
        width: 250px;
        height: 400px;
        margin: 20px;
    }
</style>