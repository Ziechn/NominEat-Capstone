// MVP: hours of operation - store and card(back) need, open and closed data -store needs card has, call to order store and card(back) need
// Nice to Haves: Num of stars, map, takeout.delivery option shown 
import { createStore as _createStore } from 'vuex';
import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from "vuex-persistedstate";
import RestaurantService from '../services/RestaurantService';

//initial backup data (comment in and out dont delete)
const backupData = [
  {
    id: 1,
    name: 'East Village Pizza',
    zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    catagories: { title: 'Pizza' },
    category: 'Pizza',
    rating: 8,
    price: '$$',
    address1: '1234 Pizza St, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.eastvillagepizza.com/menu',
    hours: '6AM - 9PM',
    status: 'Open',
  },
  {
    id: 2,
    name: 'Sushi Place',
    zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    catagories: { title: 'Sushi' },
    category: 'Sushi',
    rating: 9,
    price: '$$$',
    address1: '678 Sushi Place, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.sushiplace.com/menu',
    hours: '6AM - 9PM',
    status: 'Open',
  },
  {
    id: 3,
    name: 'Burger House',
    zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    catagories: { title: 'Burgers' },
    category: 'Burgers',
    rating: 7,
    price: '$$',
    address1: '1234 Burger Blvd, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.burgerhouse.com/menu',
    hours: '6AM - 9PM',
    status: 'Open',
    // coordinates;
  }

];

const store = _createStore({
  state: {
    zipCode: '',
    limit: 3,
    //limit should be 10 for final? or maybe removed - 
    // uncomment for API data
    // restaurants: [],
    // filteredRestaurants: [],

    //uncomment for backup data
    restaurants: backupData,
    filteredRestaurants: backupData,
    events: [],
    loading: false,
    selectedRestaurants: [],
    token: localStorage.getItem('token') || '',
    user: JSON.parse(localStorage.getItem('user')) || {}
  },
  mutations: {
    SET_ZIP_CODE(state, zipCode) {
      state.zipCode = zipCode;
    },
    SET_LIMIT(state, limit) {
      state.limit = limit;
    },
    SET_RESTAURANTS(state, restaurants) {
      state.restaurants = restaurants;
      state.filteredRestaurants = restaurants;
    },
    SET_LOADING(state, loading) {
      state.loading = loading;
    },
    ADD_SELECTED_RESTAURANTS(state, restaurant) {
      state.selectedRestaurants.push(restaurant);
    },
    SET_EVENTS(state, events) {
      state.events = events;
    },
    ADD_EVENT(state, event) {
      state.event.push(event);
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    FILTER_BY_CATEGORY(state, category) {
      if (category === '') {
        state.filteredRestaurants = state.restaurants;
      } else if (category) {
        state.filteredRestaurants = state.restaurants.filter(restaurant =>
          restaurant.catagories.some(cat =>
            cat.title.toLowerCase().includes(category.toLowerCase()))
            );
    } else { 
      state.filteredRestaurants = state.restaurants;
      }
    }
  },
  actions: {
    async fetchRestaurants({ commit, state }, { zipCode, category , limit }) {
      commit('SET_LOADING', true);
      try {
        //uncomment for fake timer on data
        setTimeout(() => {
        const { zipCode, category, limit } = state
        //uncomment for API data
        // const response = await RestaurantService.list(zipCode, limit);
        // commit('SET_RESTAURANTS', response.data)

        // const response = { data: createStore };
        // const response = { data: backupData};

       //uncomment for backup data
    
        commit('SET_RESTAURANTS', backupData);


        commit('SET_LOADING', false); } , 900);

        //fake api call here
        // const response = { data: backupData };
        //commit('SET_RESTAURANTS', response.data);

        //const response = { data: createStore };
        //commit('SET_RESTAURANTS', response);

      }
     catch (error) {
        console.error('Error fetching restaurants: ', error);
    //  } finally {
        commit('SET_LOADING', false);
      }
    },
    async createEvent({ commit }, event) { 
      try {
        const response = await axios.post('event/create', event);
        commit('ADD_EVENT', response.data);
      } catch (error) {
        console.error('Error creating event', error);
      }
    },
    async saveRestaurant({ commit }, restaurants) { 
      try {
        const response = await axios.post('restaurants/create', restaurants);
        commit('SET_RESTAURANTS', response.data);
      } catch (error) {
        console.error('Error saving restaurants', error);
      }
    },
    fetchUser({ commit }) {
      const user = JSON.parse(localStorage.getItem('user'));
      if(user){
        commit('SET_USER', user);
      }
      
    }
  },
  
  plugins: [createPersistedState(),]
});
export default store;






