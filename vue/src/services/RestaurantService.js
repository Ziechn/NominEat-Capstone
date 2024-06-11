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

  ///restaurants/search?zipcode={zip}&limit={limit}&term={category]
  
export default {
    list(zipcodeParam, limitParam, termParam){  //, category
        const params = { zipcode: zipcodeParam, limit: limitParam, term: termParam };
       // if (category) params.term = category;
        return http.get('/restaurants/search', { params });
    },
    createEvent(event) {
        return http.post('/events/create/', event);
    },
    getEvent (eventId) {
        return http.get(`/events/${eventId}`);
    },
    getEventRestaurants(eventId) {
        return http.get(`/events/${eventId}/restaurants`);
    },
    associateRestaurantWithEvent(eventId, restaurants) {
        return http.post(`restaurants/create/${eventId}`,  { restaurants });
    }
};



// return http.get(`/restaurants/search?zipcode=${zipCode}&limit=${limit}&term=${category}`)