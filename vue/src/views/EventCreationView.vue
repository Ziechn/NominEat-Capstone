<template>
    <div class="event-creation">
        <h2>Create Event</h2>
        <form @submit.prevent="createEvent">
    <div>
        <label for="eventName">Event Name:</label>
        <input type="text" v-model="eventName" required />
    </div>
    <div>
        <label for="eventDate">Event Date:</label>
        <input type="date" v-model="eventDate" required />
    </div>
    <div>
        <label for="eventTime">Event Time:</label>
        <input type="time" v-model="eventTime" required />
    </div>
    <div>
        <label for="rsvpDeadline">RSVP Deadline:</label>
        <input type="datetime-local" v-model="rsvpDeadline" required />
    </div>
    <div>
        <h3>Selected Restaurants</h3>
    <ul>
        <li v-for="restaurant in selectedRestaurant" :key="restaurant.id">
        {{ restaurant.name }}
            <button @click="removeRestaurant(restaurant.id)">Remove</button>
    </li>
    </ul>
    </div>
    <button type="submit">Create Event</button>
</form>
</div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';

export default {
    data() {
        return {
            eventName: '',
            eventDate: '',
            eventTime: '',
            rsvpDeadline: '',
        };
    },
    computed: {
        ...mapState([ 'selectedRestaurants'])
    },
    methods: {
        ...mapMutations(['REMOVE_SELECTED_RESTAURANT', 'CREATE_EVENT']),
        removeRestaurant(restaurantId) {
            this.REMOVE_SELECTED_RESTAURANT(restaurantId);
        },
        createEvent() {
            const event = {
                name: this.eventName,
                date: this.eventDate,
                time: this.eventTime,
                rsvpDeadline: this.rsvpDeadline,
                restaurants: this.selectedRestaurants
            };
            this.CREATE_EVENT(event);
            this.$router.push({ name: 'EventList' });
        }
    }
};
</script>

<style scoped>
</style>