package com.fastroof.lab6_spring.repository.jdbc;

import com.fastroof.lab6_spring.entity.Order;
import com.fastroof.lab6_spring.repository.OrderRepository;
import com.fastroof.lab6_spring.repository.RoomRepository;
import com.fastroof.lab6_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public JdbcOrderRepository(UserRepository userRepository, RoomRepository roomRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(
                "select * from orders",
                (rs, rowNum) ->
                        new Order(
                                rs.getLong("id"),
                                userRepository.findById(rs.getLong("user_id")).orElse(null),
                                roomRepository.findById(rs.getLong("room_id")).orElse(null),
                                rs.getDate("date_start_contract"),
                                rs.getDate("date_end_contract"),
                                rs.getDouble("price")
                        )
        );
    }
}
