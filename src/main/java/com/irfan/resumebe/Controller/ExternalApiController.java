package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Service.BillPlzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/external")

public class ExternalApiController {

    @Autowired
    private BillPlzService billPlzService;

    @PostMapping("/collections")
    public ResponseEntity<Map<String, Object>> createCollection(@RequestBody String requestBody) {
        try {
            String responseBody = billPlzService.createCollection(requestBody);

            Map<String, Object> data = new HashMap<>();
            data.put("responseBody", responseBody);
            data.put("message", "Collection created successfully");

            Map<String, Object> response = new HashMap<>();
            response.put("status", true);
            response.put("code", 200);
            response.put("data", data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", false);
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "Failed to create collection");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/collections/{collectionId}")
    public ResponseEntity<Map<String, Object>> getCollection(@PathVariable String collectionId) {
        try {
            String responseBody = billPlzService.getCollection(collectionId);
            if (responseBody != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("responseBody", responseBody);
                data.put("message", "Collection retrieved successfully");

                Map<String, Object> response = new HashMap<>();
                response.put("status", true);
                response.put("code", 200);
                response.put("data", data);

                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", false);
                errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                errorResponse.put("message", "An error occurred Collection Not Found");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", false);
            errorResponse.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.put("message", "An error occurred while retrieving collection information");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }





}
