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
 
    createEvent(eventData) {
        return http.post('/events/create', eventData);
    },
    addRestaurantsToEvent(eventId, restaurants) {
        return http.post(`restaurants/create/${eventId}`, restaurants);
    },
    getEvents() {
        return http.get('/events');
    },
    getEvent(eventId) {
        return http.get(`/events/${eventId}`);
    },
    getEventByLink (eventLink) {
        return http.get(`/events/link/${eventLink}`);
    },
    getRestaurantsForEvent(eventId) {
        return http.get(`/events/${eventId}/restaurants`);
    },
    getOrganizerRestaurants(eventId) {
        return http.get(`/events/${eventId}/restaurants/`)
    },
    addYesVote(eventId, restaurantId) {
        return http.post(`events/add-yes-vote/${eventId}/${restaurantId}`);
    },
    addNoVote(eventId, restaurantId) {
        return http.post(`events/add-no-vote/${eventId}/${restaurantId}`);
    },
    getNoVote(eventId, restaurantId) {
        return http.get(`events/no-votes/${eventId}/${restaurantId}`);
    },
    getYesVote(eventId, restaurantId) {
        return http.get(`events/yes-votes/${eventId}/${restaurantId}`);
    },
    getEventByUserId(){
        return http.get(`events/organizer`);
    },
    getRestaurantById(restaurantId){
        return http.get(`restaurants/${restaurantId}`);
    }
}
