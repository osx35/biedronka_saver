package com.example.biedronka_saver.mapper;

import com.example.biedronka_saver.model.entity.Product;
import com.example.biedronka_saver.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected ProductRepository productRepository;

    @Named("toProduct")
    public Product toProduct(String productName) {
        if (productName == null) return null;

        return productRepository.findByName(productName)
                .orElseGet(() -> {
                    Product newProduct = Product.builder()
                            .name(productName)
                            .build();
                    return productRepository.save(newProduct);
                });
    }
}

