package com.fastroof.lab6_spring.repository.jdbc;

import com.fastroof.lab6_spring.entity.Room;
import com.fastroof.lab6_spring.entity.RoomConfiguration;
import com.fastroof.lab6_spring.entity.RoomDescription;
import com.fastroof.lab6_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab6_spring.repository.RoomDescriptionRepository;
import com.fastroof.lab6_spring.repository.RoomRepository;
import com.fastroof.lab6_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcRoomRepository implements RoomRepository {
    private final TransactionTemplate transactionTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final RoomConfigurationRepository roomConfigurationRepository;
    private final RoomDescriptionRepository roomDescriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public JdbcRoomRepository(TransactionTemplate transactionTemplate, RoomConfigurationRepository roomConfigurationRepository,
                              RoomDescriptionRepository roomDescriptionRepository,
                              UserRepository userRepository,
                              DataSource dataSource) {
        this.transactionTemplate = transactionTemplate;
        this.roomConfigurationRepository = roomConfigurationRepository;
        this.roomDescriptionRepository = roomDescriptionRepository;
        this.userRepository = userRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Room> findByRoomConfiguration(RoomConfiguration roomConfiguration) {
        List<Room> rooms = jdbcTemplate.query(
                "select * from rooms where configuration_id = ?",
                this::mapRow, roomConfiguration.getId()
        );
        if (rooms.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(rooms.get(0));
    }

    @Override
    public Optional<Room> findById(Long id) {
        List<Room> rooms = jdbcTemplate.query(
                "select * from rooms where id = ?",
                this::mapRow, id
        );
        if (rooms.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(rooms.get(0));
    }

    @Override
    public List<Room> getRooms() {
        return jdbcTemplate.query("select * from rooms", this::mapRow);
    }

    @Override
    public boolean save(Room room) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            // Room Configuration
            RoomConfiguration roomConfiguration = room.getConfiguration();
            KeyHolder key1 = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                        PreparedStatement ps = con.prepareStatement(
                                "insert into room_configurations (area, bedroom_count, price) values(?,?,?)",
                                Statement.RETURN_GENERATED_KEYS
                        );
                        ps.setDouble(1, roomConfiguration.getArea());
                        ps.setInt(2, roomConfiguration.getBedroomCount());
                        ps.setInt(3, roomConfiguration.getPrice());
                        return ps;
                    }, key1);

            // Room Description
            RoomDescription roomDescription = room.getDescription();
            KeyHolder key2 = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        "insert into room_descriptions (description, address, creation_date) values(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, roomDescription.getDescription());
                ps.setString(2, roomDescription.getAddress());
                ps.setDate(3, new Date(roomDescription.getCreationDate().getTime()));
                return ps;
            }, key2);

            // Room
            jdbcTemplate.update(
                    "insert into rooms (configuration_id, description_id, user_id) values(?,?,?)",
                    key1.getKeyList().get(0).get("id"), key1.getKeyList().get(0).get("id"), room.getUser().getId());
            return true;
        }));
    }

    @Override
    public boolean delete(Room room) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            jdbcTemplate.update("delete from rooms where id = " + room.getId());
            jdbcTemplate.update("delete from room_configurations where id = " + room.getConfiguration().getId());
            jdbcTemplate.update("delete from room_descriptions where id = " + room.getDescription().getId());
            return true;
        }));
    }

    @Override
    public boolean update(Room updatedRoom) {
        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            Optional<Room> optionalRoom = findById(updatedRoom.getId());
            if (optionalRoom.isEmpty()) {
                return false;
            }
            Room room = optionalRoom.get();
            // Room Configuration
            Long roomConfigurationId = room.getConfiguration().getId();
            jdbcTemplate.update("update room_configurations set area = '" +
                    updatedRoom.getConfiguration().getArea() + "' where id = " + roomConfigurationId);
            jdbcTemplate.update("update room_configurations set price = '" +
                    updatedRoom.getConfiguration().getPrice() + "' where id = " + roomConfigurationId);
            jdbcTemplate.update("update room_configurations set bedroom_count = '" +
                    updatedRoom.getConfiguration().getBedroomCount() + "' where id = " + roomConfigurationId);

            // Room Description
            Long roomDescriptionId = room.getDescription().getId();
            jdbcTemplate.update("update room_descriptions set address = '" +
                    updatedRoom.getDescription().getAddress() + "' where id = " + roomDescriptionId);
            jdbcTemplate.update("update room_descriptions set description = '" +
                    updatedRoom.getDescription().getDescription() + "' where id = " + roomDescriptionId);
            jdbcTemplate.update("update room_descriptions set creation_date = '" +
                    new Date(new java.util.Date().getTime()) + "' where id = " + roomDescriptionId);

            return true;
        }));
    }

    private Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Room(
                rs.getLong("id"),
                roomConfigurationRepository.findById(rs.getLong("configuration_id")).orElse(null),
                roomDescriptionRepository.findById(rs.getLong("description_id")).orElse(null),
                userRepository.findById(rs.getLong("user_id")).orElse(null)
        );
    }


}
