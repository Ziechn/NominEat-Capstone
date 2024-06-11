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
    list(zipCode, limit){  //, category
        const params = { zipcode: zipCode, limit };
       // if (category) params.term = category;
        return http.get('/restaurants/search', { params });
    },
    create(event) {
        return http.post('/events/create/', event);
    },
    // associateRestaurantWithEvent(eventId, restaurants) {
    //     return http.post(`restaurants/create/${eventId}`,  { restaurants });
    // }
};



// return http.get(`/restaurants/search?zipcode=${zipCode}&limit=${limit}&term=${category}`)