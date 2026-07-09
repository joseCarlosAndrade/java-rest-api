package com.restaurant.pizza_hamburguer.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restaurant.pizza_hamburguer.dto.ProductDto;
import com.restaurant.pizza_hamburguer.dto.ProductVariationDto;
import com.restaurant.pizza_hamburguer.entities.Product;
import com.restaurant.pizza_hamburguer.entities.ProductVariation;
import com.restaurant.pizza_hamburguer.enums.Category;

@Component
public class Mapper {

    public ProductDto productToDto(Product product) {
        List<ProductVariationDto> variations = new ArrayList<>();

        product.getProductVariations().stream()
            .map(variation -> variations.add(
                ProductVariationDto.builder()
                    .sizeName(variation.getSizeName())
                    .description(variation.getDescription())
                    .price(variation.getPrice())
                    .available(variation.getAvailable())
                    .build()
            ));

        ProductDto p = ProductDto.builder()
            .name(product.getName())
            .description(product.getDescription())
            .category(product.getCategory().toString()) // 
            .productVariations(variations) 
            .available(product.getAvailable())
            .build();

        return p;

    }
    
    public Product productFromDto(ProductDto product) {
        List<ProductVariation> variations = new ArrayList<>();

        product.productVariations().stream()
            .map(
                variation -> 
                    variations.add(
                        ProductVariation
                            .builder()
                            .sizeName(variation.sizeName())
                            .description(variation.description())
                            .price(variation.price())
                            .available(variation.available())
                            .build()
                        )
                    );

        Product p = Product.builder()
            .name(product.name())
            .description(product.description())
            .category(Category.valueOf(product.category())) // turns string to enum
            .productVariations(variations) 
            .available(product.available())
            .build();

        return p;
    }
}
