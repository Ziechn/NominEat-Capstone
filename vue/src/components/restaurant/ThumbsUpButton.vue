<!-- handle thumbs up voting -->
<template>

    <button class="thumbs-up-button" @click="addYesVote"></button>
</template>

<script>
import EventService from '../../services/EventService';

export default {
   props: 
        ['restaurantId', 'eventId', 'imageUrl'],
        
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

<style scoped>
.thumbs-up-button {
    background: url('@/assets/UpButton.jpg') no-repeat center center;
    background-size: cover;
    border: none;
    width: 50px;
    height:50px;
    cursor: pointer;
    /* position: relative;
    display: inline-block; */
}

</style>