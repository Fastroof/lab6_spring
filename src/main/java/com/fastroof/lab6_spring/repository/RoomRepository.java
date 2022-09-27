package com.fastroof.lab6_spring.repository;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.entity.RoomConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository {
    Optional<Room> findByRoomConfiguration(RoomConfiguration roomConfiguration);
    Optional<Room> findById(Long id);
    List<Room> getRooms();
    boolean save(Room room);
    boolean delete(Room room);
    boolean update(Room updatedRoom);
}
