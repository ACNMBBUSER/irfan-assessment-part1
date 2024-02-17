package com.irfan.resumebe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {
//    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDataFromExternalAPI() {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";
        String response = restTemplate.getForObject(apiUrl, String.class);
        return response;
    }
}
