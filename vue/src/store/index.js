import { createStore as _createStore } from 'vuex';
import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';



//backup data
const backupData = [
        {
          id: 1,
          name: 'East Village Pizza',
          imageUrl: 'https://via.placeholder.com/250',
          categories: [{ title: 'Pizza' }],
          rating: 8,
          category: 'Pizza',
          price: '$$',
          location: {
          address1: '1234 Pizza St, New York, NY',
          distance: '12 miles away'
          },
          hours: '6AM - 9PM',
          isOpenNow: true,
          menuUrl: 'https:/www.eastvillagepizza.net/menu'
        },
        {
          id: 2,
          name: 'Sushi Place',
          imageUrl: 'https://via.placeholder.com/250',
          categories: [{ title: 'Sushi' }],
          rating: 9,
          category: 'Sushi',
          price: '$$$',
          location: {
          address1: '4567 Sushi St, New York, NY',
          distance: '8 miles away'
          },
          hours: '11PM - 9PM',
          isOpenNow: true,
          menuUrl: 'https:/www.eastvillagepizza.net/menu'
        },
        {
          id: 3,
          name: 'Burger House',
          imageUrl: 'https://via.placeholder.com/250',
          categories: [{ title: 'Burger' }],
          rating: 9,
          category: 'Burger',
          price: '$$',
          location: {
          address1: '5678 Burger Blvd, New York, NY',
          distance: '2 miles away'
          },
          hours: '11AM - 11PM',
          isOpenNow: true,
          menuUrl: 'https:/www.eastvillagepizza.net/menu'
        }
        

];
const store = createStore({
  state: {
    zipCode: '',
    limit: 10,
    restaurants: backupData,
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
    },
    SET_LOADING(state, loading) {
      state.loading = loading;
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

  },
  actions: {
    async fetchRestaurants({ commit }, { zipCode, limit }) {
      commit('SET_LOADING', true);
      try {
        const response = await axios.get(`http://localhost:9000/search`, {
          params: {
            zipcode: zipCode,
            limit: limit
          }
        });
        //fake api call here
        // const response = { data: backupData };
        commit('SET_RESTAURANTS', response.data);

      } catch (error) {
        console.error('Error fetching restaurants: ', error);
        commit('SET_RESTAURANTS', backupData);
      } finally {
        commit('SET_LOADING', false);
      }
    },
  },
  
  plugins: [createPersistedState(),]
});
export default store;

