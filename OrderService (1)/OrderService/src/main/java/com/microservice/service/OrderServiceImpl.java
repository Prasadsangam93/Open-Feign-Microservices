package com.microservice.service;

import com.microservice.config.CustomerClient;
import com.microservice.config.ProductClient;
import com.microservice.dto.OrderRequest;
import com.microservice.entity.Order;
import com.microservice.model.Customer;
import com.microservice.model.Product;
import com.microservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public Order placeOrder(OrderRequest request) {
        Customer customer = customerClient.getCustomerById(request.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Invalid customer ID");
        }

        // 2. Validate product IDs
        List<Product> products = productClient.getProductsByIds(request.getProducts());
        if (products == null || products.isEmpty()) {
            throw new RuntimeException("No valid products found");
        }

        // 3. Calculate total
        double totalAmount = products.stream().mapToDouble(Product::getPrice).sum();

        // 4. Save order
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductIds(request.getProducts());
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }
}
