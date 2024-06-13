<template>
    <div class="card-front">
        
        <div class="profile-content">
            <h2 class="title">My Profile</h2>
            <div class="user-info">
                <div class="profile-img">
                    <img class="placeholder-img" src="@/assets/stock-user_img.jpg" />
                </div>
                <div class="info">
                    <div class="info-label">Username</div>
                    <div class="info-data">{{ user.username }}</div>
                </div>
                <div class="info">
                    <div class="info-label">Email Address</div>
                    <div class="info-data">{{ user.email }}</div>
                </div>
            </div>
                <div class="button-container">
                    <button @click="logout()">Log Out</button>
                </div>
                <div class="line"></div>

            <div class="user-events">
                <h2 class="event-title">My Events</h2>
                <EventOrganizer/>
            </div>
            
        </div>
    </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';
import EventOrganizer from '@/components/event/EventOrganizer.vue';

export default {
    computed: {
        ...mapState(['user'])
    },
    methods: {
        ...mapActions(['fetchUser']),
        ...mapMutations(['LOGOUT']),

        logout() {
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
/* template{
    margin-bottom: 40px; 
} */
.placeholder-img {
    height: 100px;
    border-radius: 50%;
    border-width: 3px;
}

.card-front {
    display: flex;
    justify-content: center;
    width: 70%;
    height: auto; /* set to auto when importing event organizer? */

}

.user-info {
    display: flex;
    align-items: center;
    flex-direction: row;
    width: 100%;
    padding-left: 40px;
}

.profile-content {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin-bottom: 50px;
}

.title {
    padding-left: 40px;
    font-size: 30px;
    padding-bottom: 50px;
}

.info {
    padding-left: 30px;
}

.info-label {
    padding-bottom: 10px;
    font-size: 12px;
    font-weight: bold;
}

.info-data {
    color: #5c5c5c;
    font-size: 18px;
}

.user-events{
    padding-left: 40px;
    margin-bottom: 40px;
}

.button-container {
    display: flex;
    padding-right: 40px;
    padding-bottom: 20px;
    justify-content: end;
}

.line {
    width: 100%;
    height: 2px; 
    background: linear-gradient(to right, transparent, var(--primary-100) 50%, transparent); 
}

.event-title{
    padding: 0;
    margin-top: 20px;
    font-size: 30px;
}
</style>