<template>
  <div>
    <HeaderComp />
    <div class="create-event" >
    <!-- <h3>Create your NominEat event</h3>
    <eventCreationPopup @eventCreated="handl
        <h2>Create your NominEat event</h2>   
        eEventCreated" /> -->
        <h2>Create your NominEAT event</h2>
    <form @submit.prevent="createEvent"> 
        <input v-model="eventName" placeholder="Event Name" required />
        <input v-model="decisionDate"  id="decisionDate" placeholder="decisionDate" required />
        <input v-model="location" placeholder="location" required />
        <button type="submit">Submit</button>

        <!-- <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="event.date"> -->
        <!-- <label for="eventName">Event Name: </label>
        <input type="text" name="eventName" id="eventName" v-model="event.eventName">
        <label for="decisionDate">Decision Date: </label>
        <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="event.date">
        <button type="submit">Submit</button> -->
    </form>
  </div>

<div v-if="eventCreated">
    <h3>Restaurants for Event: {{ eventName }}</h3>
    <RestaurantCardForOrganizer v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" :eventId="eventId" />
    <!-- <RestaurantCard v-for="restaurant in restaurants" :key="restaurant.id" :restaurant="restaurant" /> -->
</div>
</div>
</template>
<!-- create event should pull in info from the database and save it - main view for creating events, pulling initial r data from API and saving to database... -->
<script>
import HeaderComp from '@/components/HeaderComp.vue';
//import RestaurantService from '@/services/RestaurantService.js';
import RestaurantCardForOrganizer from '@/components/restaurant/RestaurantCardForOrganizer.vue';
import { mapActions, mapState } from 'vuex';
//import EventService from '@/services/EventService';

export default {
    components: { HeaderComp, RestaurantCardForOrganizer },
    data() {
        return {
                eventName: '',
                location: '',
                decisionDate: '',
                eventCreated: false,
                eventId: null
            };
    },
    // props: ['location', 'restaurants'],

    computed: {
        ...mapState(['restaurants'])
    },
    methods: {
        ...mapActions(['createEvent', 'associateRestaurantsWithEvent']),
        createEvent() {
                        // Create a new event...
                        //let newEvent = {
              this.createEvent( {
                name: this.eventName,
                location: this.location,
                decisionDate: this.decisionDate
            }).then(response => {
                this.eventId = response.id;
                this.eventCreated = true;
                this.associateRestaurantsWithEvent({ eventId: this.eventId, restaurants: this.restaurants.slice(0, 10) });
            });

            // console.log(newEvent);
            // console.log(this.restaurants);

            // // Send the event to the API
            // let eventId = 0;
            
            // RestaurantService.create(newEvent).then(
            //     (response) => {
            //         if (response.status === 201) {
            //             eventId = response.data.eventId;
            //         }

            //     EventService.addRestaurantsToEvent(eventId, this.restaurants).then(
            //         (response) => {
            //             if (response.status === 201) {
            //                 console.log("Restaurants added to the event.");
            //                 console.log(response.data);
            //             }
            //         }
            //     ).catch(
            //         (error) => {
            //             // this.handleErrorResponse(error, "Error associating restaurants with the event.");
            //             console.log("Error associating restaurants with the event.")
            //         }
            //     );
            // }).catch(
            //     (error) => {
            //         this.handleErrorResponse(error, "Error creating an event.");
            //         console.log("Error creating an event.")
            //     }
           // );
        }
    }
};
</script>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.create-event-container {
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 50px;
}

.card-front {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 30%;
  height: 425px;
}

.event-form {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.submit-button {
    margin-top: 30px;
    margin-bottom: 30px;
}

.decision-deadline {
    padding-top: 10px;
    padding-bottom: 5px;
    color: #5c5c5c;
    font-weight: bold;
}

/* .title {
    margin-bottom: 30px;
} */
</style>