<template>
   <div class="voting-container">

       <div class="card-deck">
                 <RestaurantCard
                  v-for="(restaurant, index) in restaurantDeck" 
                  v-bind:key="restaurant.id" 
                  v-bind:restaurant="restaurant"
                  v-bind:showVoteButtons="index === currentIndex"
                  @vote="handleVote"
                  />
                  </div>
                    <div class="results" v-if="showResults && isOrganizer">
                        <h3>Voting Complete!</h3>
                        <ul>
                            <li v-for="vote in vote" :key="vote.restaurant">
                                Restaurant ID: {{ vote.restaurant }} - Vote : {{  vote.vote }}
                            </li>
                        </ul>
                        <div class="stats">
                            <p>Total Votes: {{  votes.length  }}</p>
                            <p>Thumbs Up: {{ thumbsUpCount }}</p>
                            <p>Thumbs Down: {{ thumbsDownCount }}</p>
                    </div>
                </div>
                    <div v-if="showResults && isOrganizer">
                    <h3>Thank you for voting!</h3>
                    <p>Your event organizer will reach out with the results soon.</p>
                </div>
        </div> 
</template>

<script>
import { mapState,  mapActions } from 'vuex';
import RestaurantCard from '@/components/RestaurantCard.vue';
import EventService from '@/services/EventService.js';

export default{
    components: {
        RestaurantCard,
    },
    data(){
        return {
            votes: [],
            currentIndex: 0,
            showResults: false,
            eventLink: null,
            event: null,
            isOrganizer: false
        };    
    },
        computed: {
                        //map restaurants from store and loading async
      ...mapState(['restaurants', 'user']), 
                        //get restaurantDeck could also add in progress bar here? not MVP
        restaurantDeck() {
            return this.restaurants.slice(this.currentIndex);
       },
       currentRestaurant() {
        return this.restaurants[this.currentIndex];
       },
       thumbsUpCount() {
        return this.votes.filter(vote => vote.vote === 'up').length;
       },
       thumbsDownCount(){
        return this.votes.filter(vote => vote.vote === 'down').length;
       }
    },
    methods: {
     ...mapActions(['setRestaurants']),
        async fetchEventData() {
            try {
            const response = await EventService.getEventByLink(this.eventLink);
            this.event = response.data;
            this.isOrganizer = this.event.organizer_id === this.user.id;
            const restaurantResponse = await EventService.getRestaurantsForEvent(this.event.id);
            this.setRestaurants(restaurantResponse.data);
        } catch (error) {
            console.error('Error fetching event data: ', error);
        }
    },
    async handleVote(voteType) {
        const restaurantId = this.currentRestaurant.id;
        if (voteType === 'up') {
            await EventService.addYesVote(this.event.id, restaurantId);
        } else {
            await EventService.addNoVote(this.event.id, restaurantId);
        }
        this.votes.push({ restaurant: restaurantId, vote: voteType });
        this.currentIndex++;
        if (this.this.currentIndex >= this.restaurants.length) {
            this.showResults = true;
        }
     }
    },
    created() {
        this.eventLink = this.$route.query.eventLink;
        this.fetchEventData;
    }
    };
</script>

<style scoped>
.voting-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}
.card-deck{
    position: relative;
    width: 300px;
    height: 400px;
    margin-bottom: 20px;
}

.results {
    text-align: center;
}

.stats {
    margin-top: 20px;
}

.stats p {
    margin: 5px 0;
}


</style>
