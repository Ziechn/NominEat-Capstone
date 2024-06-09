import axios from "axios";

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    list(zipCode, limit, category){
        return http.get(`/restaurants/search?zipcode=${zipCode}&limit=${limit}&term=${category}`)
    }
};