package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/external")
@RequiredArgsConstructor


public class ExternalApiController {

    @Autowired
    private ExternalApiService externalApiService;


    @PostMapping("/post")
    public String postDataToExternalAPI(@RequestBody String title) {
        // Call the service method to post data to the BillPlz API
        return externalApiService.postDataToExternalAPI(title);
    }


}
