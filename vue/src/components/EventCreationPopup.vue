<template>
    <div class="event-creation-popup">
        <div class="popup-content">
        <h2>Create Event</h2>
            <input v-model="eventName" placeholder="Event Name" />
            <input v-model="eventLocation" placeholder="Event Address" />
            <input v-model="decisionDeadline" type="datetime-local" placeholder="RSVP date" />
            <button @click="createEvent" type="submit">Create Event</button>
            <button @click="$emit('close')">Cancel</button>
</div>
</div>
</template>

<script>
//import { mapActions } from 'vuex';

export default {
    data() {
        return {
            eventName: '',
            eventLocation: '',
            decisionDeadline: '',
        };
    },
    methods:{
        async createEvent() {
            try {
            const eventData = {
            eventName: this.eventName,
            eventLocation: this.eventLocation,
            decisionDeadline: this.decisionDeadline,
            restaurants: this.$props.restaurants.map(r => r.id),
        };
            this.$emit('create', eventData);
            this.$emit('close');
                        // this.$router.push({ eventName: 'voting' });
         } catch (error) {
            console.log('Error creating event: ', error);
         }
        },
    },
    props: ['restaurants']
};
</script>

<style scoped>
.event-creation-popup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: var(--bg-100);
    color: var(--text-100);
    justify-content: center;
    align-items: center;
}

.popup-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
}


</style>