<template>
  <HeaderComp/>
  <div class="login-zip-container">
    <flipCard ref="flipCard">
      <template #front>
        <div class="form-container">
          <h1>Login</h1>
          <form v-on:submit.prevent="login">
            <div class="register-success" role="alert" v-if="this.$route.query.registration">
              Account created! Please log in.
            </div>
            <div class="form-input-group">
              <input placeholder="Username" type="text" id="username" v-model="user.username" required autofocus />
            </div>
            <div class="form-input-group">
              <input placeholder="Password" type="password" id="password" v-model="user.password" required />
            </div>
            <button type="submit">Sign in</button>
            <div role="alert" class="alert" v-if="invalidCredentials">
              Invalid username or password.
            </div>
            <p> Need an account? 
            <router-link v-bind:to="{ name: 'register' }">Sign up.</router-link>
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
          <router-link v-bind:to="{ name: 'profile' }">
            View Profile
          </router-link>
        </div>
      </template>
    </flipCard>
  </div>
</template>

<script>
import FlipCard from '@/components/cardAnimations/FlipCard.vue';
import HeaderComp from '@/components/HeaderComp.vue';
import AuthService from '@/services/AuthService';

export default {
  components: {
    FlipCard,
    HeaderComp
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
      AuthService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.flip();
          }
        })
        .catch(error => {
          console.log(error);
          // const response = error.response;

          //if (response.status === 401) {
          //  this.invalidCredentials = true;
          //}
        });
    },
    flip() {
      this.$refs.flipCard.flipCard();
    },
    goToSearch() {
      if (this.zipCode) {
          this.$store.commit("SET_ZIP_CODE", this.zipCode);
          this.$store.dispatch( "fetchRestaurants", { zipCode: this.zipCode, limit: 2}); 
          this.$router.push({ name: "SearchRestaurants" });
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
  align-items: center;
  /* padding-top: 40px; */
}

.form-container {
  width: 80%;
  padding:20px;
  text-align: center;
}

.form-input-group {
  margin-bottom: 1rem;
}

.register-success{
  margin-bottom: 0.5;
}

.alert {
  margin-top: 1.2em;
}
</style>