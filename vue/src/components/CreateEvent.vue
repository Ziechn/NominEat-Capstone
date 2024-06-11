<template>
  <div>
    Zip Code: {{ zipCode }} <br>
    Restaurants: {{ restaurants }}
    <h3>Create your NominEat event</h3>
    <form v-on:submit:prevent="createEvent">
        <label for="eventName">Event Name: </label>
        <input type="text" name="eventName" id="eventName" v-model="eventName">
        {{ eventName }}
        <label for="decisionDate">Decision Date: </label>
        <input type="datetime-local" name="decisionDate" id="decisionDate" v-model="date">
        {{ date }}
        <input type="submit">
    </form>
  </div>
</template>

<script>
import { mapState,  mapActions, mapMutations } from 'vuex'; //

export default {
    data() {
        return {
            eventName: '',
            date: ''
        }
    },
    props: {
        restaurants: []
    },
    methods: {
        createEvent() {
            window.alert("Event: " + this.eventName + " created! Decision time is: " + this.date);
        }
    },
    create: {
        setLocation() {
            location = this.$store.state.zipCode;
        }
    }
}
</script>

<style>

</style>