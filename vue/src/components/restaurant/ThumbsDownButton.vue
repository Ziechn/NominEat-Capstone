<!-- handle thumbs up voting -->
<template>
    <button @click="addNoVote">yes</button>
</template>

<script>
import EventService from '../../services/EventService';

export default {
    props: {
        eventId: String,
        restaurantId: String
    },
    methods: {
        addNoVote() {
            EventService.addNoVote(this.eventId, this.restaurantId).then(() => {
 //voting success handling
            this.$emit('voteSuccess', 'yes');
            });
        }
    }
};

</script>