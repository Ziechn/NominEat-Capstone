package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Hour;
import com.techelevator.model.Hours;
import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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

    private final String LOCATION_PARAM = "location=";
    private final String RADIUS_PARAM = "radius=";
    private final String SORT_BY_PARAM = "sort_by";
    private final String LIMIT_PARAM = "limit";

    public List<Restaurant> getSearchResults(String queryZipcode){
        String url = this.searchUrl + "location=" + queryZipcode + "&limit=5";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        List<Restaurant> restaurants = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("businesses");
            System.out.println(root);

            for (int i = 0; i < root.size(); i++){
                // Get general restaurant information:
                String id = root.path(i).path("id").asText();
                String name = root.path(i).path("name").asText();
                String phoneNumber = root.path(i).path("display_phone").asText();

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

                // Hours and open status need to be handled by going to the business id.
                // List<String> openHours = getOpenHours(id);
                getHours(id);

                List<String> openHours = new ArrayList<>();
                List<String> closingHours = new ArrayList<>();

                // Open Status
                boolean isClosed = root.path(i).path("is_closed").asBoolean();

                // External links:
                String imageUrl = root.path(i).path("image_url").asText();
                String menuUrl = root.path(i).path("attributes").path("menu_url").asText();

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
                        openHours,
                        closingHours,
                        isClosed);

                restaurants.add(restaurant);
            }
        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        return restaurants;
    }

    public List<Open> getHours(String businessId){
        String url = this.businessUrl + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        List<Open> opens = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("hours");

            for (int i = 0; i < root.path(0).path("open").size(); i++){
                System.out.println(root.path(0).path("open").path(i));
                boolean isOverNight = root.path(0).path("open").path(i).path("is_overnight").asBoolean();
                String start = root.path(0).path("open").path(i).path("start").asText();
                String end = root.path(0).path("open").path(i).path("end").asText();
                int day = root.path(0).path("open").path(i).path("day").asInt();

                opens.add(new Open(isOverNight,start,end,day));
            }

        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        return opens;
    }

    public List<String> getOpenHours(String businessId){
        String url = this.businessUrl + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        List<String> openHours = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode;

            System.out.println(root.asText());

            // START HERE. Continue digging down to get the hours.
            for (int i = 0; i < root.size(); i++){
                openHours.add(root.path(i).path("open").asText());
            }
        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        return openHours;
    }

    public List<String> getCloseHours(String businessId){
        return new ArrayList<>();
    }

    public String isOpen(String businessId){
        return "False";
    }
}
