import axios from "axios";

const http = axios.create({
    baseURL: 'http://localhost:9000'
});

export default {
    list(zipCode, limit){
        return http.get(`/search?zipcode=${zipCode}&limit=${limit}`)
    }
}