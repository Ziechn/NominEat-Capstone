<template>
    <div class="search-restaurants">
        <h2>Restaurants Near You</h2>
            <div>
                <p>Showing Results for ZIP code: {{  zipCode  }}</p>
            </div>
            <div v-if="filteredRestaurants.length">
            <input 
             type="text"
             v-model="category" 
             placeholder="Filter by Category" 
             @input="filterByCategory"
            />
            </div>

        <div v-if="loading" class="loading">Loading...please wait...</div>
        
        <!-- cards -->
        <!-- && filteredRestaurants.length" -->
        <!-- <div v-if="!loading && filteredRestaurants.length" class="restaurant-cards"> -->  
        <div v-if="!loading && filteredRestaurants.length" class="restaurant-cards">  
            <RestaurantCard v-for="restaurant in filteredRestaurants" v-bind:key="restaurant.id" v-bind:restaurant="restaurant"/>
        </div>

        <!-- && !filteredRestaurants.length -->
        <!--<div v-if="!loading && !filteredRestaurants.length">  -->
        <div v-if="!loading && !filteredRestaurants.length">
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
    data() {
        return {
         category: '',
        };
    },
    computed: {
        ...mapState(['zipCode','filteredRestaurants', 'loading']),
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
        this.fetchRestaurants({ zipCode: this.zipCode, limit: 3});
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