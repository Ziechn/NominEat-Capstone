<template>
  <div class="home">
    <h1>NominEat</h1>
    <p>Welcome! Please enter your ZIP to find restaurants near you.</p>
    <form @submit.prevent="goToSearch">
      <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
      <button type="submit">Submit</button>
    </form> 
    </div>
    <RouterLink v-bind:to="{name: 'profile'}">
      Profile
    </RouterLink>
</template>

<script>
export default {
    data() {
        return {
            zipCode: '',
        };
    },
    methods: {
        goToSearch() {
            if (this.zipCode) {
                this.$store.commit('SET_ZIP_CODE', this.zipCode);
                this.$router.push({ name: 'SearchRestaurants' });
            }
        }
        }
};

</script>
<style scoped>
.home {
  text-align: center;
  padding: 20px;
}
h1 {
  color: var(--text-100);
}
p {
  color: var(--primary-100);
}
.loading {
  margin-top: 10px;
  color: var(--primary-100);
}
.restaurant-cards {
  display: flex;
  flex-wrap: wrAP;
  gap: 20px;
  margin-top: 20px;
  justify-content: center;
}
</style>