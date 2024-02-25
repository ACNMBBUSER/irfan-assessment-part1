package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Service.BillPlzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/external")

public class ExternalApiController {

    @Autowired
    private BillPlzService billPlzService;

    @PostMapping("/collections")
    public ResponseEntity<String> createCollection(@RequestBody String requestBody) {
        try {
            billPlzService.createCollection(requestBody);
            return ResponseEntity.ok("Collection created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create collection: " + e.getMessage());
        }
    }

    @GetMapping("/collections/{collectionId}")
    public ResponseEntity<String> getCollection(@PathVariable String collectionId) {
        String responseBody = billPlzService.getCollection(collectionId);
        if (responseBody != null) {
            return ResponseEntity.ok("Collection retrieval request submitted successfully\n" + responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving collection information");
        }
    }





}
