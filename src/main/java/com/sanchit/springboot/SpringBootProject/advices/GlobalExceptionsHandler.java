package com.sanchit.springboot.SpringBootProject.advices;

import com.sanchit.springboot.SpringBootProject.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        APIError apiError = APIError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleInternalServerError(Exception exception){
        APIError apiError = APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        APIError apiError = APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid Input fields")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    // internal use method bellow all the methods
    private ResponseEntity<APIResponse<?>> buildErrorResponseEntity(APIError apiError){
        return new ResponseEntity<>(new APIResponse(apiError) , apiError.getStatus());
    }
}
