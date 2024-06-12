<template>
  <div>
    Event Organizer Panel <br>
    <EventInformation v-bind:eventId="eventId" />
    <EventOrganizerList/>
  </div>
</template>

<script>
import EventInformation from '@/components/event/EventInformation.vue';
import EventOrganizerList from '@/components/event/EventOrganizerList.vue';


export default {
    components: {
    EventInformation,
    EventOrganizerList
}
}
</script>

<style>

</style>