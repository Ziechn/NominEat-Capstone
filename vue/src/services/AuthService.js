import axios from 'axios';

export default {

  login(user) {
    return axios.post('/', user)
  },

  register(user) {
    return axios.post('/register', user)
  }

}
