package com.fastroof.lab6_spring.restcontroller;

import com.fastroof.lab6_spring.entity.Order;
import com.fastroof.lab6_spring.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    private final IndexService indexService;

    @Autowired
    public OrderRestController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/orders")
    Iterable<Order> allOrders() {
        return indexService.getAllOrders();
    }

}
