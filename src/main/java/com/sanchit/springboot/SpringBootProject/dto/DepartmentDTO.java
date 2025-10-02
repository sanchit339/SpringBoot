package com.sanchit.springboot.SpringBootProject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank
    @Size(min = 3 , max = 8)
    private String title;
    @JsonProperty("isActive")
    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
