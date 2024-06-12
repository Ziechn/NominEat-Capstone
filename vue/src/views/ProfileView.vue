<template>
    <HeaderComp/>
    <div class="profile-container">
        <Profile/>
    </div>
    <Footer/>
</template>

<script>
import Profile from '@/components/user/Profile.vue';
import HeaderComp from '@/components/HeaderComp.vue';
import Footer from '../components/Footer.vue';

export default {
    components: {
        Profile,
        HeaderComp,
        Footer
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