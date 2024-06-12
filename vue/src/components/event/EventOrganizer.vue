<template>
  <div>
    Event Organizer Panel <br>
    <EventInformation v-bind:eventId="eventId" />
    <EventRestaurantList v-bind:eventId="eventId"  />
    <VotingStats />
  </div>
</template>

<script>
import EventInformation from '@/components/event/EventInformation.vue';
import EventRestaurantList from '@/components/event/EventRestaurantList.vue';
import VotingStats from '../restaurant/VotingStats.vue';

export default {
    components: {
    EventInformation,
    EventRestaurantList,
    VotingStats
}
}
</script>

<style>

</style>