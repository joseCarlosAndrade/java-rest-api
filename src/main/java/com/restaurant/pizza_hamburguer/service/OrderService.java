package com.restaurant.pizza_hamburguer.service;

import java.util.List;

import com.restaurant.pizza_hamburguer.dto.OrderRequestDto;
import com.restaurant.pizza_hamburguer.dto.OrdersResponseDto;
import com.restaurant.pizza_hamburguer.dto.ProductDto;
import com.restaurant.pizza_hamburguer.entities.Order;

public interface OrderService {

    public Boolean createProduct(ProductDto productDto);
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Long id);

    public Order createOrder(OrderRequestDto orderDto);
    public OrdersResponseDto getAllOrders();
}
