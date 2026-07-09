package com.restaurant.pizza_hamburguer.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.pizza_hamburguer.entities.Order;

import jakarta.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // native sql (not portable)
    @Query(value = "SELECT * FROM orders WHERE id = ?1", nativeQuery = true)
    Optional<Order> findOrderById(
        @Param("id") Long id // using named params to avoid sql injections
    ); 

    //  jpql: 'Order' matches the Java Class entity name, and 'productId' matches the class attribute name
    @Query("SELECT o FROM Order o WHERE o.productId = :prodId")
    List<Order> findOrdersByProduct(Long prodId);

    // need those annotations to make updates
    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET price = ?1 WHERE id = ?2", nativeQuery =true)
    int updateOrderPrice(
        @Param("newPrice") BigDecimal newPrice,
        @Param("orderId") Long orderId
    );
        
}
