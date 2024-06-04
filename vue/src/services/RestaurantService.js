import axios from "axios";

const http = axios.create({
    baseURL: 'http://localhost:9000/search?zipcode='
});

export default {

    list(zipCode){
        return http.get(zipCode)
    }
}