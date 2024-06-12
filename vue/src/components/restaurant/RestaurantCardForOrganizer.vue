<!-- displays details of r for event organizer and includes voting stats -->

<template>
        <div class="restaurant-card">
            <h3>{{ restaurant.name }}</h3>
            <p>{{ restaurant.type }}</p>
            <p>{{ restaurant.address1 }}</p>
            <p>{{  restaurant.hours }}</p>
            <VotingStats :eventId="eventId" :restaurantId="restaurant.id" />
        </div>
</template>

<script>
import VotingStats from '@/components/restaurant/VotingStats.vue';

export default {
    components: {
        VotingStats
    },
    props: 
        ['restaurant', 'eventId']
};
</script>

<style>
</style>