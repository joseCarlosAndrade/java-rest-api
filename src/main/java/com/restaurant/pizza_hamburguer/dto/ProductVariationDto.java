package com.restaurant.pizza_hamburguer.dto;

import java.math.BigDecimal;

public record ProductVariationDto (
    String sizeName,
    String description,
    BigDecimal price,
    Boolean available
){

}