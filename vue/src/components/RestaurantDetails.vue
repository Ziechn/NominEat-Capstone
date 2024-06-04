<template>
    <div class="restaurant-details">
        <h2 class="restaurant-name">{{ curRestaurant.name }}</h2>
            <p class="restaurant-category">{{ curRestaurant.category }}</p>
            <p class="restaurant-price">{{ curRestaurant.price }}</p>
            <p class="restaurant-address1">{{ curRestaurant.address1 }}</p>
            <p class="restaurant-city">{{ curRestaurant.city }}</p>
            <p class="restaurant-state">{{ curRestaurant.state }}</p>
            <p class="restaurant-zipcode">{{ curRestaurant.zipcode }}</p>
    </div>
</template>

<script>
    export default {
        props: {
            curRestaurant: Object
        }
    }
</script>