<template>
    <div class="restaurant-card">
        <h3>{{  restaurant.name }}</h3>
        <p>{{  restaurant.type }}</p>
        <p>{{  restaurant.address1 }}</p>
<!-- 
        enter r card here -->
        <VoteCard :eventId="eventId" :restaurant="restaurant" />
    </div>
</template>

<script>
import VoteCard from '@/components/restaurant/VoteCard.vue';

export default {
    components: {
        VoteCard
    },
    props: 
        ['restaurant', 'eventId']

};

</script>