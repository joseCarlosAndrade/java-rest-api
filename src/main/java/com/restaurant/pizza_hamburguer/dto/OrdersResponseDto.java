package com.restaurant.pizza_hamburguer.dto;

import java.util.List;

import com.restaurant.pizza_hamburguer.entities.Order;

import lombok.Builder;

@Builder
public record OrdersResponseDto(
    int amount,
    List<OrderRequestDto> orders
) {
}
