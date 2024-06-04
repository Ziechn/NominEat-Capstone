<template>
    <div class="search-restaurants">
        <h2>Search</h2>
        <input placeholder="Enter ZIP Code" type="text" v-model="zipCode"/>
        <button @click="searchByZipCode">Search</button>

        <!-- <div v-if="loading" class="loading">Loading...please wait...</div>
        loading and error stuff here -->
        <div v-if="restaurants.length" class="restaurant-cards">
        <RestaurantCard/>
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
            try {
                const response = await RestaurantService.list(this.zipCode);
                this.restaurants = response.data;
            } catch (error) {
                console.error('Error fetching restaurants:', error);
            }
        }
    }
}



</script>

<style>
</style>