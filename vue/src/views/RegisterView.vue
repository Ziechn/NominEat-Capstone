<template>
  <div id="register" class="register-container">
    <h1 class="nomineat">NominEAT</h1>
    <form class="form" v-on:submit.prevent="register">
      <h2 class="create-account" >Create Account</h2>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <input placeholder="Username" type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <input placeholder="Email Address" type="text" id="email" v-model="user.email" required />
      </div>
      <div class="form-input-group">
        <input placeholder="Password" type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <input placeholder="Confirm Password" type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button class="create-account-button" type="submit">Create Account</button>
      <p>Already have an account? <router-link v-bind:to="{ name: 'login' }">Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

/* .form-input-group {
  margin-bottom: 1rem;
} */

.create-account-button {
  margin-top: 1em;
}

.form {
  margin-left: 1rem;
}

.nomineat {
  margin-left: 5px;
}

/* .header {
  
} */
</style>
