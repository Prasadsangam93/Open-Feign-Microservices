package com.microservice.model;


import com.microservice.entity.Order;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    private String name;
    private Double price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
