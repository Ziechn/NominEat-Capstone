<template>
  <HeaderComp/>
  <div class="title">
    <h2> Welcome! </h2>
  </div>
  <p class="description">
    NominEat simplifies the process of deciding where to eat with friends. Say goodbye to endless 
    back-and-forth conversations about dinner plans! With NominEat, you can easily coordinate dining 
    outings by logging in, entering your zip code, and generating a curated list of nearby restaurants. 
    Once you've selected your favorites, simply share the link with your friends. They'll be directed 
    to a page where they can vote on their preferred restaurant. NominEat tallies the votes and 
    automatically selects the restaurant with the most support as the venue for your gathering. It's 
    a hassle-free solution that streamlines the decision-making process, so you can spend less time 
    planning and more time enjoying good food and company.
  </p>
</template>

<script>
import HeaderComp from '../components/HeaderComp.vue';

export default {
  components: {
    HeaderComp
  }
}

</script>

<style scoped>

.title{
  padding-top: 10px;
  padding-left: 20px;
}

.description{
  padding-right: 20px;
  padding-left: 20px;
}
</style>