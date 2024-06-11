<template>
    <HeaderComp/>
    <div class="search-restaurants">
        <h2>Search Restaurants</h2>  
        <form @submit.prevent="searchRestaurants">
        <div>
        <label for="zipCode"></label>
        <input type="text" v-model="zipCode" placeholder="Enter ZIP Code" />
        </div>
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
        <button type="submit">Search</button>
        </form>
        <div v-if="loading" class="loading">Loading...please wait...</div>
        <div v-if="!loading && restaurants.length" class="restaurant-cards"> 
            <RestaurantCard
            v-for="restaurant in restaurants" 
            v-bind:key="restaurant.id" 
            v-bind:restaurant="restaurant"/> 
            <!--@selectRestaurant="selectRestaurant-->
         </div>
         <div v-if="!loading && !restaurants.length">
        No results found...
        
        </div>
        <!-- v-for="restaurant in filteredRestaurants"  -->
        <!-- <div v-if="selectedRestaurants.length">
            <h3>Selected Restaurants</h3>
                <ul>
                    <li v-for="restaurant in selectedRestaurants" :key="restaurant.id" >{{  restaurant.name }}
                     </li>
                </ul>
                <button @click="createEvent">Create Event</button>
        </div> -->
      
    </div>
</template>

<script>
import { mapState,  mapActions, mapMutations } from 'vuex'; //
import RestaurantCard from '@/components/RestaurantCard.vue';
import HeaderComp from '../components/HeaderComp.vue';


export default{
    components: {
    RestaurantCard,
    HeaderComp
},
    data() {
        return {
            zipCode: '',
            // category: '',
            // limit: 10
           //selectedRestaurants: [],
          //  hasSelected: false
        };
    },
    computed: {
        ...mapState(['restaurants', 'loading']), //'selectedRestaurants'
    },
    methods: {
        ...mapActions(['fetchRestaurants']), //'createEvent', 'saveRestaurants'
        ...mapMutations(['SET_ZIP_CODE']), //'FILTER_BY_CATEGORY', 'SET_LIMIT', 'SET_CATEGORY'
        // updateZipCode(event) {
        //     this.SET_ZIP_CODE(event.type.value);
        // },
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
    },
        // toggleRestaurantSelection(restaurant) {
        //     if (!this.selectedRestaurants.some(r => r.id === restaurant.id)) {
        //         this.$store.commit('REMOVE_SELECTED_RESTAURANT', restaurant.id);
        //         this.selectedRestaurants.push(restaurant);
        //         this.hasSelected = true;
        //     }else {
        //         this.$store.commit('ADD_SELECTED_RESTAURANT', restaurant);
        //     }
        // },
        // createEvent() {
        //     const event = {
        //         eventName: 'My Event',
        //         location: this.zipCode,
        //         decisionDate: new Date().toISOString(),
        //         restaurants: this.searchRestaurants
        //     };
        //     this.createEvent(event);
        // }
    //     saveSelectedRestaurants() {
    //         this.selectedRestaurants(this.selectedRestaurants);
    // }
   // },
   created() {
        if(this.$store.state.zipCode) {
            this.zipCode = this.$store.state.zipCode;
            this.fetchRestaurants({ zipCode: this.zipCode });
    }
//        this.fetchRestaurants(); OR //this.searchByZipCode();
}
    
};
</script>

<style scoped>
.search-restaurants {
    max-width: 800px;
    margin: auto;
    padding: 20px;
    background-color: var(--bg-100);
    color: var(--text-100);
    text-align: center;
    padding-top: 70px;
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