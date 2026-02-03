package com.example.checkoutretail.repository;

import com.example.checkoutretail.model.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Catalog {

    private static final Map<String, Product> PRODUCTS = new HashMap<>();

    static {
        PRODUCTS.put("p-001", new Product("p-001", "Producto 1", new BigDecimal("10000")));
        PRODUCTS.put("p-003", new Product("p-003", "Producto 3", new BigDecimal("5000")));
        PRODUCTS.put("p-010", new Product("p-010", "Producto 10", new BigDecimal("2000")));
    }

    public static Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(PRODUCTS.get(sku));
    }
}
