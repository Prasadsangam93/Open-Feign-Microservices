package com.microservice.entity;


import com.microservice.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ORDERTABLEFI")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;

    @ElementCollection  // Stores as a separate table of product IDs
    private List<Long> productIds;

    private Double totalAmount;

    
}

