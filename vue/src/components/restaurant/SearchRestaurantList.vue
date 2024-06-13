<!-- reusing card component to display search results -->
<!-- fetches restaurant data from API displays it and allows saving of ten to database -->

<template> 
<div>
    <HeaderComp />
    <div class="search-restaurant-list">
    <h2>Search Restaurants</h2>
        <input v-model="zipcode" placeholder="Enter Zipcode" />
        <input v-model="limit" type="number" placeholder="Limit" />
        <input v-model="term" placeholder="Type of Establishment" />
        <button @click="fetchRestaurants">Search</button>
        <button @click="doIt">Fetch</button>

        <div v-if="restaurants.length">
    <div v-for="restaurant in restaurants" :key="restaurant.id" >
    <RestaurantCard :restaurant="restaurant" />
</div>
    <button @click="saveRestaurants">Save Restaurants</button>
    </div>
</div>
</div>
</template>

<script>

//import RestaurantService from '@/services/RestaurantService';
//import EventService from '@/services/EventService';
import HeaderComp from '@/components/HeaderComp.vue';
import RestaurantCard from '@/components/restaurant/RestaurantCard.vue';
import { mapActions, mapState } from 'vuex';


export default {
    components: {
        HeaderComp,
        RestaurantCard
    },
    data() {
        return {
        zipcode: '',
        limit: 10,
        term: ''
       // restaurants: []
    };
    },
    computed: {
    ...mapState(['restaurants']),
      //  ...mapGetters(['getRestaurants']),
    //     restaurants() {
    //         return this.getRestaurants
     },
    methods: {
        ...mapActions(['fetchRestaurants', 'associateRestaurantsWithEvent']),
        fetchRestaurants() {
            this.fetchRestaurants({ 
            zipcode: this.zipcode, 
            limit: this.limit, 
            term: this.term
        });
    },
    saveRestaurants() {
        const eventId = this.$route.params.eventId;
        this.associateRestaurantsWithEvent({ eventId, restaurants: this.restaurants.slice(0, 10) })
        .then(() => {  //      this.fetchRestaurants({ eventId, restaurants: this.restaurant.slice(0, 10) }).
            console.log('HERE: ' + this.$store.state.restaurants)
            alert('Restaurants saved successfully');
        });
    }},
    doIt() {
        console.log('FETCH: ' + this.$store.state.restaurants)
    }
};
</script>

<style scoped>
.search-restaurant-list {
    margin: 20px;
}
</style>

