<template>
  <div class="home">
    <h1>Home</h1>
    <p>You are authenticated if you see this.</p>
  <SearchRestaurants/>
  </div>
</template>

<script>
import SearchRestaurants from '../components/SearchRestaurants.vue';



export default {
    components: {
    SearchRestaurants
}
};
</script>
