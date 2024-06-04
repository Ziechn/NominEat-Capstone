<template>
    <div class="restaurant-details">
        <h2 class="restaurant-name">{{ curRestaurant.name }}</h2>
            <p class="restaurant-category">{{ curRestaurant.category }}</p>
            <p class="restaurant-price">{{ curRestaurant.price }}</p>
            <p class="restaurant-address">{{ curRestaurant.address }}</p>
            <p class="restaurant-hours">{{ curRestaurant.hours }}</p>
            <p class="restaurant-status">{{ curRestaurant.status }}</p>
    </div>
</template>

<script>
    export default {
        props: {
            curRestaurant: Object
        }
    }
</script>