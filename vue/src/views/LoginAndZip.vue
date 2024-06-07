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
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>
      <template #back>
        <div class="form-container">
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
        password: ""
      },
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
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    //setzip if else
    flip() {
      this.$refs.flipCard.flipCard();
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