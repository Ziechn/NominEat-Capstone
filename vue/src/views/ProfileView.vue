<template>
    <HeaderComp/>
    <div class="profile-container">
        <Profile/>
    </div>
    <!-- <EventOrganizer/> -->
</template>

<script>
import Profile from '../components/Profile.vue';
import HeaderComp from '../components/HeaderComp.vue';
// import EmailEventLink from '../components/EmailEventLink.vue';
// import EventOrganizer from '../components/EventOrganizer.vue';



export default {
    components: {
        Profile,
        HeaderComp,
        // EmailEventLink,
        // EventOrganizer
    }    
}

</script>

<style scoped>

.profile-container {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    height: 100vh;
}

</style>