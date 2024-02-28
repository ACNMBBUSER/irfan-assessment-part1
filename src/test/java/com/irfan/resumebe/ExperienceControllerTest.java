package com.irfan.resumebe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irfan.resumebe.Dto.ExperienceRequest;
import com.irfan.resumebe.Dto.ExperienceResponse;
import com.irfan.resumebe.Model.Experience;
import com.irfan.resumebe.Model.User;
import com.irfan.resumebe.Repository.ExperienceRepository;
import com.irfan.resumebe.Service.ExperienceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ExperienceControllerTest {

    @MockBean
    private ExperienceService experienceService;

    @MockBean
    private ExperienceRepository experienceRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    // Common data for testing
    Experience experience = new Experience(1, "Position", "Company", "2022-01-01", "2024-01-01", "Responsibilities", new User());
    int id = experience.getId();
    // Use for update Experience
    Experience updateExperience = new Experience(1, "Senior Developer", "Accenture", "2022-04-01", "Present", "Develop A System", new User());

    @Test
    void shouldCreateExperience() throws Exception {
        // Mocking service method
        doNothing().when(experienceService).createExperience(any(ExperienceRequest.class));

        // Performing POST request
        mockMvc.perform(post("/v1/api/experience")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(experience)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Successfully Added Experience"))
                .andDo(print());
    }

    @Test
    void shouldReturnListOfExperiences() throws Exception {
        List<Experience> experiences = Arrays.asList(
                new Experience(1, "Position1", "Company1", "2022-01-01", "2024-01-01", "Responsibilities1", new User()),
                new Experience(2, "Position2", "Company2", "2023-01-01", "2025-01-01", "Responsibilities2", new User())
        );

        // Mocking service method
        when(experienceService.getAllExperiences()).thenReturn(experiences);

        // Performing GET request
        mockMvc.perform(get("/v1/api/experience"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(experiences.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnExperienceById() throws Exception {
        // Mocking service method
        when(experienceService.getExperienceById(1)).thenReturn(experience);

        // Performing GET request
        mockMvc.perform(get("/v1/api/experience/{experienceId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    void shouldUpdateExperienceById() throws Exception {
        // Mock the behavior of experienceRepository.findById(id)
        when(experienceRepository.findById(id)).thenReturn(Optional.of(experience));

        // Create an updated experience response to be returned by the service
        ExperienceResponse updateExperienceResponse = new ExperienceResponse("Updated Experience");

        // Mock the behavior of experienceService.updateExperienceById(experienceId, experienceRequest)
        when(experienceService.updateExperienceById(any(Integer.class), any(ExperienceRequest.class)))
                .thenAnswer(invocation -> {
                    Integer experienceIdArg = invocation.getArgument(0);
                    ExperienceRequest experienceRequestArg = invocation.getArgument(1);

                    // Assert that the arguments passed to the service method are correct
                    assertEquals(id, experienceIdArg);
                    assertEquals(updateExperience.getPosition(), experienceRequestArg.getPosition());
                    assertEquals(updateExperience.getCompany(), experienceRequestArg.getCompany());
                    assertEquals(updateExperience.getStart_date(), experienceRequestArg.getStart_date());
                    assertEquals(updateExperience.getEnd_date(), experienceRequestArg.getEnd_date());
                    assertEquals(updateExperience.getResponsibilities(), experienceRequestArg.getResponsibilities());

                    // Return the mocked response
                    return updateExperienceResponse;
                });

        // Performing PUT request
        mockMvc.perform(put("/v1/api/experience/{experienceId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateExperience)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Updated Experience"))
                .andDo(print());
    }

    @Test
    void shouldDeleteExperienceById() throws Exception {
        // Performing DELETE request
        mockMvc.perform(delete("/v1/api/experience/{experienceId}", 1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
