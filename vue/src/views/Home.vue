<template>
  <div class="page-container">
    <HeaderComp />
    <div class="home-container">
      <div class="card-front">
        <div class="title">
          <h2> Welcome! </h2>
        </div>
        <p class="description">
          NominEat simplifies choosing where to eat with friends. No more endless discussions!
          Just log in, enter your zip code, and get a list of nearby restaurants. Then share the link with
          friends, who can then vote on their preferred spot. NominEat tallies the votes and picks the
          restaurant with the most support, making planning easy and hassle-free. Enjoy more time with good
          food and company!
        </p>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import HeaderComp from '../components/HeaderComp.vue';
import Footer from '../components/Footer.vue';

export default {
  components: {
    HeaderComp,
    Footer
  }
}

</script>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.home-container {
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 50px;
}

.card-front {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 50%;
  height: 550px;
}

.title {
  padding-top: 20px;
  padding-left: 20px;
  font-size: 32px;

}

.description {
  padding-right: 80px;
  padding-left: 80px;
  padding-bottom: 80px;
  font-size: 22px;
  text-indent: 2em;
  line-height: 1.5em;
}
</style>