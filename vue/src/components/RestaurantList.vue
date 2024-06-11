<template>
    <div class="restaurant-list">
        <div class="cards-container">
            <RestaurantCard
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             />
    </div>
     <!-- <button @click="openEventCreationPopup" class="create-event-button"> Create an Event </button> -->

</div>
</template>

<script>
import RestaurantCard from './RestaurantCard.vue';
//import EventCreationPopup from './EventCreationPopup.vue';
//import EventCreationConfirmation from './EventCreationConfirmation.vue';

export default {
    components: {
        RestaurantCard
    },
    props: {
        eventId: this.Number
    }
};
</script>

<style scoped>
.cards-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}
</style>