<template>
  <div class="home">
    <h1>Home</h1>
    <p>You are authenticated if you see this.</p>
    <RestaurantCard/>
  </div>
</template>

<script>
import RestaurantCard from '../components/RestaurantCard.vue';

export default {
    components: {
    RestaurantCard
}
};
</script>
