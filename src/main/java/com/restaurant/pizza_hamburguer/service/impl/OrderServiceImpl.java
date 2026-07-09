package com.restaurant.pizza_hamburguer.service.impl;

import com.restaurant.pizza_hamburguer.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pizza_hamburguer.dto.OrderRequestDto;
import com.restaurant.pizza_hamburguer.dto.OrdersResponseDto;
import com.restaurant.pizza_hamburguer.dto.ProductDto;
import com.restaurant.pizza_hamburguer.dto.ProductVariationDto;
import com.restaurant.pizza_hamburguer.entities.Order;
import com.restaurant.pizza_hamburguer.entities.Product;
import com.restaurant.pizza_hamburguer.repository.ProductRepository;
import com.restaurant.pizza_hamburguer.repository.ProductVariationRepository;
import com.restaurant.pizza_hamburguer.service.OrderService;
import com.restaurant.pizza_hamburguer.utils.Mapper;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRequestDto orderRequestDto;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ProductVariationRepository productVariationRepository;

    private final Mapper mapper;

    // @Autowired
    public OrderServiceImpl(ProductRepository productRepository,
                          ProductVariationRepository productVariationRepository,
                          Mapper mapper, OrderRepository orderRepository, OrderRequestDto orderRequestDto) {
        this.productRepository = productRepository;
        this.productVariationRepository = productVariationRepository;
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.orderRequestDto = orderRequestDto;
    }

    public Boolean createProduct(ProductDto productDto) {

        if (!productDto.available() && productDto.productVariations().stream().anyMatch(ProductVariationDto::available)) {
            throw new RuntimeException("A variação de tamanho não pode estar disponível se o produto não está.");
        }
        Product product = mapper.productFromDto(productDto);

        productRepository.save(product);
        
        return true;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        products.stream().map(
            product -> productsDto.add(
                mapper.productToDto(product)
            )
        );

        return productsDto;
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return mapper.productToDto(product);
    }

    public Order createOrder(OrderRequestDto orderDto) {
        Order order = Order.builder()
            .quantity(orderDto.quantity())
            .totalCost(orderDto.totalCost())
            .build();
        
        return orderRepository.save(order);
    }

    public OrdersResponseDto getAllOrders() {
        var orders = orderRepository.findAll();

        // OrdersResponseDto ordersDto = new OrdersResponseDto(0, new ArrayList<>());
        
        List<OrderRequestDto> ordersRequestDto = new ArrayList<>();

        orders.stream().map(
            order -> ordersRequestDto.add(
                OrderRequestDto.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .totalCost(order.getTotalCost())
                .build()
            ));
        
        OrdersResponseDto ordersDto = new OrdersResponseDto(ordersRequestDto.size(), ordersRequestDto);
            
        return ordersDto;
    }
}
