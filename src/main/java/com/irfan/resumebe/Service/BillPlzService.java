package com.irfan.resumebe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.beans.factory.annotation.Value;

@Service
public class BillPlzService {

    @Value("${billplz.api.base-url}")
    private String baseUrl;

    @Value("${billplz.api.authorization-token}")
    private String authToken;

    @Autowired
    private RestTemplate restTemplate;

    public void createCollection(String requestBody) {
        String url = baseUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + authToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Collection created successfully!");
                System.out.println("Response Body: " + responseEntity.getBody());
            } else {
                System.err.println("Failed to create collection. Status code: " + responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("Error occurred: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
        }
    }
    public String getCollection(String collectionId) {
        String url =  baseUrl + collectionId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + authToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                System.out.println("Collection retrieved successfully!");
                System.out.println("Response Body: " + responseEntity.getBody());
                return responseEntity.getBody(); // Return the response body
            } else {
                System.err.println("Failed to retrieve collection. Status code: " + responseEntity.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException.NotFound | HttpServerErrorException.InternalServerError e) {
            System.err.println("Error occurred: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        }
    }
}
