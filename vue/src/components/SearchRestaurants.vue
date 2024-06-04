<template>
    <div class="search-restaurants">
        <h2>Search</h2>
        <form class="search-form" @submit.prevent="searchByZipCode">
            <input placeholder="Enter ZIP Code" type="text" v-model="zipCode"/>
            <input placeholder="Enter # to Limit Results" type="number" v-model="limit"/>
            <button type="submit">Search</button>
        </form>

        <div v-if="loading" class="loading">Loading...please wait...</div>
        
        <div v-if="!loading && restaurants.length" class="restaurant-cards">
            <RestaurantCard v-for="restaurant in restaurants" v-bind:key="restaurant.id" v-bind:restaurant="restaurant"/>
        </div>

        <div v-if="!loading && !restaurants.length">
            No results found...
        </div>
    </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue';
import RestaurantService from '../services/RestaurantService';


export default{
    components: {
        RestaurantCard,
    },
    data(){
        return {
            zipCode: '',
            limit: 10,
            restaurants: [],
            loading: false
        };
    },
    created() {
        RestaurantService.list(this.zipCode).then(response => {
            this.restaurants = response.data;
        })
    },
    methods: {
        async searchByZipCode() {
            this.loading = true;
            try {
                const response = await RestaurantService.list(this.zipCode, this.limit);
                this.restaurants = response.data;
                this.loading = false;
            } catch (error) {
                console.error('Error fetching restaurants:', error);
                this.loading = false;
            } 
        }
    }
}
</script>

<style>
</style>