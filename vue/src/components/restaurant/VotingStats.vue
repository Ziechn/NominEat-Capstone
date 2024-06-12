<template>
  <div class="voting-stats">
   <h2> Restaurant Name: {{ restaurantName }} </h2> <br>
   <p> Restaurant Yes Votes: {{ yesVotes }}</p> <br>
   <p>  Restaurant No Votes: {{ noVotes }}</p>
  </div>
</template>

<script>
import EventService from '../../services/EventService';

export default {
    props: ['restaurantId', 'eventId'],
    data(){
        return {
            restaurantName: '',
            yesVotes: 0,
            noVotes: 0
        };
    },
    methods: {
        getRestaurantName(){
            // Create an event in the RestaurantService to reach out to a new endpoint to get the restaurant name.
        },
        fetchVotes() {
            EventService.getYesVote(this.eventId, this.restaurantId).then(response => {
                   // if (response.status === 200) {
                        this.yesVotes = response.data;
                    }); 
                    EventService.getNoVote(this.eventId, this.restaurantId).then(response => {
                    //if (response.status === 200) {
                        this.noVotes = response.data;
                    });
                }
            },
            watch: {
                eventId: 'fetchVotes',
                restaurantId: 'fetchVotes'
            }
        };
</script>
<style>
</style>

            <!-- catch
                (error)  {
                    console.log("Problem getting no votes for restaurant ID: " + this.restaurantId + " in event ID: " + this.eventId);
            }
             -->
