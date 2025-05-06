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
import java.util.Optional;

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

    @Override
    public Optional<Order> getOrderById(Long id) {
        return  orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order updateOrder(Long id, OrderRequest orderRequest) {

        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            // Validate products via ProductClient
            List<Product> products = productClient.getProductsByIds(orderRequest.getProducts());
            if (products == null || products.isEmpty()) {
                throw new RuntimeException("No valid products found");
            }

            // Update fields
            existingOrder.setProductIds(orderRequest.getProducts());
            double totalAmount = products.stream().mapToDouble(Product::getPrice).sum();
            existingOrder.setTotalAmount(totalAmount);

            return orderRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order with ID " + id + " does not exist.");
        }
        orderRepository.deleteById(id);

    }


}
