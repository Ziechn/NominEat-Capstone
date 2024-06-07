<template>
    <div class="search-restaurants">
        <div>
                <p>Showing Results near {{  zipCode  }}.</p>
            </div>
            <div v-if="filteredRestaurants.length">
            <input 
             type="text"
             v-model="category" 
             placeholder="Filter by Category" 
             @input="filterByCategory"
            />
            </div>

        <h2>Search Restaurants</h2>
        <form class="search-form" @submit.prevent="searchByZipCode">
        <div class="input-group">
            <input type="text" v-model="zipCode"   placeholder="Enter Zip Code" />
            <input type="text" v-model="category"   placeholder="Search by type of restaurant" />
            <select v-model="limit" class="search-input">
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
            </select>
            <button type="submit" class="submit">Search</button>
        </div>
        </form>
           
        <div v-if="loading" class="loading">Loading...please wait...</div>
        <div v-if="!loading && filteredRestaurants.length" class="restaurant-cards">  
            <RestaurantCard v-for="restaurant in filteredRestaurants" v-bind:key="restaurant.id" v-bind:restaurant="restaurant"
            @select="selectRestaurant"/>
        </div>

        <!-- && !filteredRestaurants.length -->
        <!--<div v-if="!loading && !filteredRestaurants.length">  -->
        <div v-if="!loading && !filteredRestaurants.length">
            No results found...
        </div>
        <div v-if="selectedRestaurant.length" class="selected-list">
        <h3>Selected Restaurants</h3>
        <ul>
            <li v-for="restaurant in selectedRestaurant" :key="restaurant.id">{{  restaurant.name  }}</li>
        </ul>
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
    data() {
        return {
         category: '',
        };
    },
    computed: {
        ...mapState(['zipCode','filteredRestaurants', 'restaurants','loading']),
    },
    methods: {
        ...mapActions(['fetchRestaurants']),
        ...mapMutations(['FILTER_BY_CATEGORY']),
        filterByCategory() {
            this.FILTER_BY_CATEGORY(this.category);
        },
        restaurantSelectionList() {
            if (this.zipCode) {
                this.$store.commit('SET_ZIP_CODE', this.zipCode);
                this.$router.push({ name: 'SearchRestaurants' });
            }
        }
    },
   created() {
        this.fetchRestaurants({ zipCode: this.zipCode, category: this.category, limit: this.limit });
}};
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

.search-input {
    width: 200px;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.submit:hover {
    background-color: var(--primary-200);
}

.loading {
    margin-top: 10px;
    color: var(--primary-100)
}

.restaurant-cards {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
    justify-content: center;
    }

    .no-results {
        color: var(--text-200);
    }
</style>