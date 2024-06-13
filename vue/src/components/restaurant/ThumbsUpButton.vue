<!-- handle thumbs up voting -->
<template>
    <button @click="addYesVote">Thumbs Up</button>
    {{ restaurantId }}  {{ eventId }}
</template>

<script>
import EventService from '../../services/EventService';

export default {
   props: 
        ['restaurantId', 'eventId'],
        
    methods: {
        async addYesVote() {
            try {
                await  EventService.addYesVote(this.eventId, this.restaurantId);
                alert('Vote added successfully');
            }catch (error) {
                console.error('Error adding vote:', error);
            }

 //voting success handling in store
               // this.$emit('voteSuccess', 'yes');
            }
        }
};

</script>