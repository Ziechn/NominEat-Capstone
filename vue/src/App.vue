<template>
  <div id="app">
    <router-view></router-view>
              <!-- <header class="sticky-header">
                <nav>
                  <router-link to="/" aria-label="Home"><i class="icon-house"></i></router-link>
                  <router-link to="/search" aria-label="Search"><i class="icon-magnifying-glass"></i></router-link>
                  <router-link to="/profile" aria-label="Profile" v-if="$store.state.token != ''"><i class="icon-user"></i></router-link>
                  <router-link to="/login" aria-label="Profile" v-if="$store.state.token != ''"><i class="icon-exit"></i></router-link>
                </nav> -->
              <div id="nav">
                <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
              </div>
              <router-view />
  </div>
</template>

<script>
export default{
  name: 'App',
};
</script>


<style scoped>
nav {
  display: flex;
}
</style>