package com.restaurant.pizza_hamburguer.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductVariationDto (
    String sizeName,
    String description,
    BigDecimal price,
    Boolean available
){

}