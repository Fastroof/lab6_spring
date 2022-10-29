package com.fastroof.lab6_spring.service;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.entity.RoomDescription;
import com.fastroof.lab6_spring.entity.User;
import com.fastroof.lab6_spring.pojo.RoomConfigurationPojo;
import com.fastroof.lab6_spring.pojo.RoomCreationRequest;
import com.fastroof.lab6_spring.pojo.RoomDescriptionPojo;
import com.fastroof.lab6_spring.repository.RoomRepository;
import com.fastroof.lab6_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(UserRepository userRepository,
                           RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    @Override
    public boolean addRoom(RoomCreationRequest roomCreationRequest, Principal principal) {
        Optional<User> user = userRepository.findByEmail(principal.getName());

        if (user.isPresent()) {
            Room room = new Room();
            room.setUser(user.get());
            roomRepository.save(room);

            RoomConfigurationPojo roomConfigurationPojo = roomCreationRequest.getConfiguration();
            RoomConfiguration roomConfiguration = new RoomConfiguration();
            roomConfiguration.setId(room.getId());
            roomConfiguration.setArea(roomConfigurationPojo.getArea());
            roomConfiguration.setPrice(roomConfigurationPojo.getPrice());
            roomConfiguration.setBedroomCount(roomConfigurationPojo.getBedroomCount());
            roomConfiguration.setRoom(room);
            room.setConfiguration(roomConfiguration);

            RoomDescriptionPojo roomDescriptionPojo = roomCreationRequest.getDescription();
            RoomDescription roomDescription = new RoomDescription();
            roomDescription.setId(room.getId());
            roomDescription.setDescription(roomDescriptionPojo.getDescription());
            roomDescription.setAddress(roomDescriptionPojo.getAddress());
            roomDescription.setCreationDate(new Date());
            roomDescription.setRoom(room);
            room.setDescription(roomDescription);

            roomRepository.save(room);
            return true;
        }
        return false;
    }

    @Override
    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public boolean updateRoom(Long id, RoomCreationRequest updatedRoom, Principal principal) {
        Optional<User> OptOwner = userRepository.findByEmail(principal.getName());

        if (OptOwner.isPresent()) {
            Optional<Room> OptRoom = roomRepository.findById(id);

            if (OptRoom.isPresent() && OptRoom.get().getUser().equals(OptOwner.get())) {
                Room room = OptRoom.get();

                RoomConfigurationPojo roomConfigurationPojo = updatedRoom.getConfiguration();
                RoomConfiguration roomConfiguration = room.getConfiguration();
                roomConfiguration.setArea(roomConfigurationPojo.getArea());
                roomConfiguration.setPrice(roomConfigurationPojo.getPrice());
                roomConfiguration.setBedroomCount(roomConfigurationPojo.getBedroomCount());

                RoomDescriptionPojo roomDescriptionPojo = updatedRoom.getDescription();
                RoomDescription roomDescription = room.getDescription();
                roomDescription.setDescription(roomDescriptionPojo.getDescription());
                roomDescription.setAddress(roomDescriptionPojo.getAddress());
                roomDescription.setCreationDate(new Date());

                roomRepository.save(room);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteRoom(Room room) {
        try {
            roomRepository.delete(room);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
