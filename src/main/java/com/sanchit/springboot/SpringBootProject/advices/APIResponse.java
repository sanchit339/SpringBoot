package com.sanchit.springboot.SpringBootProject.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIResponse <T>{

    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy")
    private LocalDateTime timeStamp;
    // API response will either have the data field or the Error Field
    private T data;
    private APIError apiError;

    public APIResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    public APIResponse(T data) {
        this(); // this = calling default constructor
        this.data = data;
    }


    public APIResponse(APIError apiError) {
        this();
        this.apiError = apiError;
    }
}
