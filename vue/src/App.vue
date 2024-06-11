<template>
  <div id="app"> 
      <router-view></router-view>
    <!-- <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view /> -->
  </div>
</template>

<script>
export default{
  name: 'App',
  data() {
    return {
      date: ''
    }
  }
};
</script>


<style scoped>
nav {
  display: flex;
}
</style>