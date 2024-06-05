<template>
  <div class="home">
    <h1>NominEat</h1>
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
h1 {
  color: var(--text-100);
}
p {
  color: var(--primary-100);
}
</style>