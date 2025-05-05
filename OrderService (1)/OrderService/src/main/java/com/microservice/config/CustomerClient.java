package com.microservice.config;


import com.microservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "http://localhost:8092")
public interface CustomerClient {



    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable("id") Long id);
}
