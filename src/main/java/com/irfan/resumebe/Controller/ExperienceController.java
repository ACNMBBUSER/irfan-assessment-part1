package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Dto.ExperienceRequest;
import com.irfan.resumebe.Dto.ExperienceResponse;
import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor


public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createExperience(@RequestBody ExperienceRequest experienceRequest){
        experienceService.createExperience(experienceRequest);
        return "Successfully Added Experience";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();
    }

    @GetMapping("{experienceId}")
    @ResponseStatus(HttpStatus.OK)
    public Experience getExperienceById(@PathVariable Integer experienceId) {
        return experienceService.getExperienceById(experienceId);
    }

    @PutMapping("{experienceId}")
    @ResponseStatus(HttpStatus.OK)
    public ExperienceResponse updateExperienceById(@PathVariable Integer experienceId, @RequestBody ExperienceRequest experienceRequest) {
        return experienceService.updateExperienceById(experienceId, experienceRequest);
    }

    @DeleteMapping("{experienceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExperienceById(@PathVariable Integer experienceId) {
        experienceService.deleteExperienceById(experienceId);
    }





}
