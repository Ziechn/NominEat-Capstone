<!-- displays details of r for event organizer and includes voting stats -->

<template>
    <div class="voting-stats-wrapper">
        <h3>{{ restaurant.name }}</h3>
        <!-- <p>{{ restaurant.type }}</p> -->
        <!-- <p>{{ restaurant.address1 }}</p> -->
        <!-- <p>{{ restaurant.hours }}</p> -->
        <!-- <VotingStats :eventId="eventId" :restaurantId="restaurant.id" /> -->
       <VotingStats v-bind:eventId="passthroughEventId" v-bind:restaurantId="passthroughRestaurantId" />
    </div>
</template>

<script>
import VotingStats from "@/components/restaurant/VotingStats.vue";
import EventService from "@/services/EventService";

export default {
  data() {
    return {
      restaurant: {},
      passthroughRestaurantId: "",
      passthroughEventId: -420,
    };
  },
  components: {
    VotingStats,
  },
//   props: ["eventId", "restaurant"],
  props: ["eventId", "restaurantId"],
  methods: {
    setIds() {
      this.passthroughEventId = this.eventId;
      this.passthroughRestaurantId = this.restaurantId;
    },
    getRestaurant() {
      EventService.getRestaurantById(this.restaurantId)
        .then((response) => {
          if (response.status === 200) {
            this.restaurant = response.data;
          }
        })
        .catch((error) => {
          console.log(
            "Error getting restaurant for ID: " + this.restaurantId + error
          );
        });
    },
  },
  created() {
    this.getRestaurant();
    this.setIds();
  },
};
</script>

<style scoped>
.voting-stats-wrapper {
    background-color: var(--bg-200);
    border: 1px solid #ccc;
    margin-right: 20px;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 10px;
    display: flex;
    align-items: center;
}

h3 {
    flex-grow: 1;
    padding-left: 22px;
    width: 50%;
}

p {
    flex-grow: 1;
    padding-left: 22px;
    width: 25%;
}
</style>