package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> getAll();
}
