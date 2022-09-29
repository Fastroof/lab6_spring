package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Order;
import com.fastroof.lab6_spring.entity.Room;
import org.springframework.stereotype.Service;

@Service
public interface IndexService {
    Iterable<Room> getAllRooms();
    Iterable<Order> getAllOrders();
}