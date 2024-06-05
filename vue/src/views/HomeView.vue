<template>
  <div class="home">
    <h1>Home</h1>
    <p>You are authenticated if you see this.</p>
    <router-link to="/search">Click to search for restaurants</router-link>
  <!-- <SearchRestaurants/>  -->

  </div>
</template>

<script>
// import SearchRestaurants from '../components/SearchRestaurants.vue';



export default {
  name: 'home',
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
</style>