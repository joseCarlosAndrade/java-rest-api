package com.restaurant.pizza_hamburguer.entities;

import java.math.BigDecimal;
import java.util.List;

import com.restaurant.pizza_hamburguer.enums.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity // its an entity in the database
@Table(name = "products") // name of the table
@Builder // lombok annotation that reduces boilerplate
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT", comment = "description regarding the product")
    private String description;

    @Enumerated(EnumType.STRING) // if EnumType.ORDINAL, maps enum to int
    private Category category;

    private Boolean available;

    private BigDecimal price;

    // tells its a 1:N relationship. hibernate NEEDS the bidirectional relation even though
    // in the actual database schema the only foreign key will be in product_variations
    // so we have to put the variable name that will be defined as foreign key in the 
    // other table inside the mappedBy field
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariation> productVariations;

    // cascadetype all: make operations transitive, bc in java , if we just delete a parent
    // object, the sub objects will just sit in memory. this is not ideal for database
    // operations. so when we define ALL (or specific for each op: persist, remove, etc), 
    // every operation on the parent object will be propagated to the children
    // if we call ProductRepository.save(Product), all ProductVariation that are children
    // from it will be also saved. same thing with deletion, etc

    // orphanremoval = true: 
    /*Without orphanRemoval = true: If you write myCompany.getEmployees().remove(0), 
    you are simply removing that employee from the Java list. When you save the company, 
    Hibernate will execute an UPDATE employee SET company_id = NULL WHERE id = ?.
    The employee is not deleted; it becomes a "homeless" orphan row in your database.

    With orphanRemoval = true: When you remove an employee from that list, Hibernate 
    tracks the removal and physically fires a DELETE FROM employee WHERE id = ? command 
    to wipe it completely from disk. */
}
