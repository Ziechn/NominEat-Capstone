<template>
    <div class="search-container">
        <HeaderComp />
        <RestaurantList />
    </div>
</template>

<script>
import HeaderComp from '@/components/HeaderComp';
import RestaurantList from '@/components/restaurant/RestaurantList.vue';

export default {
    components: {
        HeaderComp,
        RestaurantList
    }
};

</script> 
<!-- <template>
    <HeaderComp/>
    <div class="search-container">

        <RestaurantList :restaurants="restaurants"/>
 <div class="search-restaurants">
        <h2>Search Restaurants</h2>  
        <form @submit.prevent="searchRestaurants">
        <div>
        <label for="zipCode"></label>
        <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
        </div> -->
        <!-- <div>
        <label for="category"></label>
        <input type="text" v-model="category" placeholder="Filter by Category"/>
        </div> -->
            <!-- <div>
            <label for="limit"></label>
            <select v-model="limit" class="search-input"> 
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option> 
            </select>
        </div> -->
        <!-- <button type="submit">Search</button>
        </form> -->
        <!-- <div v-if="loading" class="loading">Loading...please wait...</div>
        <div v-if="!loading && restaurants.length" class="restaurant-cards"> 
            <RestaurantCard
            v-for="restaurant in restaurants" 
            v-bind:key="restaurant.id" 
            v-bind:restaurant="restaurant"/> 
         </div>
         <div v-if="!loading && !restaurants.length">
        No results found...
        </div>
        <button  @click="openEventCreationPopup" class="create-event-button">Create Event</button>
        <EventCreationPopup
        v-if="showEventCreationPopup"
        :restaurants="restaurants"
        @create="handleEventCreation"
        @close="closeEventCreationPopup"
        />
        <EventCreationConfirmation
        v-if="showEventCreationConfirmation"
        :eventLink="createdEventLink"
        @close="closeEventCreationConfirmation"
        />
        v-for="restaurant in filteredRestaurants" 
         <div v-if="selectedRestaurants.length">
            <h3>Selected Restaurants</h3>
                <ul>
                    <li v-for="restaurant in selectedRestaurants" :key="restaurant.id" >{{  restaurant.name }}
                     </li>
                </ul>
        </div>
    </div>
</template>

<script>
import { mapState,  mapActions } from 'vuex'; //, mapMutations
import RestaurantList from '../components/RestaurantList.vue';
import RestaurantCard from '@/components/RestaurantCard.vue';
import EventCreationPopup from '@/components/EventCreationPopup.vue';
import EventCreationConfirmation from '@/components/EventCreationConfirmation.vue';
import HeaderComp from '../components/HeaderComp.vue';


export default{
    components: {
    RestaurantList,
    RestaurantCard,
    EventCreationPopup,
    HeaderComp

},
    // data() {
    //     return {
    //         showEventCreationPopup: false,
    //         showEventCreationConfirmation: false,
    //         zipCode: '',
    //         // category: '',
    //         limit: 10
    //        //selectedRestaurants: [],
    //       //  hasSelected: false
    //     };
    // },
    computed: {
        ...mapState(['restaurants', 'loading']), //'selectedRestaurants'
    },
    methods: {
        ...mapActions(['fetchRestaurants']), //'createEvent', 'saveRestaurants'
        //...mapMutations(['SET_ZIP_CODE']), //'FILTER_BY_CATEGORY', 'SET_LIMIT', 'SET_CATEGORY'
        openEventCreationPopup() {
            this.showEventCreationPopup = true;
        },
        closeEventCreationPopup() {
            this.showEventCreationPopup = false
        },
        searchRestaurants() {
          this.SET_ZIP_CODE(this.zipCode);
        //   this.SET_CATEGORY(this.category);
        //   this.SET_LIMIT(this.limit);
        //   this.fetchRestaurants();
        this.fetchRestaurants({ zipCode: this.zipCode }); //limit: this.limit, category: this.category
                             },
                            // filterByCategory() {
                            //     this.FILTER_BY_CATEGORY(this.category);
                            // }
                            // toggleRestaurantSelection(restaurant) {
                            //     if (!this.selectedRestaurants.some(r => r.id === restaurant.id)) {
                            //         this.$store.commit('REMOVE_SELECTED_RESTAURANT', restaurant.id);
                            //         this.selectedRestaurants.push(restaurant);
                            //         this.hasSelected = true;
                            //     }else {
                            //         this.$store.commit('ADD_SELECTED_RESTAURANT', restaurant);
                            //     }
                            // },
                            //     this.createEvent(event);
                            // }
                        //     saveSelectedRestaurants() {
                        //         this.selectedRestaurants(this.selectedRestaurants);
                        // }
                    // },
        async handleEventCreation(eventData) {
            //this.$router.push({ name: 'createEvent', query: { zipCode: this.zipCode } });
            try {
                const response = await this.$store.dispatch('createEvent', eventData );
                this.createdEventLink = response.eventLink;
                this.showEventCreationConfirmation = true;
                    this.$router.push({ name: 'EventOrganizer', query: { eventLink: response.eventLink } });
            } catch(error) {
                console.log('Error creating event: ', error);
             }
            },
            closeEventCreationConfirmation() {
                this.showEventCreationConfirmation = false;
            }
        },
        created() {
        if(this.$store.state.zipCode) {
            this.zipCode = this.$store.state.zipCode;
            this.fetchRestaurants({ zipCode: this.zipCode });
                                     }
                 }
    };
</script> -->

<style scoped>
.search-restaurants {
    max-width: 800px;
    margin: auto;
    padding: 20px;
    background-color: var(--bg-100);
    color: var(--text-100);
    text-align: center;
}

.search-form {
    display: flex;
}


.search-input,
.search-button{
    margin: 5px 0;
    padding: 10px;
    border: 1px solid var(--bg-300);
    border-radius: 5px;
}

.search-input {
    width: calc(100% - 22px);
}

.search-form button {
    background-color: var(--primary-100);
    color: white;
    cursor: pointer;
}

.search-form:hover {
    background-color: var(--primary-200);
}

.search-input {
    width: 200px;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.submit:hover {
    background-color: var(--primary-200);
}

.loading {
    margin-top: 10px;
    color: var(--primary-100)
}

.restaurant-cards {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
    justify-content: center;
    }

    .no-results {
        color: var(--text-200);
    }
</style>  