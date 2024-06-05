import { createStore as _createStore } from 'vuex';
import { createStore } from 'vuex';
import axios from 'axios';
import createPersistedState from "vuex-persistedstate";
import RestaurantService from '../services/RestaurantService';

const store = createStore({
  state: {
    zipCode: '',
    limit: 10,
    restaurants: [],
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
        const response = await RestaurantService.list(zipCode, limit);
        commit('SET_RESTAURANTS', response.data);
      } catch (error) {
        console.error('Error fetching restaurants: ', error);
        commit('SET_RESTAURANTS', []);
      } finally {
        commit('SET_LOADING', false);
      }
    },
  },
  plugins: [createPersistedState()]
});

export default store;

//backup data
const backupData = [
  
];

