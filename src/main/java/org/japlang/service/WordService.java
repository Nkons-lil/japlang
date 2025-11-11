package org.japlang.service;

import org.japlang.model.Word;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final JdbcTemplate jdbcTemplate;

    public WordService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Word> findAll() {
        String sql = "SELECT id, front_side AS frontSide, back_side AS backSide FROM words";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Word.class));
    }

    public Word findById(Long id) {
        String sql = "SELECT id, front_side AS frontSide, back_side AS backSide FROM words WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Word.class), id);
    }

    public Word findRandom() {
        String sql = "SELECT id, front_side AS frontSide, back_side AS backSide FROM words ORDER BY RANDOM() LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Word.class));
    }

    public void save(Word word) {
        String sql = "INSERT INTO words (front_side, back_side) VALUES (?, ?)";
        jdbcTemplate.update(sql, word.getFrontSide(), word.getBackSide());
    }

    public void update(Word word) {
        String sql = "UPDATE words SET front_side = ?, back_side = ? WHERE id = ?";
        jdbcTemplate.update(sql, word.getFrontSide(), word.getBackSide(), word.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM words WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
