package com.restaurant.pizza_hamburguer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.pizza_hamburguer.entities.ProductVariation;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {
    
    // jpql language. i dont like it
    @Query("SELECT pv FROM ProductVariation pv WHERE pv.product.id = :productId AND pv.id = :productVariationId")
    Optional<ProductVariation> findByProductIdAndProductVariationId(@Param("productId") Long productId, @Param("productVariationId") Long productVariationId);

    // Optional<type> returns either null or type
}
