package com.restaurant.pizza_hamburguer.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ApiFailureDto(
    LocalDateTime timestamp,
    String message,
    String code
) {
} 
