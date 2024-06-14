import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:9000',
});

http.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if(token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
  
export default {
    searchRestaurants(zipcodeParam, limitParam, termParam){  //, category
        const params = { zipcode: zipcodeParam, limit: limitParam, term: termParam };
       // if (category) params.term = category;
        return http.get('/restaurants/search', { params });
    },
    };
    // create(event) {
    //     return axios.post('/events/create', event);
    // },
    // associateRestaurantWithEvent(eventId, restaurants) {
    //     return http.post(`restaurants/create/${eventId}`,  { restaurants });
    // }


