<!-- reusing card component to display search results -->

<template> 
<div class="search-restaurant-list">
    <RestaurantCard v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" />
</div>
</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';

export default {
    components: {
        RestaurantCard
    },
    props: {
        eventId: String
    },
    data() {
        return 
        restaurants: []
    };
    

    }
};
</script>

