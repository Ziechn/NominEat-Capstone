<template>
    <div class="selected-restaurants-list">
        <h2>Your Restaurants</h2>

        <div v-if="!loading && restaurants.length" class="restaurant-cards">
        <RestaurantCard v-for="restaurant in restaurants" v-bind:key="restaurant.id" v-bind:restaurant="restaurant"/>
</div>

        <div v-if="loading" class="loading">Loading...Please Wait...</div>
        <div v-if="!loading && !restaurants.length">
            No restaurants selected yet! Go back to add your selections
        </div>
    </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex';
import RestaurantCard from '@/components/RestaurantCard.vue';


export default{
    components: {
        RestaurantCard,
    },
    computed: {
        ...mapState(['loading']),
    }
};

</script>