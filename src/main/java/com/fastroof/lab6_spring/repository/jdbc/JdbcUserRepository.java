package com.fastroof.lab6_spring.repository.jdbc;


import com.fastroof.lab6_spring.entity.User;
import com.fastroof.lab6_spring.repository.UserRepository;
import com.fastroof.lab6_spring.repository.rowmapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from users", new UserMapper());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query("select * from users where email = ?", new UserMapper(), email);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(0));
    }

    public Optional<User> findById(Long id) {
        List<User> users = jdbcTemplate.query("select * from users where id = ?", new UserMapper(), id);
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(0));
    }

    @Override
    @Transactional
    public void save(User user) {
        jdbcTemplate.update(
                "insert into users (email, password, full_name, provider) values(?,?,?,?)",
                user.getEmail(), user.getPassword(), user.getFullName(), user.getProvider().name());
    }
}
