package com.sanchit.springboot.SpringBootProject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanchit.springboot.SpringBootProject.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// When You want to Transfer the Data from the Clients - Controllers - Service
public class EmployeeDTO {

    private Long id;
    @Email(message = "Email Must be valid :")
    private String email;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3 , max = 10 , message = "Name size should be in Range [3,10]")
    private String name;

    @Max(value = 80, message = "Age cannot be greater then 80 :")
    @Min(value = 10, message = "Age must be min 10 : ")
    private Integer age;

    @Past(message = "Date cannot be Future " )
    private LocalDate DOJ;
    @JsonProperty("isActive") // input field name
    private Boolean active;

    @NotBlank(message = "Role of the employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)")
    @EmployeeRoleValidation
    private String role;

    // all arg constructor
    // ctrl n to add the getter and setters for all
}
