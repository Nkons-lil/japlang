package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Получение списка пользователей по имени
    public List<String> getUserNamesByName(String name) {
        String sql = "SELECT name FROM users WHERE name = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{name},
                (rs, rowNum) -> rs.getString("name")
        );
    }

    // Вставка нового пользователя
    public int addUser(String name) {
        String sql = "INSERT INTO users(name) VALUES (?)";
        return jdbcTemplate.update(sql, name);
    }
}
