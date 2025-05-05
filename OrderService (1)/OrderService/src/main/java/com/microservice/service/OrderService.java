package com.microservice.service;


import com.microservice.dto.OrderRequest;
import com.microservice.entity.Order;

public interface OrderService {

    Order placeOrder(OrderRequest request);
}
