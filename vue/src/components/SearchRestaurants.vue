<template>
        <div class="search-restaurants">
            <h2>Search</h2>
            <input type="text" v-model="searchText" placeholder="Enter zip" />

            <button @click="searchRestaurants" class="search">Search</button>

                <div v-if="loading" class="loading">Loading...please wait...</div>
            <!-- loading and error stuff here -->
            <div v-if="restaurants.length" class="restaurant-cards">
                <RestaurantCard v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" />
            </div>
        </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue';
//vuex mapstate stuff


export default{
    components: {
        RestaurantCard,
    },
    data(){
        return {
            text: '',
            loading: false,
        };
    },methods: {
        async searchRestaurants() {
            this.loading = true;
            try {
                this.$store.dispatch('retrieveRestaurants', this.searchText);
            } catch (error) {
                this.error = 'Failed to load restaurants';
            }
        }
    }
    }



</script>

<style>
</style>