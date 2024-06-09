<template>
    <div class="search-restaurants">
        <h2>Search Restaurants</h2>  
        <!-- <div>
            <p>Showing Results for Zip code: {{ zipCode }}</p>
        </div> -->
    <!--<div v-if="filteredRestaurants.length">
        <input 
             type="text"
             v-model="zipCode" 
             placeholder="Enter ZIP Code" 
             @input="updateZipCode"
            />
            <input 
             type="text"
             v-model="category" 
             placeholder="Filter by Category" 
             @input="filterByCategory"
            />
            <select v-model="limit" @change="searchByZipCode" class="search-input"> 
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option> 
            </select>
            <button @click="searchByZipCode">Search</button>
            </div> -->
        <!-- <form class="search-form" @submit.prevent="searchByZipCode" v-if="!hasSelected"> 
            <div class="input-group"> -->
                    <!-- <input type="text" v-model="zipCode"  placeholder="Enter Zip Code" /> -->
         
    
        <div v-if="loading" class="loading">Loading...please wait...</div>
        <div v-if="!loading && filteredRestaurants.length" class="restaurant-cards"> 
            <RestaurantCard
            v-for="restaurant in filteredRestaurants" 
            v-bind:key="restaurant.id" 
            v-bind:restaurant="restaurant"
            />
                        <!-- @select="selectRestaurant" -->
         </div>
         <div v-if="!loading && !filteredRestaurants.length">
        No results found...
        <!-- </div> 
        <div v-if="selectedRestaurants.length">
        <h3>Selected Restaurants</h3>
        <ul>
            <li v-for="restaurant in selectedRestaurant" :key="restaurant.id">{{  restaurant.name  }}</li>
        </ul>
        <button @click="saveSelectedRestaurants">Save Selected Restaurants</button> -->
        </div>
    </div>
</template>

<script>
import { mapState,  mapActions, mapMutations } from 'vuex';
import RestaurantCard from '@/components/RestaurantCard.vue';


export default{
    components: {
        RestaurantCard,
    },
    data() {
        return {
            // zipCode: this.$store.state.zipCode,
            category: '',
            limit: 10,
            selectedRestaurants: [],
            // hasSelected: false
        };
    },
    computed: {
        ...mapState(['zipCode','filteredRestaurants', 'loading']),
    },
    methods: {
        ...mapActions(['fetchRestaurants']), //'saveRestaurants'
        ...mapMutations(['SET_ZIP_CODE','FILTER_BY_CATEGORY', 'SET_LIMIT']),
        updateZipCode(event) {
            this.SET_ZIP_CODE(event.type.value);
        },
        searchByZipCode() {
            this.fetchRestaurants({ zipCode: this.zipCode, limit: this.limit });
        },
        filterByCategory() {
            this.FILTER_BY_CATEGORY(this.category);
        },
        selectRestaurant(restaurant) {
            if (!this.selectedRestaurants.includes(restaurant)) {
                this.selectedRestaurants.push(restaurant);
                this.hasSelected = true;
            }
        },
        saveSelectedRestaurants() {
            this.selectedRestaurants(this.selectedRestaurants);
    }
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
    text-align: center;
}

.search-form {
    display: flex;
}


.search-input,
.search-button{
    margin: 5px 0;
    padding: 10px;
    border: 1px solid var(--bg-300);
    border-radius: 5px;
}

.search-input {
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