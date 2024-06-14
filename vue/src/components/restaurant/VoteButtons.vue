<!-- provide a thumbs up and down button -->

<template>
    <div class="vote-buttons">
        <ThumbsUpButton :eventId="eventId" :restaurantId="restaurantId" :class="{ voted: isVoted }" class="vote-buttonq"/>
        <ThumbsDownButton :eventId="eventId" :restaurantId="restaurantId" :class="{ voted: isVoted }" class="vote-buttonl" />
    </div>
</template>

<script>
import ThumbsDownButton from '@/components/restaurant/ThumbsDownButton.vue';
import ThumbsUpButton from '@/components/restaurant//ThumbsUpButton.vue';

export default {
    components: { 
        ThumbsUpButton,
        ThumbsDownButton
    },
  props: 
        ['restaurantId', 'eventId'],
    methods: {
        vote(voteType) {
            this.isVoted = true;
        }
    }
};
</script>

<style scoped>

.vote-buttons {
cursor: pointer;
display: flex;
justify-content: space-between;
width: 100%;
margin-top: 10px;
transition: transform 0.3s ease;
}

.vote-buttonq:hover {
    transform: scale(1.1);
}
.vote-buttonl:hover {
    transform: scale(1.1);
}

.vote-buttons.voted {
    filter: blur(2px);

}




</style>
