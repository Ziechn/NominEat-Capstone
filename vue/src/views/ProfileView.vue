<template>
    <HeaderComp/>
    <div class="profile-container">
        <Profile/>
    </div>
</template>

<script>
import Profile from '@/components/user/Profile.vue';
import HeaderComp from '@/components/HeaderComp.vue';

export default {
    components: {
        Profile,
        HeaderComp,
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