package com.fastroof.lab6_spring.repository.rowmapper;

import com.fastroof.lab6_spring.entity.RoomDescription;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDescriptionMapper implements RowMapper<RoomDescription> {
    @Override
    public RoomDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RoomDescription(
                        rs.getLong("id"),
                        rs.getString("description"),
                        rs.getString("address"),
                        rs.getDate("creation_date")
                );
    }
}
