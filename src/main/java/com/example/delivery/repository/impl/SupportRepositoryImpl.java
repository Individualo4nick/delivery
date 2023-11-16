package com.example.delivery.repository.impl;

import com.example.delivery.entity.Client;
import com.example.delivery.entity.Support;
import com.example.delivery.repository.SupportRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupportRepositoryImpl implements SupportRepository {
    private final JdbcTemplate jdbcTemplate;

    public SupportRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Support> getAllSupport() {
        return jdbcTemplate.query("SELECT * FROM support", new BeanPropertyRowMapper<>(Support.class));
    }

    @Override
    public Support getSupportById(Integer id) {
        Support support = jdbcTemplate.queryForObject("SELECT id, name, surname, salary, number FROM support WHERE id = " + id.toString(), new BeanPropertyRowMapper<>(Support.class));
        List<Client> clients = jdbcTemplate.query("SELECT * FROM client WHERE id = (SELECT client_id FROM chat_with_support WHERE support_id=" + support.getId() + ")", new BeanPropertyRowMapper<>(Client.class));
        support.setClients(clients);
        return support;
    }

    @Override
    public void createSupport(Support support) {
        jdbcTemplate.update("INSERT INTO support(name, surname, salary, number) VALUES (?, ?, ?, ?)", support.getName(), support.getSurname(), support.getSalary(), support.getNumber());
    }

    @Override
    public void updateSupport(Support support) {
        jdbcTemplate.update("UPDATE support SET name = ?, surname = ?, salary = ?, number = ? WHERE id = " + support.getId(), support.getName(), support.getSurname(), support.getSalary(), support.getNumber());
    }

    @Override
    public void deleteSupportById(Integer id) {
        jdbcTemplate.update("DELETE FROM chat_with_support WHERE support_id = "+id.toString());
        jdbcTemplate.update("DELETE FROM support WHERE id = " + id.toString());
    }

    @Override
    public void deleteAllSupport() {
        jdbcTemplate.update("DELETE FROM chat_with_support");
        jdbcTemplate.update("TRUNCATE TABLE support");
    }

    @Override
    public List<Support> orderBy(String field, String asc) {
        return jdbcTemplate.query("SELECT * FROM support ORDER BY " + field + ' ' + asc, new BeanPropertyRowMapper<>(Support.class));
    }
}
