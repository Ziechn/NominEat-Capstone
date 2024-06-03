<template>
    <div class="restaurant-details">
        <h2 class="restaurant-name">{{ restaurant.name }}</h2>
        <p class="restaurant-category">{{ restaurant.category }}</p>
        <p class="restaurant-address">{{ restaurant.address }}</p>
        <p class="restaurant-hours">{{ restaurant.hours }}</p>
        <p class="restaurant-status">{{ restaurant.status }}</p>
    </div>
</template>

<script>
    export default {
        props: ['restaurant']
    }
</script>