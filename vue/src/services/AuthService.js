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
    return http.post('/login', user); //user or credentials??
  },

  register(userData) {
    return http.post('/register', userData);
  },
  getUserProfile() {
    return http.get('/user/profile');
  }

};