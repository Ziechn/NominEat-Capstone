<template>
  <div>
    Restaurant Name: {{ restaurantName }} <br>
    Restaurant Yes Votes: {{ yesVotes }} <br>
    Restaurant No Votes: {{ noVotes }}
  </div>
</template>

<script>
import EventService from '../services/EventService';

export default {
    props: ['restaurantId', 'eventId'],
    data(){
        return {
            restaurantName: '',
            yesVotes: 0,
            noVotes: 0
        }
    },
    methods: {
        getRestaurantName(){
            // Create an event in the RestaurantService to reach out to a new endpoint to get the restaurant name.
        },
        getRestaurantYesVotes(){
            EventService.getYesVote(this.eventId, this.restaurantId).then(
                (response) => {
                    if (response.status === 200) {
                        this.yesVotes = response.data;
                    }
                }
            ).catch(
                (error) => {
                    console.log("Problem getting yes votes for restaurant ID: " + this.restaurantId + " in event ID: " + this.eventId);
                }
            );
        },
        getRestaurantNoVotes() {
            EventService.getNoVote(this.eventId, this.restaurantId).then(
                (response) => {
                    if (response.status === 200) {
                        this.noVotes = response.data;
                    }
                }
            ).catch(
                (error) => {
                    console.log("Problem getting no votes for restaurant ID: " + this.restaurantId + " in event ID: " + this.eventId);
                }
            );
        }

    }
}
</script>

<style>

</style>