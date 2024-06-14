<template>
    <div class="page-container">
        <div class="header-container">
            <HeaderComp />
        </div>
        <div class="profile-container">
            <Profile />
        </div>
        <div class="header-container">
            <Footer />
        </div>
    </div>
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
    overflow: auto;
    padding-top: 20px;
    padding-bottom: 60px;
}

.page-container {
    display: flex;
    flex-direction: column;
}


</style>

