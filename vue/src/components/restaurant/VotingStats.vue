<template>
    <div class="voting-stats-wrapper">
        <div class="voting-stats">
            <h3 class="restaurant-name">{{ restaurant.name }} </h3>
            <div class="vote-wrapper yes-votes">
            <p class="votes yes-votes">{{ yesVotes }}</p>
            </div>
            <div class="vote-wrapper no-votes">
            <p class="votes no-votes">{{ noVotes }}</p>
            </div>
        </div>
    </div>
</template>

<script>
import EventService from "../../services/EventService";

export default {
  props: ["restaurantId", "eventId"],
  data() {
    return {
      restaurantName: "",
      yesVotes: -420,
      noVotes: -420,
      restaurant: {},
    };
  },
  methods: {
    getRestaurantName() {
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
    fetchVotes() {
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
    this.getRestaurantName();
    this.fetchVotes();
  },
};
</script>

<style scoped>
.voting-stats-wrapper {
    background-color: var(--bg-200);
    border: 1px solid #ccc;
    margin-right: 40px;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 10px;
}

.voting-stats {
    display: flex;
    align-items: center;
    width: 95%;
}

.restaurant-name {
    flex-grow: 1;
    padding-left: 22px;
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