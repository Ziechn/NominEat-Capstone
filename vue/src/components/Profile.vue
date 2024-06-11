<template>
    <div class="user-profile">
        <div class="profile-content">
            <div>
                <h1>User Profile</h1>
                <p>Username: {{ user.username }}</p>
                <p>Email: {{ user.email }}</p>
            </div>
            <img class="placeholder-img" src="../assets/stock-user_img.jpg"/>
            <div class="user-events">
                <h2>Events</h2>
                <p>Events go here</p>
                <EventOrganizer />
            </div>
        </div>
    </div>
    <button @click="logout()">Log Out</button>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';
import EventOrganizer from './EventOrganizer.vue';

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
    },
    components: {
        EventOrganizer
    }
}
</script>

<style scoped>
.placeholder-img {
    height: 50px;
    border-radius: 50%;
    border-width: 3px;
    border-color: var(--bg-100);
}

.user-profile {
    display: flex;
    justify-content: center;
}
</style>