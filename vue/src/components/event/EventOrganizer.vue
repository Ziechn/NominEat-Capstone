<template>
  <div>
    Event Organizer Panel <br />
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