<template>
  <div class="voting-stats">
   <h2> Restaurant Name: {{ restaurant.name }} </h2> <br>
   <p> Restaurant Yes Votes: {{ yesVotes }}</p> <br>
   <p> Restaurant No Votes: {{ noVotes }}</p>
  </div>
</template>

<script>
import EventService from '../../services/EventService';

export default {
    props: ['restaurantId', 'eventId'],
    data(){
        return {
            tempRestaurantId: 'A123',
            tempEventId: 1,
            restaurantName: '',
            yesVotes: -420,
            noVotes: -420,
            restaurant: {}
        };
    },
    methods: {
        getRestaurantName(){
            EventService.getRestaurantById(this.tempRestaurantId).then(
                (response) => {
                    if (response.status === 200) {
                        this.restaurant = response.data;
                    }
                }
            ).catch(
                (error) => {
                    console.log("Error getting restaurant for ID: " + this.restaurantId + error);
                }
            ); 
        },
        fetchVotes() {
            EventService.getYesVote(this.tempEventId, this.tempRestaurantId).then(response => {
                    if (response.status === 200) {
                        this.yesVotes = response.data;
                    }  
                }).catch(
                    (error) => {
                        console.log("Error getting yes vote for restaurant ID: " + this.restaurantId + " for event ID: " + this.eventId);
                    }
                ); 
                EventService.getNoVote(this.tempEventId, this.tempRestaurantId).then(response => {
                    if (response.status === 200) {
                        this.noVotes = response.data;
                    }
                }).catch(
                    (error) => {
                        console.log("Error getting no vote for restaurant ID: " + this.restaurantId + " for event ID: " + this.eventId);
                    }
                );
            }
        },
        watch: {
            eventId: 'fetchVotes',
            restaurantId: 'fetchVotes'
        },
        created() {
            this.getRestaurantName();
            this.fetchVotes();
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
