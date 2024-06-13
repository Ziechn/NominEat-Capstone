<!-- handle thumbs up voting -->
<template>
    <button @click="addNoVote">Thumbs Down</button>
</template>

<script>
import EventService from '../../services/EventService';


export default {
   props: 
        ['restaurantId', 'eventId'],
        async addYesVote() {
            try {
                await  EventService.addNoVote(this.eventId, this.restaurantId);
                alert('Vote added successfully');
            }catch (error) {
                console.error('Error adding vote:', error);
            }

 //voting success handling in store
               // this.$emit('voteSuccess', 'yes');
            }
};
</script>