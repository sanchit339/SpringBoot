package com.sanchit.springboot.SpringBootProject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


// Used LOMBOOK lib
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // if sequence thats because of PostgreSQL db used by h2
    private Long id;
    private String email;
    private String name;
    private Integer age;
    private LocalDate DOJ;
    @JsonProperty("isActive")
    private Boolean active;
    private String role;
}
