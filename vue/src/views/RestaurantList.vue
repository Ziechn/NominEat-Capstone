<template>
    <div class="restaurant-list">
        <h2>Restaurants Near You</h2>
        <form class="search-form"
            @submit.prevent="searchByCategory">
            <input placeholder="Enter Category" type="text" v-model="category" />
            <button type="submit">Search</button>
         </form>
        
         <div v-if="loading" class="loading">Loading...Please Wait...</div>

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
    data() {
        return {
            category: '',
        };
    },
    computed: {
        ...mapState(['filteredRestaurants', 'loading']),
    },
    methods: {
        ...mapActions(['fetchRestaurants']),
        searchByCategory() {
            this.$store.commit('FILTER_BY_CATEGORY', this.category);
        }
    },
    created() {
        //default load for now
        this.fetchRestaurants({ zipCode: this.$store.state.zipCode, limit: this.$store.state.limit });
    }
};
</script>
<style scoped>
.restaurant-list {
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


.search-form button:hover {
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
    justify-content: center;
    }

    .no-results {
        color: var(--text-200);
    }

</style>