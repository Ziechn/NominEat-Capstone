<template>
    <div class="user-profile">
        <h1>User Profile</h1>
        <p>Username: {{ user.username }}</p>
        <p>Email: </p>
    </div>
    <img class="placeholder-img" src="../assets/stock-user_img.jpg"/>
    <div class="user-events">
        <h2>Events</h2>
        <p>Events go here</p>
    </div>
    <button @click="logout()">Log Out</button>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';

export default {
    computed: {
        ...mapState(['user'])
    },
    methods: {
        ...mapActions(['fetchUser']),
        ...mapMutations(['LOGOUT']),

        logout(){
            this.LOGOUT();
            this.$router.push({ name: 'login' })
        }
    },
    created() {
        this.fetchUser();
    }
}
</script>

<style scoped>
.placeholder-img {
    height: 50px;
    border-radius: 50%;
    border-width: 3px;
    border-color: --bg-100;
}

template {
    display: flex;
    text-align: center;
}


</style>