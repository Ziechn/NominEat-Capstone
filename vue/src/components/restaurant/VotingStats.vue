<template>
  <div class="voting-stats">
    <div class="vote-wrapper yes-votes">
      <p class="votes yes-votes">{{ yesVotes }}</p>
    </div>
    <div class="vote-wrapper no-votes">
      <p class="votes no-votes">{{ noVotes }}</p>
    </div>
  </div>
</template>

<script>
import EventService from "../../services/EventService";

export default {
  props: ["restaurantId", "eventId"],
  data() {
    return {
      yesVotes: -420,
      noVotes: -420,
    };
  },
  methods: {
    fetchVotes() {
      console.log("Restaurant ID: " + this.restaurantId);
      console.log("Event ID: " + this.eventId);
      EventService.getYesVote(this.eventId, this.restaurantId)
        .then((response) => {
          if (response.status === 200) {
            this.yesVotes = response.data;
          }
        })
        .catch((error) => {
          console.log(
            "Error getting yes vote for restaurant ID: " +
              this.restaurantId +
              " for event ID: " +
              this.eventId
          );
        });
      EventService.getNoVote(this.eventId, this.restaurantId)
        .then((response) => {
          if (response.status === 200) {
            this.noVotes = response.data;
          }
        })
        .catch((error) => {
          console.log(
            "Error getting no vote for restaurant ID: " +
              this.restaurantId +
              " for event ID: " +
              this.eventId
          );
        });
    },
  },
  watch: {
    eventId: "fetchVotes",
    restaurantId: "fetchVotes",
  },
  created() {
    this.fetchVotes();
  },
};
</script>

<style scoped>
.voting-stats {
  display: flex;
  justify-content: end;
  width: 45%;
  flex-grow: 1;
  padding-right: 20px;
}

.vote-wrapper {
  padding-left: 25px;
  border-radius: 50%;
  width: 40px;
}

.votes {
  font-weight: bold;
  color: var(--bg-200);
  padding: 10px;
  text-align: center;
}

.yes-votes .votes {
  background-color: rgb(43, 116, 43);
  border-radius: 50%;
  border: 1px solid #288a24;
}

.no-votes .votes {
  background-color: rgb(196, 20, 20);
  border-radius: 50%;
  border: 1px solid #a74545;
}
</style>