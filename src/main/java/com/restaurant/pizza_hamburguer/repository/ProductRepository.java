package com.restaurant.pizza_hamburguer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.pizza_hamburguer.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // no methods is needed bc the basic ones for CRUD operations are automatically defined
}
