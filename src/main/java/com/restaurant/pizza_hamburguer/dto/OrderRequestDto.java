package com.restaurant.pizza_hamburguer.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record OrderRequestDto(
    Long productId,
    Integer quantity,
    BigDecimal totalCost
) {
    
}
