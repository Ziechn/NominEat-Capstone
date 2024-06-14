<!-- provide a thumbs up and down button -->

<template>
    <div class="vote-buttons">
        <ThumbsUpButton :eventId="eventId" :restaurantId="restaurantId" />
        <ThumbsDownButton :eventId="eventId" :restaurantId="restaurantId" />
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
};
</script>

<style scoped>
.vote-buttons {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 0px;
}
</style>