<!-- event organizer should not be importing the event organizer list it should be importing the restaurant card for organizer...-->

<template>
  <div>
    <EventInformation />
    <EventOrganizerList />
  </div>
</template>
  
<script>
import EventInformation from "@/components/event/EventInformation.vue";
import EventOrganizerList from "./EventOrganizerList.vue";
import EventService from "../../services/EventService";

export default {
  components: {
    EventInformation,
    EventOrganizerList,
  },
  data() {
    return {
      event: {
        eventId: -420,
        eventName: "",
        location: "",
        decisionDate: "",
        eventLink: "",
      },
    };
  },
  methods: {
    getEventInfo() {
      EventService.getEventByUserId()
        .then((response) => {
          this.event = response.data;
        })
        .catch((error) => {
          console.error("Error fetching event data:", error);
        });
    },
  },
  created() {
    this.getEventInfo();
  },
};
</script>

<style scoped>
div {
  margin-bottom: 40px;
}
</style>