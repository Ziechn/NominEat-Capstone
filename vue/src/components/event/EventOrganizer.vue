<template>
  <div>
    Event Organizer Panel <br>
    <EventInformation v-bind:eventId="eventId" />
    <EmailEventLink v-bind:eventId="eventId"  />
    <EventRestaurantList v-bind:eventId="eventId"  />
  </div>
</template>

<script>
import EmailEventLink from '@/components/event/EmailEventLink.vue';
import EventInformation from '@/components/event/EventInformation.vue';
import EventRestaurantList from '@/components/event/EventRestaurantList.vue';

export default {
    components: {
    EventInformation,
    EmailEventLink,
    EventRestaurantList
}
};
</script>

<style>

</style>