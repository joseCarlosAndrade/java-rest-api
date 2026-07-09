package com.restaurant.pizza_hamburguer.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restaurant.pizza_hamburguer.dto.ApiFailureDto;
import com.restaurant.pizza_hamburguer.exception.ResourceAlreadyExists;
import com.restaurant.pizza_hamburguer.exception.ResourceNotFoundException;

// global intercepter that catches runtime errors
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiFailureDto> handleNotFound(ResourceNotFoundException ex) {
        ApiFailureDto error = ApiFailureDto.builder()
            .message(ex.getMessage())
            .code("CUSTOMCODE09")
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(error);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ApiFailureDto> handleAlreadyExists(ResourceAlreadyExists ex){
        ApiFailureDto error = ApiFailureDto.builder()
            .message(ex.getMessage())
            .code("CUSTOMCODE01")
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(error);
    }
}
