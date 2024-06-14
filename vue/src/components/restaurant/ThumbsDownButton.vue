<!-- handle thumbs up voting -->
<template>
    <button class="thumbs-up-button" @click="addNoVote"></button>
</template>

<script>
import EventService from '../../services/EventService';


export default {
   props: 
        ['restaurantId', 'eventId'],

methods: {
        async addNoVote() {
            try {
                await  EventService.addNoVote(this.eventId, this.restaurantId);
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
    background: url('@/assets/DownButton.jpg') no-repeat center center;
    background-size: cover;
    border: none;
    width: 50px;
    height:50px;
    cursor: pointer;
    /* position: relative;
    display: inline-block; */

}
</style>