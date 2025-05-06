package com.microservice.service;


import com.microservice.dto.OrderRequest;
import com.microservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeOrder(OrderRequest request);

    Optional<Order> getOrderById(Long id);


    List<Order> getOrdersByCustomerId(Long customerId);

    Order updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);
}
