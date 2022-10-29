package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Order;
import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.repository.OrderRepository;
import com.fastroof.lab6_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    private final RoomRepository roomRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public IndexServiceImpl(RoomRepository roomRepository, OrderRepository orderRepository) {
        this.roomRepository = roomRepository;
        this.orderRepository = orderRepository;
    }

    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.getAll();
    }
}
