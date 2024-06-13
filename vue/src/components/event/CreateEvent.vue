<template>
  <div>
    <HeaderComp />
    <div class="create-event" >
        <h2>Create your NominEAT event</h2>
        <form v-on:submit.prevent="createEvent"> 
            <input v-model="eventName" placeholder="Event Name" required /> {{ eventName }}
            <input type="datetime-local" v-model="decisionDate"  id="decisionDate" required /> {{ decisionDate }}
            <button type="submit">Submit</button>
        </form>
  </div>
</div>
</template>

<!-- create event should pull in info from the database and save it - main view for creating events, pulling initial r data from API and saving to database... -->
<script>
import HeaderComp from '@/components/HeaderComp.vue';
import EventService from '@/services/EventService';

export default {
    components: { HeaderComp},
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