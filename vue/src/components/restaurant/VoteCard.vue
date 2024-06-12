<template>
        <div class="vote-card">
            <VoteButtons :eventId="eventId" :restaurantId="restaurant.id"  />
        </div>
</template>

<script>
import VoteButtons from './VoteButtons.vue';

export default {
    components: { VoteButtons},
    props: 
        ['restaurant', 'eventId'],
};
</script>