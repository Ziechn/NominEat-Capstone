
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