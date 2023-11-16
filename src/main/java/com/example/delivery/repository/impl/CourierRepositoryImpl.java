package com.example.delivery.repository.impl;

import com.example.delivery.entity.*;
import com.example.delivery.repository.CourierRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourierRepositoryImpl implements CourierRepository {
    private final JdbcTemplate jdbcTemplate;

    public CourierRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Courier> getAllCourier() {
        return jdbcTemplate.query("SELECT id, name, surname, salary FROM courier", new BeanPropertyRowMapper<>(Courier.class));
    }

    @Override
    public Courier getCourierById(Integer id) {
        Courier courier = jdbcTemplate.queryForObject("SELECT id, name, surname, salary, order_count FROM courier WHERE id = " + id.toString(), new BeanPropertyRowMapper<>(Courier.class));
        List<Transport> transports = jdbcTemplate.query("SELECT id, title FROM transport WHERE courier_id = " + id, new BeanPropertyRowMapper<>(Transport.class));
        courier.setTransports(transports);
        List<Client> clients = jdbcTemplate.query("SELECT * FROM client WHERE id = (SELECT client_id FROM client_and_courier WHERE courier_id=" + courier.getId() + ")", new BeanPropertyRowMapper<>(Client.class));
        courier.setClients(clients);
        return courier;
    }

    @Override
    public void createCourier(Courier courier) {
        jdbcTemplate.update("INSERT INTO courier(name, surname, salary) VALUES (?, ?, ?)", courier.getName(), courier.getSurname(), courier.getSalary());
    }

    @Override
    public void updateCourier(Courier courier) {
        jdbcTemplate.update("UPDATE courier SET name = ?, surname = ?, salary = ? WHERE id = " + courier.getId(), courier.getName(), courier.getSurname(), courier.getSalary());
    }

    @Override
    public void deleteCourierById(Integer id) {
        jdbcTemplate.update("UPDATE clientorder SET courier_id = null WHERE courier_id = "+id.toString());
        jdbcTemplate.update("UPDATE transport SET courier_id = null WHERE courier_id = "+id);
        jdbcTemplate.update("DELETE FROM client_and_courier WHERE courier_id = "+ id);
        jdbcTemplate.update("DELETE FROM courier WHERE id = " + id);
    }

    @Override
    public void deleteAllCourier() {
        jdbcTemplate.update("UPDATE clientorder SET courier_id = null");
        jdbcTemplate.update("UPDATE transport SET courier_id = null");
        jdbcTemplate.update("DELETE FROM client_and_courier");
        jdbcTemplate.update("TRUNCATE TABLE courier");
    }

    @Override
    public List<Courier> orderBy(String field, String asc) {
        return jdbcTemplate.query("SELECT id, name, surname FROM courier ORDER BY " + field + ' ' + asc, new BeanPropertyRowMapper<>(Courier.class));
    }

    @Override
    public void createTransport(Transport transport) {
        jdbcTemplate.update("INSERT INTO transport(title, courier_id) VALUES (?, ?)", transport.getTitle(), transport.getCourier_id());
    }

    @Override
    public void deleteTransport(Integer id) {
        jdbcTemplate.update("DELETE FROM transport WHERE id = " + id.toString());
    }

    @Override
    public int getCountCourier() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM courier", Integer.class);
    }
}
