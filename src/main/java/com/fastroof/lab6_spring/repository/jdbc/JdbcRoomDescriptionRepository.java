package com.fastroof.lab6_spring.repository.jdbc;


import com.fastroof.lab6_spring.entity.RoomDescription;
import com.fastroof.lab6_spring.repository.RoomDescriptionRepository;
import com.fastroof.lab6_spring.repository.rowmapper.RoomDescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcRoomDescriptionRepository implements RoomDescriptionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRoomDescriptionRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<RoomDescription> getRoomDescriptions() {
        return jdbcTemplate.query("select * from room_descriptions", new RoomDescriptionMapper());
    }

    @Override
    public Optional<RoomDescription> findById(Long id) {
        List<RoomDescription> roomDescriptions = jdbcTemplate.query("select * from room_descriptions where id = ?", new RoomDescriptionMapper(), id);
        if (roomDescriptions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(roomDescriptions.get(0));
    }
}
