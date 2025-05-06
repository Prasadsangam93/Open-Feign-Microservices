package com.microservice.config;


import com.microservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "product", url = "http://localhost:8093")
public interface ProductClient  {

    @PostMapping("/api/products/details")
    List<Product> getProductsByIds(@RequestBody List<Long> ids);

}
