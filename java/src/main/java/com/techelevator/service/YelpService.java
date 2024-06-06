package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class YelpService {

    @Value("${yelp.api.url}")
    private String searchUrl;

    @Value("${yelp.api.business.url}")
    private String businessUrl;

    @Value("${yelp.api.token}")
    private String apiToken;

    private ResponseEntity<String> getResponse(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );
        } catch (RestClientException e) {
            System.out.println("Rest template error.");
        }

        return response;
    }

    public List<Restaurant> getSearchResults(String queryZipcode, int limit, String term){
        String url = this.searchUrl + "location=" + queryZipcode + "&limit=" + limit + "&term=" + term;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        List<Restaurant> restaurants = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(getResponse(url).getBody());
            JsonNode root = jsonNode.path("businesses");

            for (int i = 0; i < root.size(); i++){
                // Get general restaurant information:
                String id = root.path(i).path("id").asText();
                String name = root.path(i).path("name").asText();
                String phoneNumber = root.path(i).path("display_phone").asText();
                double rating = root.path(i).path("rating").asDouble();

                // Categories will need to be handled differently as they are a JSON array.
                List<String> categories = new ArrayList<>();
                for (int a = 0; a < root.path(i).path("categories").size(); a++){
                    categories.add(root.path(i).path("categories").path(a).path("title").asText());
                }

                // Address
                String address1 = root.path(i).path("location").path("address1").asText();
                String address2 = root.path(i).path("location").path("address2").asText();
                String address3 = root.path(i).path("location").path("address3").asText();
                String city = root.path(i).path("location").path("city").asText();
                String zipcode = root.path(i).path("location").path("zip_code").asText();
                String country = root.path(i).path("location").path("country").asText();
                String state = root.path(i).path("location").path("state").asText();

                // Map coordinates
                double latitude = root.path(i).path("coordinates").path("latitude").asDouble();
                double longitude = root.path(i).path("coordinates").path("longitude").asDouble();
                Coordinates coordinates = new Coordinates(latitude, longitude);

                // External links:
                String imageUrl = root.path(i).path("image_url").asText();
                String menuUrl = root.path(i).path("attributes").path("menu_url").asText();

                // Transactions:
                List<String> transactions = new ArrayList<>();
                for (int a = 0; a < root.path(i).path("transactions").size(); a++){
                    transactions.add(root.path(i).path("transactions").path(a).asText());
                }

                Restaurant restaurant = new Restaurant(
                        id,
                        name,
                        phoneNumber,
                        categories,
                        address1,
                        address2,
                        address3,
                        city,
                        state,
                        country,
                        zipcode,
                        imageUrl,
                        menuUrl,
                        rating,
                        coordinates,
                        transactions);

                // Hours and open status need to be handled by going to the business id.
                setHoursAndIsOpen(restaurant, id);

                restaurants.add(restaurant);
            }
        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        return restaurants;
    }

    public void setHoursAndIsOpen(Restaurant restaurant, String businessId){
        String url = this.businessUrl + businessId;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        List<Open> hours = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(getResponse(url).getBody());
            JsonNode root = jsonNode.path("hours");

            for (int i = 0; i < root.path(0).path("open").size(); i++){
                boolean isOverNight = root.path(0).path("open").path(i).path("is_overnight").asBoolean();
                String start = root.path(0).path("open").path(i).path("start").asText();
                String end = root.path(0).path("open").path(i).path("end").asText();
                int day = root.path(0).path("open").path(i).path("day").asInt();
                hours.add(new Open(isOverNight,start,end,day));
            }

            boolean isOpenNow = root.path(0).path("is_open_now").asBoolean();

            restaurant.setHours(hours);
            restaurant.SetIsOpenNow(isOpenNow);

        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        // Frontend branch
    }
}
