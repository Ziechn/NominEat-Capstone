<!-- pulls and displays r data for a specific event from database used for voting  -->


<template> 
       
 <div class="event-restaurant-list">
    <h2 class="title">NominEat</h2>
    <p class="memo">You have been invited to share a meal!</p>
    <h1 class="voting-title"> Place your restaurant choices! </h1>

    <div class="cards-container">
    <RestaurantCardForVoting
             v-for="restaurant in restaurants"
             :key="restaurant.id"
             :restaurant="restaurant"
             :eventId="eventId"
             :class="[ 'carousel-item', 'shuffle-in' ]"
             :style="{ '--shuffle-order': index }"
                @animationend="animationEnd"
             />
            </div>      
 </div>
</template>

<script>
import EventService from '@/services/EventService';
import RestaurantCardForVoting from '@/components/restaurant/RestaurantCardForVoting.vue'
//import { mapActions, mapGetters } from 'vuex';

export default {
    components: {
        RestaurantCardForVoting
    },

// props: 
//         ['eventId'],

            data() {
        return {
            eventId: 1,
            restaurants: [],
            eventLink: '28e2e4b3-2bca-40bc-a876-6eeff60a52b9'
        };

    },
    // mounted() {
    //     this.fetchEventRestaurants();
    // },

// computed: {
//     ...mapGetters(['getRestaurants']),
//     restaurants() {
//     return this.getRestaurants;
//     }
// },
    
        

    // },
    created() {
        console.log(this.$route.params.eventLink);
        this.fetchEventLink();
    },
    methods: {

       fetchEventRestaurants(frog) { 
            EventService.getRestaurantsForEvent(frog).then(response => {

                this.restaurants = response.data
            }).catch (error => {
                console.error('error getting restaurants: ', error);
          });   
        },

        fetchEventLink() {
            EventService.getEventByLink(this.$route.params.eventLink).then(response => {
                this.eventId = response.data.eventId
                this.fetchEventRestaurants(this.eventId)
            }).catch (error => {
                console.error('error getting link: ', error);
            });
        },
        animationEnded() {
            this.fallIn = false;
        }
    }


};
</script>

<style scoped>

.title {
    font-size: xx-large;
    padding-left: 30px;
    padding-top: 30px;
}

.memo {
    text-align: center;
}
.voting-title {
    color: var(--accent-200);
    text-align: center;
    padding-bottom: 1vh;
}

.event-restaurant-list {
    padding: 20px;
    overflow: hidden;
    

}
.cards-container {
    display: flex;
    overflow-x: auto;
    scroll-snap-type: x mandatory;
    scroll-behavior: smooth;
    -webkit-overflow-scrolling: touch;
}
.carousel-item {
    flex: 0 0 auto;
    scroll-snap-align: center;
    margin: 35px 35px; 
    transition: transform 0.3s;
}

.carousel-item:hover {
    transform: scale(1.05);
}

.shuffle-in {
    animation: shuffle-in 1s ease-out;
    animation-delay: calc(var(--shuffle-order) * 0.1s);
}

@keyframes shuffle-in {
    0% {
        opacity: 0;
        transform: rotate(-30deg) translateY(-300px) translateX(100px);
    }
    50% {
        opacity: 0.7;
        transform: rotate(0deg) translateY(-100px) translateX(0px);
    }
    100% {
        opacity: 1;
        transform: rotate(0deg) translateY(0) translateX(0);
    }
}


</style>