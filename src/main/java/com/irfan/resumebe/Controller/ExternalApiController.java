package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor


public class ExternalApiController {

    @Autowired
    private ExternalApiService externalAPIService;


    @GetMapping("/data")
    public String getDataFromExternalAPI() {
        // Call the service method to fetch data from the external API
        String responseData = externalAPIService.getDataFromExternalAPI();
        return responseData;
    }







}
