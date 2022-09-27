package com.fastroof.lab6_spring.repository.jdbc;


import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab6_spring.repository.rowmapper.RoomConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcRoomConfigurationRepository implements RoomConfigurationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRoomConfigurationRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<RoomConfiguration> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price) {
        String statement = "select * from room_configurations where area = ? and bedroom_count = ? and price = ?";
        if (area == null) {
            statement = statement.replace("area = ?", "true");
        } else {
            statement = statement.replace("area = ?", "area = " + area);
        }
        if (bedroomCount == null) {
            statement = statement.replace("bedroom_count = ?", "true");
        } else {
            statement = statement.replace("bedroom_count = ?", "bedroom_count = " + bedroomCount);
        }
        if (price == null) {
            statement = statement.replace("price = ?", "true");
        } else {
            statement = statement.replace("price = ?", "price = " + price);
        }
        return jdbcTemplate.query(
                statement,
                new RoomConfigurationMapper()
        );
    }

    @Override
    public List<RoomConfiguration> getRoomConfigurations() {
        return jdbcTemplate.query("select * from room_configurations", new RoomConfigurationMapper());
    }

    @Override
    public Optional<RoomConfiguration> findById(Long id) {
        List<RoomConfiguration> roomConfigurations = jdbcTemplate.query(
                "select * from room_configurations where id = ?",
                new RoomConfigurationMapper(), id);
        if (roomConfigurations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(roomConfigurations.get(0));
    }
}

