<template>
  <div class="login-zip-container"></div>
    <flipCard ref="flipCard">
      <template #front>
        <div class="form-container">
          <h1 >Please Sign In</h1>
          <form v-on:submit.prevent="login">
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="form-input-group">
              <label for="username">Username</label>
              <input type="text" id="username" v-model="user.username" required autofocus />
            </div>
            <div class="form-input-group">
              <label for="password">Password</label>
              <input type="password" id="password" v-model="user.password" required />
            </div>
            <button type="submit">Sign in</button>
            <p>
            <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
            </p>
          </form>
        </div>
      
      </template>

      <template #back>
        <div class="form-container">
          <div class="home">
            <h1>NominEat</h1>
            <p>Welcome! Please enter your ZIP to find restaurants near you.</p>
            <form @submit.prevent="goToSearch">
            <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
            <button type="submit">Submit</button>
            </form> 
          </div>
          <RouterLink v-bind:to="{name: 'profile'}">
            View Profile
          </RouterLink>
        </div>
      </template>
    </flipCard>
</template>

<script>
import FlipCard from "../components/FlipCard.vue";
import authService from "../services/AuthService";

export default {
  components: {
    FlipCard
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      zipCode: '',
      error: '',
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.flip();
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    flip() {
      this.$refs.flipCard.flipCard();
    },
    goToSearch() {
      if (this.zipCode) {
          this.$store.commit('SET_ZIP_CODE', this.zipCode);
          this.$store.dispatch( 'fetchRestaurants', { zipCode: this.zipCode, limit: 10}); //This is why search was not loading anything,,, we needed to also get the restaurants not just search them
          this.$router.push({ name: 'SearchRestaurants' });
        }
    }

  },
  computed: {
    isAuthenticated(){
      return this.$store.state.user !== null;
    }
  }
};
</script>

<style scoped>
.login-zip-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: center;
}

.form-container {
  width: 80%;
  padding:20px;
  text-align: center;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>