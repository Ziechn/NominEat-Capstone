// import { createStore as _createStore } from 'vuex';
import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';



//backup data
const backupData = [
        {
          id: 1,
          name: 'Olive Garden',
          imageUrl: 'https://via.placeholder.com/250',
          categories: [{ title: 'Italian' }],
          rating: 4,
          category: 'Italian',
          price: '$$',
          location: {
          address1: '1234 Mertle St',
          },
          hours: '6AM - 9PM',
          isOpenNow: true,
          menuUrl: 'https://olivegarden.com/menu'
        }
        // ,
        // {
        //   id: 2,
        //   name: 'McDonalds',
        //   category: 'Fast-Food',
        //   price: '$',
        //   address: '4545 Washington Ave',
        //   hours: '24/7',
        //   status: 'Open'
        // },
        // { 
        //   id: 3,
        //   name: 'Perkins Restaurant & Bakery',
        //   category: 'Diner',
        //   price: '$$',
        //   address: '582 Mainstreet',
        //   hours: '6AM - 12AM',
        //   status: 'Closed'
        // },
        // {
        //   id: 4, 
        //   name: 'Jets Pizza',
        //   category: 'Pizza',
        //   price: '$$',
        //   address: '415 12th Ave',
        //   hours: '9AM - 8PM',
        //   status: 'Open'
        // },
        // {
        //   id: 5,
        //   name: 'Casa Deli',
        //   category: 'Mexican',
        //   price: '$$$$',
        //   address: '978 Abercrombie Lane',
        //   hours: '8AM - 11PM',
        //   status: 'Closed'
        // }
  
]
const store = createStore({
  state: {
    zipCode: '',
    limit: 10,
    restaurants: [],
    loading: false,
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
      
        commit('SET_RESTAURANTS', response.data);
      } catch (error) {
        console.error('Error fetching restaurants: ', error);
        commit('SET_RESTAURANTS',backupData);
      } finally {
        commit('SET_LOADING', false);
      }
    },
  },
  
  plugins: [createPersistedState(),]
});
export default store;

