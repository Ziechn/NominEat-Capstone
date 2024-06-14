<template>
  <div>
    <div class="create-event" >
        <div>
            <h2>Create Your Event</h2>
        </div>
        <form v-on:submit.prevent="createEvent"> 
            <input  v-model="eventName" placeholder="Event Name" required />
            <input type="datetime-local" v-model="decisionDate"  id="decisionDate" required />
            <button type="submit">Submit</button>
        </form>
  </div>
</div>
</template>

<!-- create event should pull in info from the database and save it - main view for creating events, pulling initial r data from API and saving to database... -->
<script>
import EventService from '@/services/EventService';

export default {
    data() {
        return {
                eventName: '',
                decisionDate: '',
            };
    },
    props: ['restaurants', 'location'],
    methods: {
        createEvent() {
            // Create a new event...
            let newEvent = {
                eventName: this.eventName,
                decisionDate: this.decisionDate,
                location: this.$store.state.zipCode
            }

            console.log(newEvent);
            console.log(this.restaurants);

            let eventId = 0;
            
            EventService.createEvent(newEvent).then(
                (response) => {
                    if (response.status === 201) {
                        console.log(response.data);
                        eventId = response.data.eventId;
                    }

                EventService.addRestaurantsToEvent(eventId, this.restaurants).then(
                    (response) => {
                        console.log(response.status);
                        if (response.status === 201) {
                            console.log("Restaurants added to the event.");
                            console.log(response.data);
                            this.$router.push({name: 'profile'});
                        }
                    }
                ).catch(
                    (error) => {
                        console.log("Error associating restaurants with the event.")
                    }
                );
            }).catch(
                (error) => {
                    console.log("Error creating an event.")
                }
           );
        }
    }
};
</script>

<style scoped>
.create-event {
    background-color: var(--bg-200);
    border: 1px solid #ccc;
    margin-right: 500px;
    margin-left: 500px;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

form{
    padding-bottom: 40px;
}

input {
    margin-right: 20px;
}

/* .title {
    margin-bottom: 30px;
} */
</style>