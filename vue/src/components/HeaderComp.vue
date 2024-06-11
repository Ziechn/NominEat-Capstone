<template>
    <div id="app">
      <header class="sticky-header">
        <div class="title-img-container">
          <h1 class="title">NominEAT</h1>
          <img class="logo" src="../assets/cropped-logo.png">
        </div>
        <nav class="nav-container">
          <router-link v-if="showProfileLink" v-bind:to="{ name: 'profile' }">Profile</router-link>
          <router-link v-if="showLoginLink" v-bind:to="{ name: 'login' }">Login</router-link>
          <router-link v-if="showLogoutLink" v-bind:to="{ name: 'logout' }" >Logout</router-link>
        </nav> 
        
      </header>
    </div>
  </template>

<script>
  export default {
    computed: {
      showProfileLink() {
        return this.$router.path !== '/profile' && this.$router.path !== '/login';
      },
      showLoginLink() {
        return this.$router.path !== '/login' && this.$router.path !== '/register';
      },
      showLogoutLink() {
        return (this.$router.path !== '/logout' && this.$router.path !== '/login' && this.$router.path !== '/register');
      }
    }
  }
</script>

<style scoped>
.sticky-header{
  display: flex;
  align-items: center;
  justify-content: flex-end;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
  background-color: var(--bg-100);
  height: 60px;
  border-bottom-width: 1.1px;
  border-bottom-color: var(--primary-100);
  border-bottom-style: solid;
  box-shadow: 0 2px 6px var(--primary-100);
}

.nav-container {
  display: flex;
  justify-content: flex-end;
  flex-direction: row;
  gap: 10px;
  padding: 16px;
  flex-grow: 1;
}
h1 {
  text-align: center;
}
/* a {
  color: var(--bg-200);
} */

.title-img-container{
  display: flex;
}

.title{
  padding-left: 16px;
}

.logo {
  height: 47px;
  padding-top: 27px;
  
  /* padding-right: 718px;
  padding-bottom: 15px; */
}

</style>