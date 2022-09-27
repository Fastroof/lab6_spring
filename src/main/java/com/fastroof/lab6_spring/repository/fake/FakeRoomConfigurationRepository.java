package com.fastroof.lab6_spring.repository.fake;

import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.repository.RoomConfigurationRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Setter
public class FakeRoomConfigurationRepository implements RoomConfigurationRepository {
    private final List<RoomConfiguration> roomConfigurations = new ArrayList<>();

    public FakeRoomConfigurationRepository() {
        roomConfigurations.add(new RoomConfiguration(0L, 80.0, 2, 580));
        roomConfigurations.add(new RoomConfiguration(1L, 50.0, 1, 450));
        roomConfigurations.add(new RoomConfiguration(2L, 60.0, 1, 450));
        roomConfigurations.add(new RoomConfiguration(3L, 70.0, 2, 850));
        roomConfigurations.add(new RoomConfiguration(4L, 65.0, 1, 650));
        roomConfigurations.add(new RoomConfiguration(5L, 40.0, 1, 250));
    }

    @Override
    public List<RoomConfiguration> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price) {
        return roomConfigurations.stream().filter(roomConfiguration -> ((area == null) || (roomConfiguration.getArea().equals(area))) &&
                ((bedroomCount == null) || (roomConfiguration.getBedroomCount().equals(bedroomCount))) &&
                ((price == null) || (roomConfiguration.getPrice().equals(price)))
        ).toList();
    }

    @Override
    public Optional<RoomConfiguration> findById(Long id) {
        return roomConfigurations.stream().filter(user -> id.equals(user.getId())).findAny();
    }
}
