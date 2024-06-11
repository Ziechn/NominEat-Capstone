<template>
  <HeaderComp/>
  <div id="register" class="register-container">
    <div class="card-front">
      <div class="form-container">
        <form class="form" v-on:submit.prevent="register">
          <h1 class="create-account">Create Account</h1>
          <div class="form-input-group">
            <input placeholder="Username" type="text" id="username" v-model="user.username" required />
          </div>
          <div class="form-input-group">
            <input placeholder="Email Address" type="text" id="email" v-model="user.email" required />
          </div>
          <div class="form-input-group">
            <input placeholder="Password" type="password" id="password" v-model="user.password" required />
          </div>
          <div class="form-input-group">
            <input placeholder="Confirm Password" type="password" id="confirmPassword" v-model="user.confirmPassword"
              required />
          </div>
          <button class="create-account-button" type="submit">Create Account</button>
          <div role="alert" class="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <p>Already have an account? <router-link v-bind:to="{ name: 'login' }">Log in.</router-link></p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderComp from '../components/HeaderComp.vue';
import authService from '../services/AuthService';

export default {
    data() {
        return {
            user: {
                username: '',
                email: '',
                password: '',
                confirmPassword: '',
                passwordStrength: '',
                role: 'user',
            },
            registrationErrors: false,
            registrationErrorMsg: 'There were problems registering this user.',
        };
    },
    methods: {
        register() {
            this.clearErrors();
            if (this.user.password != this.user.confirmPassword) {
                this.registrationErrors = true;
                this.registrationErrorMsg = 'Password & Confirm Password do not match.';
            }
            else if (!this.checkPasswordStrength(this.user.password) || !this.checkPasswordStrength(this.user.confirmPassword)) {
                this.registrationErrors = true;
                this.registrationErrorMsg = 'Password must be at least 8 characters long and contain one uppercase letter, one lowercase letter, one special character, and one number;';
                return; //exits register early if password issues are still present
            }
            else {
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
                        this.registrationErrorMsg = 'Username is already in use. Please enter a different username.';
                    }
                });
            }
        },
        clearErrors() {
            this.registrationErrors = false;
            this.registrationErrorMsg = 'Email already in use. Please enter a different email.';
        },
        checkPasswordStrength(password) {
            const passwordCriteria = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9])(?=.{8,}$)/;
            return passwordCriteria.test(password);
        }
    },
    components: { HeaderComp }
};
</script>

<style scoped>

.card-front {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 700px;
  height: 500px;
}

.form-container {
  width: 90%;
  padding:20px;
  text-align: center;
}

.register-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

h1 {
  margin: 10px;
  padding-bottom: 0.6em;
}

.alert {
  margin-top: 1.2em;
}

</style>
