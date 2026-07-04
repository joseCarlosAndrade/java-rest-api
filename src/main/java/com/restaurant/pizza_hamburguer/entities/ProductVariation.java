package com.restaurant.pizza_hamburguer.entities;

import java.math.BigDecimal;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_variations")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sizeName;

    private String description;

    private Boolean available;

    private BigDecimal price;

    @ManyToAny
    @JoinColumn(name = "product_id") // acual field in the db
    private Product product; // variable used in mappedBy
}
