package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Restaurant;
import org.apache.coyote.Response;
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
    private String apiUrl;

    @Value("${yelp.api.token")
    private String apiToken;

    private final String LOCATION_PARAM = "location=";
    private final String RADIUS_PARAM = "radius=";
    private final String SORT_BY_PARAM = "sort_by";
    private final String LIMIT_PARAM = "limit";

    public List<Restaurant> getSearchResults(String zipcode){
        String url = this.apiUrl + "location=" + zipcode;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);

        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        System.out.println(response.getBody());

        List<Restaurant> restaurants = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");

            for (int i = 0; i < root.size(); i++){
                String restaurantId = root.path(i).path("id").asText();
            }
        } catch (JsonProcessingException e) {
            System.out.println("[Yelp Service] Problem retrieving data.");
        }

        return new ArrayList<>();
    }
}
