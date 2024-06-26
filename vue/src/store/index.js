import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';
import RestaurantService from '../services/RestaurantService';
import EventService from '../services/EventService';
import AuthService from '../services/AuthService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      restaurants: [],
      loading: false,
      error: null,
      //votes: {},
      events: null,
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
        //state.filteredRestaurants = restaurants;
      },
      ADD_SELECTED_RESTAURANTS(state, restaurants) {
        state.selectedRestaurants.push(restaurants);
      },
      setVotes(state, { restaurantId, votes }) {
        state.votes = {
          ...state.votes,
          [restaurantId]: votes
        };
      },
      incrementYesVotes(state, restaurantId) {
        if (state.votes[restaurantId]) {
          state.votes[restaurantId].yesVotes++;
        } else {
          state.votes[restaurantId] = { yesVotes: 1, noVotes: 0 };
        }
      },
      incrementNoVotes(state, restaurantId) {
        if (state.votes[restaurantId]) {
          state.votes[restaurantId].noVotes++;
        } else {
          state.votes[restaurantId] = { noVotes: 1, yesVotes: 0 };
        }
      },

      // REMOVE_SELECTED_RESTAURANTS(state, restaurantId) {
      //   state.selectedRestaurants = state.selectedRestaurants.filter(
      //     restaurant => restaurant.id !== restaurantId
      //   );
      // },
      // CREATE_EVENT(state, event) {
      //   state.events.push(event);
      // },
      // ADD_EVENT(state, event) {
      //   state.events.push(event);
      // },
      SET_ERROR(state, error) {
        state.error = error;
      },
      SET_LOADING(state, loading) {
        state.loading = loading;
      },
      SET_EVENTS(state, events) {
        state.events = events;
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
          )
        } else {
          state.filteredRestaurants = state.restaurants;
        }
      }
    },
//initial backup data (comment in and out dont delete)
// const backupData = [
//   {
//     id: 1,
//     name: 'East Village Pizza',
//    // zipCode: '11222',
//     imageUrl: 'https://via.placeholder.com/250',
//     categories: ['Pizza'],
//     rating: 3.5,
//     price: '$$',
//     address1: '1234 Pizza St, New York, NY',
//     isOpenNow: true,
//     menuUrl: 'http://www.eastvillagepizza.com/menu',
//     hours: [
//       { day: 1, start: '1100', end: '2200'},
//       { day: 2, start: '1100', end: '2200'},
//       { day: 3, start: '1100', end: '2200'},
//       { day: 4, start: '1100', end: '2200'},
//       { day: 5, start: '1100', end: '2200'},
//       { day: 6, start: '1100', end: '2200'},
//       { day: 7, start: '1100', end: '2200'},
//     ],
//     phoneNumber: '555-1234',
//     status: 'Open',
//   },
//   {
//     id: 2,
//     name: 'Sushi Place',
//    // zipCode: '11222',
//     imageUrl: 'https://via.placeholder.com/250',
//     categories: [ 'Sushi' ],
//     // category: 'Sushi',
//     rating: 4,
//     price: '$$$',
//     address1: '678 Sushi Place, New York, NY',
//     isOpenNow: true,
//     menuUrl: 'http://www.sushiplace.com/menu',
//     hours: [
//       { day: 1, start: '1100', end: '2200'},
//       { day: 2, start: '1100', end: '2200'},
//       { day: 3, start: '1100', end: '2200'},
//       { day: 4, start: '1100', end: '2200'},
//       { day: 5, start: '1100', end: '2200'},
//       { day: 6, start: '1100', end: '2200'},
//       { day: 7, start: '1100', end: '2200'},
//     ],
//     phoneNumber: '555-1234',
//     status: 'Open',
//   },
//   {
//     id: 3,
//     name: 'Burger House',
//    // zipCode: '11222',
//     imageUrl: 'https://via.placeholder.com/250',
//     categories: ['Burgers'],
//     rating: 5,
//     price: '$$',
//     address1: '1234 Burger Blvd, New York, NY',
//     isOpenNow: true,
//     menuUrl: 'http://www.burgerhouse.com/menu',
//     hours: [
//       { day: 1, start: '1100', end: '2200'},
//       { day: 2, start: '1100', end: '2200'},
//       { day: 3, start: '1100', end: '2200'},
//       { day: 4, start: '1100', end: '2200'},
//       { day: 5, start: '1100', end: '2200'},
//       { day: 6, start: '1100', end: '2200'},
//       { day: 7, start: '1100', end: '2200'},
//     ],
//     phoneNumber: '555-1234',
//     status: 'Open',
//     // coordinates;
//   }

// ];

// const store = _createStore({
//   state: {
//     restaurants: [],
//     events: [],
//     //votes: {},
//     token: localStorage.getItem('token') || '',
//     user: JSON.parse(localStorage.getItem('user')) || {}
//   },
 
