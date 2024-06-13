<template>
    <div class="restaurant-card">
        <h3>{{  restaurant.name }}</h3>
        <p>{{  restaurant.type }}</p>
        <p>{{  restaurant.address1 }}</p>
        <VoteButtons :eventId="eventId" :restaurantId="restaurant.id"  />


    </div>
</template>

<script>
import VoteButtons from '@/components/restaurant/VoteButtons.vue';

export default {
    components: { VoteButtons },
    props: 
        ['restaurant', 'eventId']

};

</script>
<style scoped>
.restaurant-card {
    perspective: 1000px; 
    width: 300px;
    height: 400px;
    margin: 20px;
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    display: grid;
    place-items: center;
}
</style>