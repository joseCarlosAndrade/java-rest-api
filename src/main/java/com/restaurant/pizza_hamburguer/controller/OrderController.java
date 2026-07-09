package com.restaurant.pizza_hamburguer.controller;

import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pizza_hamburguer.dto.ApiFailureDto;
import com.restaurant.pizza_hamburguer.dto.OrderRequestDto;
import com.restaurant.pizza_hamburguer.dto.OrdersResponseDto;
import com.restaurant.pizza_hamburguer.entities.Order;
import com.restaurant.pizza_hamburguer.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/orders") // versioning order
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDto request) {
        // call service
        // we dont need to handle logic and errors here, the global exception handler does that
        Order order = orderService.createOrder(request);


        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/")
    public ResponseEntity<OrdersResponseDto> getMethodName() {
        OrdersResponseDto orders=  orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
    
}
