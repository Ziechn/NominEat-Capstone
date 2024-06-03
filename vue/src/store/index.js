import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      restaurants: [
        {
          id: 1,
          name: 'Olive Garden',
          category: 'Italian',
          address: '1234 Mertle St',
          hours: '6AM - 9PM',
          status: 'Open'
        },
        {
          id: 2,
          name: 'McDonalds',
          category: 'Fast-Food',
          address: '4545 Washington Ave',
          hours: '24/7',
          status: 'Open'
        },
        { 
          id: 3,
          name: 'Perkins Restaurant & Bakery',
          category: 'Diner',
          address: '582 Mainstreet',
          hours: '6AM - 12AM',
          status: 'Closed'
        },
        {
          id: 4, 
          name: 'Jets Pizza',
          category: 'Pizza',
          address: '415 12th Ave',
          hours: '9AM - 8PM',
          status: 'Open'
        },
        {
          id: 5,
          name: 'Casa Deli',
          category: 'Mexican',
          address: '978 Abercrombie Lane',
          hours: '8AM - 11PM',
          status: 'Closed'
        }
      ],
      token: currentToken || '',
      user: currentUser || {}
    },
    mutations: {
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
      }
    },
  });
  return store;
}
