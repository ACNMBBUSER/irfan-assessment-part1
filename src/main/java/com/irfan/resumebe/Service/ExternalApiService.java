package com.irfan.resumebe.Service;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

@Service
public class ExternalApiService {
//    @Autowired
    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String postDataToExternalAPI(String title) {
        String apiUrl = "https://www.billplz-sandbox.com/api/v4/collections";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic 8d6b182b-b2bc-4777-8a2e-624719e67ed8");

        // Create request JSONOGQ2YjE4MmItYjJiYy00Nzc3LThhMmUtNjI0NzE5ZTY3ZWQ4Og==
        String requestJson = "{\"title\" : \"" + title + "\"}";

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        // Logging response
        System.out.println("Response: " + response.getBody());

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Request Successful";
        } else {
            return "Request Failed";
        }
    }
}
