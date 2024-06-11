// MVP: hours of operation - store and card(back) need, open and closed data -store needs card has, call to order store and card(back) need
// Nice to Haves: Num of stars, map, takeout.delivery option shown 
import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';
import RestaurantService from '../services/RestaurantService';
// import AuthService from '../services/AuthService';
//import backupData from '@/backupData';

//initial backup data (comment in and out dont delete)
const backupData = [
  {
    id: 1,
    name: 'East Village Pizza',
   // zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    categories: ['Pizza'],
    rating: 3.5,
    price: '$$',
    address1: '1234 Pizza St, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.eastvillagepizza.com/menu',
    hours: [
      { day: 1, start: '1100', end: '2200'},
      { day: 2, start: '1100', end: '2200'},
      { day: 3, start: '1100', end: '2200'},
      { day: 4, start: '1100', end: '2200'},
      { day: 5, start: '1100', end: '2200'},
      { day: 6, start: '1100', end: '2200'},
      { day: 7, start: '1100', end: '2200'},
    ],
    phoneNumber: '555-1234',
    status: 'Open',
  },
  {
    id: 2,
    name: 'Sushi Place',
   // zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    categories: [ 'Sushi' ],
    // category: 'Sushi',
    rating: 4,
    price: '$$$',
    address1: '678 Sushi Place, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.sushiplace.com/menu',
    hours: [
      { day: 1, start: '1100', end: '2200'},
      { day: 2, start: '1100', end: '2200'},
      { day: 3, start: '1100', end: '2200'},
      { day: 4, start: '1100', end: '2200'},
      { day: 5, start: '1100', end: '2200'},
      { day: 6, start: '1100', end: '2200'},
      { day: 7, start: '1100', end: '2200'},
    ],
    phoneNumber: '555-1234',
    status: 'Open',
  },
  {
    id: 3,
    name: 'Burger House',
   // zipCode: '11222',
    imageUrl: 'https://via.placeholder.com/250',
    categories: ['Burgers'],
    rating: 5,
    price: '$$',
    address1: '1234 Burger Blvd, New York, NY',
    isOpenNow: true,
    menuUrl: 'http://www.burgerhouse.com/menu',
    hours: [
      { day: 1, start: '1100', end: '2200'},
      { day: 2, start: '1100', end: '2200'},
      { day: 3, start: '1100', end: '2200'},
      { day: 4, start: '1100', end: '2200'},
      { day: 5, start: '1100', end: '2200'},
      { day: 6, start: '1100', end: '2200'},
      { day: 7, start: '1100', end: '2200'},
    ],
    phoneNumber: '555-1234',
    status: 'Open',
    // coordinates;
  }

];

const store = _createStore({
  state: {
    zipCode: '',
    limit: 2,
    restaurants: [],
    category: '',
    // filteredRestaurants: [],
    // selectedRestaurants: [],
    // events: null,
    loading: false,
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
    // ADD_SELECTED_RESTAURANTS(state, restaurant) {
    //   state.selectedRestaurants.push(restaurant);
    // },
    // REMOVE_SELECTED_RESTAURANTS(state, restaurantId) {
    //   state.selectedRestaurants = state.selectedRestaurants.filter(
    //     restaurant => restaurant.id !== restaurantId
    //   );
    // },
    // CREATE_EVENT(state, event) {
    //   state.events.push(event);
    // },
    SET_LOADING(state, loading) {
      state.loading = loading;
    },
    // SET_EVENTS(state, events) {
    //   state.events = events;
    // },
    // ADD_EVENT(state, event) {
    //   state.events.push(event);
    // },
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
      axios.defaults.headers.common = ['Authorization'];
    axios.defaults.headers.common = {};
    },
    SET_CATEGORY(state, category) {
      state.category = category;
    },
    FILTER_BY_CATEGORY(state, category) {
     if (category) {
        state.filteredRestaurants = state.restaurants.filter(restaurant =>
          restaurant.categories.some(cat =>
            cat.title.toLowerCase().includes(category.toLowerCase())
            )
          )   } else { 
      state.filteredRestaurants = state.restaurants;
      }
    }
  },
  actions: {
    async fetchRestaurants({ commit }, { zipCode, limit, category }) {
      commit('SET_LOADING', true);
      try {
        const response = await RestaurantService.list(zipCode, limit, category);
        console.log('Restaurants fetched:', response.data);
        commit('SET_RESTAURANTS', response.data);
       } catch (error) {
        console.error('Error fetching restaurants: ', error);
        commit('SET_RESTAURANTS', backupData); // Fallback to backup data 
        }finally { 
          commit('SET_LOADING', false);
      }
    },
    // async createEvent({ commit }, event) { 
    //   console.log('Creating new event with:', event);
    //   try {
    //    // const response = await axios.post('/events/create', event);
        
    //     const response = await RestaurantService.createEvent(event);
    //     console.log('Event created: ', response.data);
    //     commit('CREATE_EVENT', response.data);
    //   } catch (error) {
    //     console.error('Error creating event', error);
    //   }
    // },
    // async fetchEvent({ commit }, eventId ) {
    //   try {
    //     const response = await axios.get(`/events/${eventId}`);
    //     console.log('Event fetched: ', response.data);
    //     commit('SET_CURRENT_EVENT', response.data);
    //   } catch (error) {
    //     console.error('Error fetching event:', error);
    //   }
    // },
    // async fetchEventRestaurants({ commit }, eventId ) {
    //   try {
    //     const response = await axios.get(`/events/${eventId}/restaurants`);
    //     console.log('Restaurants fetched: ', response.data);
    //     commit('SET_RESTAURANTS', response.data);
    //   } catch (error) {
    //     console.error('Error fetching event restaurants:', error);
    //   }
    // },
    // async associateRestaurantsWithEvent({ commit }, { eventId, restaurants }) { 
    //   try {
    //     //const response = await axios.post(`/restaurants/create/${eventId}`, { restaurants });

    //     const response = await RestaurantService.associateRestaurantWithEvent(eventId, restaurants);
    //     return response;
    //   } catch (error) {
    //     console.error('Error saving restaurants to event:', error);
    //   }
    // },
    fetchUser({ commit }) {
      const user = JSON.parse(localStorage.getItem('user'));
      if(user){
        console.log('Retrieving user: ', user);
        commit('SET_USER', user);
      }
      
    }
  },
  
  plugins: [createPersistedState(),]
});
export default store;






