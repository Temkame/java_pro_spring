package ru.fokin.spring.service;

import ru.fokin.spring.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String accountNumber, Double balance, String productType, Long userId);
    List<Product> getProductsByUserId(Long userId);
    Product getProductById(Long productId);
}