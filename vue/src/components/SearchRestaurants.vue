<template>
    <div class="search-restaurants">
        <h2>Search</h2>
        <form class="search-form" @submit.prevent="searchByZipCode">
            <input placeholder="Enter ZIP Code" type="text" v-model="zipCode" @input="updateZipCode"/>
            <input placeholder="Enter # to Limit Results" type="number" v-model="limit" @input="updateLimit"/>
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
import { mapState, mapMutations, mapActions } from 'vuex';
import RestaurantCard from '@/components/RestaurantCard.vue';


export default{
    components: {
        RestaurantCard,
    },
    // data() {
    //     return {
    //         searchText: '',
    //         loading: false,
    //         error: '',
    //     };
    // },
    computed: {
        ...mapState(['zipCode', 'limit', 'restaurants', 'loading']),
    },

    methods: {
        ...mapMutations(['SET_ZIP_CODE', 'SET_LIMIT']),
        ...mapActions(['fetchRestaurants']),
        searchByZipCode() {
            this.fetchRestaurants({ zipCode: this.zipCode, limit: this.limit });
        },
        updateZipCode(event) {
            this.SET_ZIP_CODE(event.target.value);
        },
        updateLimit(event) {
            this.SET_LIMIT(event.target.value);
        },
        // async searchByZipCode() {
        //     this.loading = true;
        //     try {
        //         const response = await RestaurantService.list(this.zipCode, this.limit);
        //         this.restaurants = response.data;
        //         this.loading = false;
        //     } catch (error) {
        //         console.error('Error fetching restaurants:', error);
        //         this.loading = false;
        //     } 
        // }
    },
    created() {
        this.searchByZipCode();
    }
};
</script>

<style scoped>
.search-restaurants {
    max-width: 800px;
    margin: auto;
    padding: 20px;
    background-color: var(--bg-100);
    color: var(--text-100);
}

.search-form input,
.search-form button{
    margin: 5px 0;
    padding: 10px;
    border: 1px solid var(--bg-300);
    border-radius: 5px;
}

.search-form input {
    width: calc(100% - 22px);
}

.search-form button {
    background-color: var(--primary-100);
    color: white;
    cursor: pointer;
}

.search-form:hover {
    background-color: var(--primary-200);
}

.loading {
    margin-top: 10px;
    color: var(--primary-100);
}

.restaurant-cards {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
    }
</style>