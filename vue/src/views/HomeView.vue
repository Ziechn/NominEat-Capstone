<template>
  <div class="home">
    <h1>NominEat</h1>
    <p>Welcome! PLease enter your ZIP to find restaurants near you.</p>
    <form @submit.prevent="goToSearch">
      <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
      <button type="submit">Submit</button>
    </form>
    <!-- <router-link to="/search">Click to search for restaurants</router-link> -->
  <!-- <SearchRestaurants/>  -->

  </div>
</template>

<script>
// import SearchRestaurants from '../components/SearchRestaurants.vue';



export default {
  data(){
    return {
      zipCode: '',
    };
  },
  // name: 'home',
  methods: {
    goToSearch(){
      if(this.zipCode) {
        this.$store.commit('SET_ZIP_CODE', this.zipCode);
        this.$router.push({ name: 'SearchRestaurants' });
      }
    },
  },
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
</style>