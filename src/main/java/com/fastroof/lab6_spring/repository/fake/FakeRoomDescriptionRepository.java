package com.fastroof.lab6_spring.repository.fake;

import com.fastroof.lab6_spring.entity.RoomDescription;
import com.fastroof.lab6_spring.repository.RoomDescriptionRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeRoomDescriptionRepository implements RoomDescriptionRepository {
    private final List<RoomDescription> roomDescriptions = new ArrayList<>();

    public FakeRoomDescriptionRepository() {
        roomDescriptions.add(new RoomDescription(0L, "Test desc 1", "123 Test st.", new Date()));
        roomDescriptions.add(new RoomDescription(1L, "Test desc 2", "321 Test st.", new Date()));
        roomDescriptions.add(new RoomDescription(2L, "Test desc 3", "456 Test st.", new Date()));
        roomDescriptions.add(new RoomDescription(3L, "Test desc 4", "654 Test st.", new Date()));
        roomDescriptions.add(new RoomDescription(4L, "Test desc 5", "789 Test st.", new Date()));
        roomDescriptions.add(new RoomDescription(5L, "Test desc 6", "987 Test st.", new Date()));
    }

    @Override
    public Optional<RoomDescription> findById(Long id) {
        return roomDescriptions.stream().filter(user -> id.equals(user.getId())).findAny();
    }
}
