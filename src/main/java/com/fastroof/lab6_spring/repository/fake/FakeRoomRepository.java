package com.fastroof.lab6_spring.repository.fake;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab6_spring.repository.RoomDescriptionRepository;
import com.fastroof.lab6_spring.repository.RoomRepository;
import com.fastroof.lab6_spring.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeRoomRepository implements RoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    private UserRepository fakeUserRepository;
    private RoomConfigurationRepository fakeRoomConfigurationRepository;
    private RoomDescriptionRepository fakeRoomDescriptionRepository;

    @Autowired
    public void setFakeUserRepository(UserRepository fakeUserRepository) {
        this.fakeUserRepository = fakeUserRepository;
    }

    @Autowired
    public void setFakeRoomConfigurationRepository(RoomConfigurationRepository fakeRoomConfigurationRepository) {
        this.fakeRoomConfigurationRepository = fakeRoomConfigurationRepository;
    }

    @Autowired
    public void setFakeRoomDescriptionRepository(RoomDescriptionRepository fakeRoomDescriptionRepository) {
        this.fakeRoomDescriptionRepository = fakeRoomDescriptionRepository;
    }

    public FakeRoomRepository() {
        setFakeUserRepository(new FakeUserRepository());
        setFakeRoomConfigurationRepository(new FakeRoomConfigurationRepository());
        setFakeRoomDescriptionRepository(new FakeRoomDescriptionRepository());
        rooms.add(new Room(0L, fakeRoomConfigurationRepository.getRoomConfigurations().get(0), fakeRoomDescriptionRepository.getRoomDescriptions().get(0), fakeUserRepository.getUsers().get(0)));
        rooms.add(new Room(1L, fakeRoomConfigurationRepository.getRoomConfigurations().get(1), fakeRoomDescriptionRepository.getRoomDescriptions().get(1), fakeUserRepository.getUsers().get(1)));
        rooms.add(new Room(2L, fakeRoomConfigurationRepository.getRoomConfigurations().get(2), fakeRoomDescriptionRepository.getRoomDescriptions().get(2), fakeUserRepository.getUsers().get(1)));
        rooms.add(new Room(3L, fakeRoomConfigurationRepository.getRoomConfigurations().get(3), fakeRoomDescriptionRepository.getRoomDescriptions().get(3), fakeUserRepository.getUsers().get(1)));
        rooms.add(new Room(4L, fakeRoomConfigurationRepository.getRoomConfigurations().get(4), fakeRoomDescriptionRepository.getRoomDescriptions().get(4), fakeUserRepository.getUsers().get(1)));
        rooms.add(new Room(5L, fakeRoomConfigurationRepository.getRoomConfigurations().get(5), fakeRoomDescriptionRepository.getRoomDescriptions().get(5), fakeUserRepository.getUsers().get(1)));
    }

    @Override
    public Optional<Room> findByRoomConfiguration(RoomConfiguration roomConfiguration) {
        return rooms.stream().filter(room -> room.getConfiguration().equals(roomConfiguration)).findAny();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return rooms.stream().filter(room -> room.getId().equals(id)).findAny();
    }

    @Override
    public boolean save(Room room) {
        rooms.add(room);
        return true;
    }

    @Override
    public boolean delete(Room room) {
        rooms.remove(room);
        return true;
    }

    @Override
    public boolean update(Room updatedRoom) {
        Optional<Room> roomOptional = findById(updatedRoom.getId());
        if (roomOptional.isPresent()) {
            rooms.remove(roomOptional.get());
            rooms.add(updatedRoom);
        }
        return false;
    }
}
