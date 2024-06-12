<!-- handle thumbs up voting -->
<template>
    <button @click="addYesVote">yes</button>
</template>

<script>
import EventService from '../../services/EventService';

export default {
    props: {
        eventId: String,
        restaurantId: String
    },
    methods: {
        addYesVote() {
            EventService.addYesVote(this.eventId, this.restaurantId).then(() => {
 //voting success handling
            }
           
            );
        }
    }
};

</script>