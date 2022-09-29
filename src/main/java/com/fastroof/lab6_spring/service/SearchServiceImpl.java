package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab6_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {

    private final RoomRepository roomRepository;
    private final RoomConfigurationRepository roomConfigurationRepository;

    @Autowired
    public SearchServiceImpl(RoomRepository roomRepository, RoomConfigurationRepository roomConfigurationRepository) {
        this.roomRepository = roomRepository;
        this.roomConfigurationRepository = roomConfigurationRepository;
    }

    @Override
    public List<Room> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price) {
        List<Room> rooms = new ArrayList<>();
        for (RoomConfiguration roomConfiguration :
                roomConfigurationRepository.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price)
        ){
            Optional<Room> room = roomRepository.findByConfiguration(roomConfiguration);
            room.ifPresent(rooms::add);
        }
        return rooms;
    }

    @Override
    public List<Room> findPaginatedByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price, Integer page, Integer size) {
        List<Room> rooms = findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price);
        int startIndex = page * size;
        int endIndex = page * size + size;
        if (startIndex > rooms.size() || endIndex > rooms.size()) {
            startIndex = Math.min(startIndex, rooms.size());
            endIndex = rooms.size();
        }
        return rooms.subList(startIndex, endIndex);
    }
}
