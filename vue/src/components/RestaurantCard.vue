<<<<<<< HEAD

<!-- Goal is to split the card into two divs - one for each side -Lines -->
<template> 
<div class="restaurant-card-wrapper" :class="wrapperClasses" @click="enlargeCard"></div>
    <div class="restaurant-card" :class="cardClasses" @click.stop="flipCard">

        <div class="card-front">
            <img :src="restaurant.imageUrl" :alt="restaurant.name" /> 
            <h2>{{ restaurant.name }}</h2>
            <p v-if="restaurant.categories && restaurant.categories.length">{{ restaurant.categories[0].title }}
        </p>
            <p>{{ restaurant.rating }}</p>    
=======
<template>
    <input placeholder="Enter ZIP Code" type="text" v-model="zipCode"/>
    <div class="restaurant-cards">
        <div class="restaurant-card" v-for="restaurant in $store.state.restaurants" v-bind:key="restaurant.id">
            <h2 class="restaurant-name">{{ restaurant.name }}</h2>
            <p class="restaurant-category">{{ restaurant.category }}</p>
            <p class="restaurant-price">{{ restaurant.price }}</p>
            <!-- <router-link v-bind:to="{ name: 'details', params: { id: restaurant.id } }">View Details</router-link> -->
            <RestaurantDetails v-bind:curRestaurant="restaurant"/>
>>>>>>> 45e8e47c452727c93b2de1c2d517341f24dba026
        </div>

            <div class="card-back"></div>
            <p>{{ restaurant.name }}</p>
            <p>{{ restaurant.price }}</p>
            <!-- <p>{{ restaurant.location }}</p> -->
          
            <!-- open: restaurant.isOpenNow, closed: !restaurant.isOpenNow -->
            <RestaurantDetails v-bind:curRestaurant="restaurant"/>
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