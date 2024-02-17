package com.irfan.resumebe.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private Long id;
    private String Position;
    private String Company;
    private String start_date;
    private String end_date;
    private String Responsibilities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Add this annotation to prevent infinite recursion
    private User user;

}