//   mutations: {

 
//     SET_AUTH_TOKEN(state, token) {
//       state.token = token;
//       localStorage.setItem('token', token);
//       axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
//     },
//     SET_USER(state, user) {
//       state.user = user;
//       localStorage.setItem('user', JSON.stringify(user));
//     },
//     SET_EVENTS(state, events) {
//       state.events = events;
//     },
//     SET_RESTAURANTS(state, restaurants) {
//       state.restaurants = restaurants;
     // state.filteredRestaurants = restaurants;
    // },
    //       SET_ZIP_CODE(state, zipCode) {
    //   state.zipCode = zipCode;
    // },
    // SET_LIMIT(state, limit) {
    //   state.limit = limit;
    // },
    //   ADD_RESTAURANT(state, restaurant) {
    //   state.restaurants.push(restaurant);
    // },
  //   SET_VOTES(state, { restaurantId, votes }) {
  //    state.votes = ??
  //   }
  // },
  //   LOGOUT(state) {
  //     localStorage.removeItem('token');
  //     localStorage.removeItem('user');
  //     state.token = '';
  //     state.user = {};
  //     axios.defaults.headers.common = ['Authorization'];
  //   axios.defaults.headers.common = {};
  //   },
  // },

    // REMOVE_SELECTED_RESTAURANTS(state, restaurantId) {
    //   state.selectedRestaurants = state.selectedRestaurants.filter(
    //     restaurant => restaurant.id !== restaurantId
    //   );
    // // },
    // CREATE_EVENT(state, event) {
    //   state.events.push(event);
    // },
        // ADD_EVENT(state, event) {
    //   state.events.push(event);
    // },
    // SET_ERROR(state, error) {
    //   state.error = error;
    // },
    // SET_LOADING(state, loading) {
    //   state.loading = loading;
    // },

    // SET_CATEGORY(state, category) {
    //   state.category = category;
    // },
    // FILTER_BY_CATEGORY(state, category) {
    //  if (category) {
    //     state.filteredRestaurants = state.restaurants.filter(restaurant =>
    //       restaurant.categories.some(cat =>
    //         cat.title.toLowerCase().includes(category.toLowerCase())
    //         )
    //       )   } else { 
    //   state.filteredRestaurants = state.restaurants;
    //   }
    // }

  actions: {


      async fetchRestaurants({ commit, state }, { zipCode, limit , term }) {
        commit('SET_LOADING', true);
        try {
          const response = await RestaurantService.searchRestaurants(zipCode, limit, term).then(response => {
          commit('SET_RESTAURANTS', response.data);
          })
          // const responseEvents = await EventService.getEventRestaurants({ zipCode, limit });
          //console.log('Restaurants fetched:', response.data);
          //commit('SET_RESTAURANTS', responseEvents);
        } catch (error) {
          console.error('Error fetching restaurants: ', error);
          //commit('SET_RESTAURANTS', backupData); // Fallback to backup data 
        } finally {
          commit('SET_LOADING', false);
        }

      },
      async createEvent({ commit }, eventData) {
return EventService.createEvent(eventData).then(response => {
  commit('SET_EVENTS', [...this.state.events, response.data]);
  return response.data;
});

        //commit('SET_LOADING', true);
        //try {
      //     eventData.organizerId = state.user.id;
      //     const eventResponse = await EventService.createEvent(eventData);
      //     const eventId = eventResponse.data.id;
      //     await EventService.addRestaurantsToEvent(eventId, eventData.restaurants);
      //     return eventResponse.data;
      //   } catch (error) {
      //     commit('SET_ERROR', error);
      //     throw error;
      //   } finally {
      //     commit('SET_LOADING', false);
      //   }
      // },
      //    create event og method const response = await EventService.createEvent(eventData);
      //     commit('SET_EVENTS', [...this.state.events, response.data]);
      //     return response.data;
      //   } catch (error) {
      //     console.error('Error creating event', error);
      //     commit('SET_ERROR', error);
      //     throw error;
      //   } finally {
      //     commit('SET_LOADING', false);
      //   }
      },
      async fetchEvent({ commit }, eventId ) {
        try {
          const response = await axios.get(`/events/${eventId}`);
          console.log('Event fetched: ', response.data);
          commit('SET_CURRENT_EVENT', response.data);
        } catch (error) {
          console.error('Error fetching event:', error);
        }
      },
      async fetchEventRestaurants({ commit }, eventId ) {
      return EventService.getRestaurantsForEvent(eventId).then(response => {
        commit('SET_RESTAURANTS', response.data);
      });
    },
         // const response = await axios.get(`/events/${eventId}/restaurants`);
         // console.log('Restaurants fetched: ', response.data);
       
        // } catch (error) {
        //   console.error('Error fetching event restaurants:', error);
        // }
        //saveRestauranttoEvent 
      async associateRestaurantsWithEvent({ commit }, { eventId, restaurants }) { 
          // const response = await RestaurantService.associateRestaurantWithEvent(eventId, restaurants);
          // return response;
         return EventService.addRestaurantsToEvent(eventId, restaurants).then(response => {
          response.data.forEach(restaurant => {
            // commit('ADD_SELECTED_RESTAURANTS');
            commit('SET_RESTAURANTS')
          });
         });
        
        },
      fetchUser({ commit }) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
          console.log('Retrieving user: ', user);
          commit('SET_USER', user);

        }
      }
    },
    fetchVotes({ commit }, { eventId, restaurantId }) {
      return Promise.all([
        EventService.getYesVote(eventId, restaurantId),
        EventService.getNoVote(eventId, restaurantId)]).then(([
          yesResponse, noResponse]) => {
            commit('setVotes', {
              restaurantId,
              votes: {
                yesVotes: yesResponse.data,
                noVotes: noResponse.data
              }
            });
          });
        },
    //       addYesVote({ commit }, { eventId, restaurantId }) {
    //         return EventService.addYesVote(eventId, restaurantId).then(() 
    //         => {
    //           commit('incrementNoVotes', restaurantId);
    //         });
    //       },
    //       addNoVote({ commit }, { eventId,restaurantId })
      
    // },
   

    plugins: [createPersistedState(),]
  });
  return store;
}
