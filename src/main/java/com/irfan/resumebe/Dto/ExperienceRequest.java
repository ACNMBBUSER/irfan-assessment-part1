package com.irfan.resumebe.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequest {
    @NotBlank(message = "Position Cannot be blank")
    @NotNull(message = "Position Cannot be null")
    private String position;

    @NotBlank(message = "Company Cannot be blank")
    @Length(min = 10, max=100)
    private String company;

    @NotBlank(message = "start_date Cannot be blank")
    private String startDate;

    @NotBlank(message = "end_date Cannot be blank")
    private String endDate;

    @NotBlank(message = "Responsibilities Cannot be blank")
    private String responsibilities;

    // Getters and setters
}