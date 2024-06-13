<template>
    <div class="page-container">
        <HeaderComp />
        <div class="create-event-container">
            <div class="card-front">
                <h1 class="title">Create Event</h1>
                <!-- <eventCreationPopup @eventCreated="handleEventCreated" /> -->
                <form class="event-form" v-on:submit.prevent="createEvent">
                    <input placeholder="Event Name" type="text" name="eventName" id="eventName" v-model="event.eventName">
                    <label class="decision-deadline" for="decisionDate">Decision Deadline </label>
                    <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="event.date">
                    <button class="submit-button" type="submit">Submit</button>
                </form>
            </div>
        </div>
        <Footer />
    </div>
</template>
<!-- create event should pull in info from the database and save it - main view for creating events, pulling initial r data from API and saving to database... -->
<script>
import RestaurantService from '@/services/RestaurantService';
import EventService from '@/services/EventService';
import HeaderComp from '../HeaderComp.vue';
import Footer from '../footer.vue';

export default {
    data() {
        return {
            event: {
                eventName: '',
                date: ''
            }
        };
    },
    props: ['location', 'restaurants'],
    methods: {
        createEvent() {
            // Create a new event...
            let newEvent = {
                eventName: this.event.eventName,
                location: this.location,
                decisionDate: this.event.date
            };
            console.log(newEvent);
            console.log(this.restaurants);
            // Send the event to the API
            let eventId = 0;
            RestaurantService.create(newEvent).then((response) => {
                if (response.status === 201) {
                    eventId = response.data.eventId;
                }
                EventService.addRestaurantsToEvent(eventId, this.restaurants).then((response) => {
                    if (response.status === 201) {
                        console.log("Restaurants added to the event.");
                        console.log(response.data);
                    }
                }).catch((error) => {
                    // this.handleErrorResponse(error, "Error associating restaurants with the event.");
                    console.log("Error associating restaurants with the event.");
                });
            }).catch((error) => {
                this.handleErrorResponse(error, "Error creating an event.");
                console.log("Error creating an event.");
            });
        }
    },
    components: { HeaderComp, Footer }
}
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