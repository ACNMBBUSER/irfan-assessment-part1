package com.irfan.resumebe.Service;

import com.irfan.resumebe.Dto.ExperienceRequest;
import com.irfan.resumebe.Dto.ExperienceResponse;
import com.irfan.resumebe.Exception.ExperienceNotFoundException;
import com.irfan.resumebe.Exception.UserNotFoundException;
import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Model.User;
import com.irfan.resumebe.Repository.ExperienceRepository;
import com.irfan.resumebe.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<Experience> getAllExperiences() {
        List<Experience> experiences = experienceRepository.findAll();

        if(experiences.isEmpty()) {
            throw new UserNotFoundException("No experiences found");
        }

        log.info("Retrieved Experiences list:");
        for(Experience experience : experiences){
            log.info("Experiences: {}", experience.getCompany());
        }

        return experiences;
    }

    public void createExperience(ExperienceRequest experienceRequest) {
        // Fetch the user entity with id = 1
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User with id = 1 not found"));

        Experience experience = Experience.builder()
                .Position(experienceRequest.getPosition())
                .Company(experienceRequest.getCompany())
                .start_date(experienceRequest.getStart_date())
                .end_date(experienceRequest.getEnd_date())
                .Responsibilities(experienceRequest.getResponsibilities())
                .user(user) // Set the user
                .build();

        experienceRepository.save(experience);
        log.info("Experience {} is saved", experience.getId());

    }

    public Experience getExperienceById(Long experienceId) {
        Optional<Experience> getExperience = experienceRepository.findById(experienceId);

        if (getExperience.isPresent()) {
            Experience experience = getExperience.get();
            log.info("Retrieved Experience by ID {}: {}", experienceId, experience);
            return experience;
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceId);
        }
    }

    public ExperienceResponse updateExperienceById(Long experienceId, ExperienceRequest experienceRequest) {
        Optional<Experience> getExperience = experienceRepository.findById(experienceId);

        if (getExperience.isPresent()) {
            Experience existingExperience = getExperience.get();
            // Update the existing experience with new data from the request
            existingExperience.setPosition(experienceRequest.getPosition());
            existingExperience.setCompany(experienceRequest.getCompany());
            existingExperience.setStart_date(experienceRequest.getStart_date());
            existingExperience.setEnd_date(experienceRequest.getEnd_date());
            existingExperience.setResponsibilities(experienceRequest.getResponsibilities());

            // Save the updated experience
            experienceRepository.save(existingExperience);

            // Log the experience
            log.info("Updated experience by ID {}: {}", experienceId, existingExperience);

            // Map and return the updated experiences
            return mapToProductResponse(existingExperience);
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceId);
        }
    }

    public void deleteExperienceById(Long experienceId) {
        Optional<Experience> getExperience = experienceRepository.findById(experienceId);

        if (getExperience.isPresent()) {
            Experience existingExperience = getExperience.get();

            // Delete the experience
            experienceRepository.delete(existingExperience);

            // Log the deletion
            log.info("Deleted experience by ID {}: {}", experienceId, existingExperience);
        } else {
            throw new ExperienceNotFoundException("Experience not found with ID: " + experienceId);
        }
    }

    private ExperienceResponse mapToProductResponse(Experience experience) {
        return ExperienceResponse.builder()
                .id(experience.getId())
                .Position(experience.getPosition())
                .Company(experience.getCompany())
                .start_date(experience.getStart_date())
                .end_date(experience.getEnd_date())
                .Responsibilities(experience.getResponsibilities())
                .build();


    }
}
