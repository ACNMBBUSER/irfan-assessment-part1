package com.irfan.resumebe.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceRequest {
    private Long id;
    private String Position;
    private String Company;
    private String start_date;
    private String end_date;
    private String Responsibilities;

}
