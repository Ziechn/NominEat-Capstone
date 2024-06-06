<template>
  <div class="home">
    <h1>NominEat</h1>
    <p>Welcome! Please enter your ZIP to find restaurants near you.</p>
    <form @submit.prevent="goToSearch">
      <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
      <button type="submit">Submit</button>
    </form> 
    <div v-if="restaurants.length">
      <input 
        type="text" v-model="category" placeholder="Filter by Category" @input="filterByCategory" />
    </div>
    

    
    <!-- <router-link to="/search">Click to search for restaurants</router-link> -->
  <!-- <SearchRestaurants/>  -->

  <div v-if="loading" class="loading">Loading...</div>

  <div v-if="!loading" class="restaurant-cards">
  <RestaurantCard v-for="restaurant in filteredRestaurants" :key="restaurant.id" :restaurant="restaurant" />
  </div>
  </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue';
import { mapState, mapActions, mapMutations } from 'vuex';

//import SearchRestaurants from '../components/SearchRestaurants.vue';



export default {
    data() {
        return {
            zipCode: '',
            category: ''
        };
    },
    computed:{
      ...mapState(['restaurants', 'filteredRestaurants', 'loading'])
    },
    methods: {
      ...mapActions(['fetchRestaurants']),
      ...mapMutations(['FILTER_BY_CATEGORY']),
        goToSearch() {
            if (this.zipCode) {
                this.$store.commit('SET_ZIP_CODE', this.zipCode);
                // this.$router.push({ name: 'SearchRestaurants' });
                this.fetchRestaurants(this.zipCode);
            }
        },
        filterByCategory() {
          this.FILTER_BY_CATEGORY(this.category);
        }
    },
    components: { RestaurantCard }
};
//     components: {
//     SearchRestaurants
// }

</script>
<style scoped>
.home {
  text-align: center;
  padding: 20px;
}
h1 {
  color: var(--text-100);
}
p {
  color: var(--primary-100);
}
.loading {
  margin-top: 10px;
  color: var(--primary-100);
}
.restaurant-cards {
  display: flex;
  flex-wrap: wrAP;
  gap: 20px;
  margin-top: 20px;
  justify-content: center;
}
</style>