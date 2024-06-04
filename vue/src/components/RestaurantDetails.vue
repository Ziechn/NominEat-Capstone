<template>
    <div class="restaurant-details">
            <h2 class="restaurant-name">{{ restaurant.name }}</h2>

            <p class="restaurant-category">{{ restaurant.category }}</p>
            <p class="restaurant-categories">{{ restaurant.categories }}</p>v
            <p class="restaurant-price">{{ restaurant.price }}</p>
            <p class="restaurant-address1">{{ restaurant.address1 }}</p>
            <p class="restaurant-city">{{ restaurant.city }}</p>
            <p class="restaurant-state">{{ restaurant.state }}</p>
            <p class="restaurant-zipcode">{{ restaurant.zipcode }}</p>
    </div>
</template>

<script>
    export default {
        props: {
            restaurant: Object
        }
    }
</script>