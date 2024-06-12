<!-- provide a thumbs up and down button -->

<template>
    <div class="vote-buttons">
        <ThumbsUpButton :eventId="eventId" :restaurantId="restaurantId" />
        <ThumbsDownButton :eventId="eventId" :restaurantId="restaurantId" />
    </div>
</template>

<script>
import ThumbsDownButton from './ThumbsDownButton.vue';
import ThumbsUpButton from './ThumbsUpButton.vue';

export default {
    components: { 
        ThumbsUpButton,
        ThumbsDownButton
    },
  props: 
        ['restaurant', 'eventId'],
};
</script>