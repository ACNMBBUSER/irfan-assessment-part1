package com.irfan.resumebe.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Position Cannot be blank")
    @NotNull(message = "Position Cannot be blank")
    private String Position;

    @NotBlank(message = "Company Cannot be blank")
    @Length(min = 10, max=100)
    private String Company;

    @NotBlank(message = "start_date Cannot be blank")
    private String start_date;

    @NotBlank(message = "end_date Cannot be blank")
    private String end_date;

    @NotBlank(message = "Responsibilities Cannot be blank")
    private String Responsibilities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Add this annotation to prevent infinite recursion
    private User user;

}
