package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Dto.ExperienceRequest;
import com.irfan.resumebe.Dto.ExperienceResponse;
import com.irfan.resumebe.Exception.ExperienceNotFoundException;
import com.irfan.resumebe.Exception.UserNotFoundException;
import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/api/experience")
@RequiredArgsConstructor


public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<String> createExperience(@RequestBody ExperienceRequest experienceRequest) {
        try {
            experienceService.createExperience(experienceRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Added Experience");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating experience: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        try {
            List<Experience> experiences = experienceService.getAllExperiences();
            return ResponseEntity.ok(experiences);
        } catch (ExperienceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Integer experienceId) {
        try {
            Experience experience = experienceService.getExperienceById(experienceId);
            return ResponseEntity.ok(experience);
        } catch (ExperienceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{experienceId}")
    public ResponseEntity<ExperienceResponse> updateExperienceById(@PathVariable Integer experienceId, @RequestBody ExperienceRequest experienceRequest) {
        try {
            ExperienceResponse updatedExperience = experienceService.updateExperienceById(experienceId, experienceRequest);
            return ResponseEntity.ok(updatedExperience);
        } catch (ExperienceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{experienceId}")
    public ResponseEntity<Void> deleteExperienceById(@PathVariable Integer experienceId) {
        try {
            experienceService.deleteExperienceById(experienceId);
            return ResponseEntity.noContent().build();
        } catch (ExperienceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }





}
