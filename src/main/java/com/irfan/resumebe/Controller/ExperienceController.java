package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Dto.ExperienceRequest;
import com.irfan.resumebe.Dto.ExperienceResponse;
import com.irfan.resumebe.Exception.ExperienceNotFoundException;
import com.irfan.resumebe.Exception.UserNotFoundException;
import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Service.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/experience")
@RequiredArgsConstructor


public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ResponseEntity<?> createExperience(@Valid @RequestBody ExperienceRequest experienceRequest) throws UserNotFoundException {
        Experience createdExperience = experienceService.createExperience(experienceRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully Insert Experience");
        response.put("experience", createdExperience);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() throws ExperienceNotFoundException {
        List<Experience> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Integer experienceId) {
        Experience experience = experienceService.getExperienceById(experienceId);
        return ResponseEntity.ok(experience);
    }

    @PutMapping("{experienceId}")
    public ResponseEntity<ExperienceResponse> updateExperienceById(@PathVariable Integer experienceId, @RequestBody ExperienceRequest experienceRequest) throws ExperienceNotFoundException {
        ExperienceResponse updatedExperience = experienceService.updateExperienceById(experienceId, experienceRequest);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("{experienceId}")
    public ResponseEntity<Void> deleteExperienceById(@PathVariable Integer experienceId) throws ExperienceNotFoundException {
        experienceService.deleteExperienceById(experienceId);
        return ResponseEntity.noContent().build();
    }


}
