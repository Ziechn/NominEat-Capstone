<template>
  <div id="app"> 
      <router-view></router-view>
    <!-- <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view /> -->
  </div>
  <div>
    <EventInformation />
  </div>
</template>

<script>
import EventInformation from './components/EventInformation.vue';
export default{
  name: 'App',
  data() {
    return {
      date: ''
    }
  },
  components: {
    EventInformation
  }
};
</script>


<style scoped>
nav {
  display: flex;
}
</style>