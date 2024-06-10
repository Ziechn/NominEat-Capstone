import axios from 'axios';



  const http = axios.create({
    baseURL: 'http://loacalhost:9000',
  });

  http.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if(token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });

  export default {
  login(user) {
    //return axios.post('/', user),
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  }

}
