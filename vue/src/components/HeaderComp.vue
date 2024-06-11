<template>
    <div id="app">
      <header class="sticky-header">
        <div class="title-img-container">
          <h1 class="title">NominEat</h1>
          <img class="logo" src="../assets/cropped-logo.png">
        </div>
        <nav class="nav-container">
          <router-link v-if="isSignedIn && showProfileLink" v-bind:to="{ name: 'profile' }">PROFILE</router-link>
          <router-link v-if="!isSignedIn && showLoginLink" v-bind:to="{ name: 'login' }">LOG IN</router-link>
          <router-link v-if="showHomeLink" v-bind:to="{ name: 'home' }">HOME</router-link>
        </nav> 
        
      </header>
    </div>
  </template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';

  export default {
    computed: {
      ...mapState([ 'user' ]), 
      showHomeLink(){
        return this.$route.path !== '/'
      },
      showProfileLink() {
        return this.$route.path !== '/profile';
      },
      showLoginLink() {
        return this.$route.path !== '/login' && this.$route.path !== '/register';
      },
      showLogoutLink() {
        return this.$route.path !== '/login' && this.$route.path !== '/register';
      },
      isSignedIn(){
        if (this.$store.state.user.id != undefined){
          return true;
        } else {
          return false;
        }
      }
    },
    methods: {
      ...mapMutations([ 'LOGOUT' ]),
      ...mapActions([ 'fetchUser' ]),
      logout(){
        this.LOGOUT();
        this.$router.push({ name: 'login' });
      }
    },
    created(){
      this.fetchUser();
    }
  }
</script>

<style scoped>
.sticky-header{
  display: flex;
  align-items: center;
  justify-content: flex-end;
  position: top;
  width: 100%;
  background-color: var(--bg-100);
}
/* 
  height: 60px;
  border-bottom-width: 1.1px;
  border-bottom-color: var(--primary-100);
  box-shadow: 0 2px 6px var(--primary-100);
border-bottom-style: solid;*/
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
}

</style>