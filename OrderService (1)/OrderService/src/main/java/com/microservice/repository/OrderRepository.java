package com.microservice.repository;

import com.microservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {



    List<Order> findByCustomerId(Long customerId);
}
