<template>
    <div class="restaurant-card" v-for="restaurant in $store.state.restaurants"
    v-bind:key="restaurant.id">
        <h1>test title</h1>
        <h2 class="restaurant-name">{{ restaurant.name }}</h2>
        <p class="restaurant-category">{{ restaurant.category }}</p>
        <div class="button-container">
            <!-- button to display book details -->
        </div>
    </div>
</template>

<script>
export default {
    name: 'RestaurantCard',
    props: {
        restaurant: Object
    }
}
</script>