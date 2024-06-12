<template>
    <div class="voting-stats-wrapper">
        <div class="voting-stats">
            <h3 class="restaurant-name"> Restaurant Name: {{ restaurant.name }} </h3>
            <p> Restaurant Yes Votes: {{ yesVotes }}</p>
            <p> Restaurant No Votes: {{ noVotes }}</p>
        </div>
    </div>
</template>

<script>
import EventService from '../../services/EventService';

export default {
    props: ['restaurantId', 'eventId'],
    data() {
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
        getRestaurantName() {
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
<style scoped>

.voting-stats-wrapper{
    background-color: gray;
    margin-right: 40px;
    border-radius: 10px;
}
.voting-stats {
    display: flex;
    align-items: center;
    width: 100%;
    border-radius: 10px;
}

.restaurant-name {
    flex-grow: 1;
}
</style>

