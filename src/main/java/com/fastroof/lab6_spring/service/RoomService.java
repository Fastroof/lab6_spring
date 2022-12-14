package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.pojo.RoomCreationRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.security.Principal;

@Service
public interface RoomService {
    boolean addRoom(RoomCreationRequest roomCreationRequest, Principal principal);
    Room getRoom(Long id);
    boolean updateRoom(Long id, @Valid RoomCreationRequest updatedRoom, Principal principal);
    boolean deleteRoom(Room room);
}
