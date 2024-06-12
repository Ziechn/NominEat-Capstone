<!-- reusing card component to display search results -->

<template> 
<div class="search-restaurant-list">
    <RestaurantCard :key="restaurantId" :restaurant="restaurant" />
</div>
</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';

export default {
    components: {
        RestaurantCard
    },
    props: {
        restaurants: []
    }
};
</script>

