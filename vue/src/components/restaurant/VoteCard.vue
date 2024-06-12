<template>
        <div class="vote-card">
            <VoteButtons :eventId="eventId" :restaurantId="restaurant.id"  />
            <VotingStats :eventId="eventId" :restaurantId="restaurant.id" />
        </div>
</template>

<script>
import VoteButtons from './VoteButtons.vue';
import VotingStats from './VotingStats.vue';

export default {
    components: { VoteButtons, VotingStats },
    props: {
        eventId: String,
        restaurant: Object
    }
};
</script>