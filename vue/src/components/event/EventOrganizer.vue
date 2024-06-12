<template>
  <div>
    Event Organizer Panel <br>
    <EventInformation v-bind:eventId="eventId" />
    <EventRestaurantList v-bind:eventId="eventId"  />
  </div>
</template>

<script>
import EventInformation from '@/components/event/EventInformation.vue';
import EventRestaurantList from '@/components/event/EventRestaurantList.vue';

export default {
    components: {
    EventInformation,
    EventRestaurantList
}
}
</script>

<style>

</style>