package com.restaurant.pizza_hamburguer.dto;

import java.util.List;

public record ProductDto (
    String name,
    String description,
    String category,
    List<ProductVariationDto> productVariations,
    Boolean available
){}