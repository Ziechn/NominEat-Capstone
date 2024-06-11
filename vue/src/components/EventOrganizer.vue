<template>
  <div>
    Event Organizer Panel <br>
    <EventInformation v-bind:eventId="eventId" />
    <EmailEventLinkVue v-bind:eventId="eventId"  />
    <EventOrganizerList v-bind:eventId="eventId"  />
  </div>
</template>

<script>
import EmailEventLinkVue from './EmailEventLink.vue';
import EventInformation from './EventInformation.vue';
import EventOrganizerList from './EventOrganizerList.vue';

export default {
    components: {
        EventInformation,
        EventOrganizerList,
        EmailEventLinkVue
    }
}
</script>

<style>

</style>