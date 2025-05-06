package com.microservice.service;

import com.microservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);


    List<Product> getProductsByIds(List<Long> ids);
}
