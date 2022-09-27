package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<Room> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price);
    List<Room> findPaginatedByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price, Integer page, Integer size);
}
