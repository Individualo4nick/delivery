package com.example.delivery.repository.impl;

import com.example.delivery.entity.*;
import com.example.delivery.repository.ClientOrderRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientOrderRepositoryImpl implements ClientOrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClientOrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ClientOrder> getAllOrder() {
        return jdbcTemplate.query("SELECT id, title, description, total, stage FROM clientorder", new BeanPropertyRowMapper<>(ClientOrder.class));
    }

    @Override
    public ClientOrder getOrderById(Integer id) {
        ClientOrder clientOrder = jdbcTemplate.queryForObject("SELECT id, title, description, total, stage, courier_id, client_id FROM clientorder WHERE id = " + id.toString(), new BeanPropertyRowMapper<>(ClientOrder.class));
        List<Product> products = jdbcTemplate.query("SELECT title, description, price FROM product WHERE order_id = " + clientOrder.getId().toString(), new BeanPropertyRowMapper<>(Product.class));
        clientOrder.setProducts(products);
        Client client = jdbcTemplate.queryForObject("SELECT name, surname, address FROM client WHERE id = " + clientOrder.getClient_id().toString(), new BeanPropertyRowMapper<>(Client.class));
        clientOrder.setClient(client);
        Courier courier = jdbcTemplate.queryForObject("SELECT id, name, surname FROM courier WHERE id = " + clientOrder.getCourier_id().toString(), new BeanPropertyRowMapper<>(Courier.class));
        clientOrder.setCourier(courier);
        return clientOrder;
    }

    @Override
    public void createOrder(ClientOrder clientOrder) {
        jdbcTemplate.update("INSERT INTO clientorder(title, description, total, stage, courier_id, client_id) VALUES (?, ?, ?, ?, ?, ?)", clientOrder.getTitle(), clientOrder.getDescription(), clientOrder.getTotal(), clientOrder.getStage(), clientOrder.getCourier_id(), clientOrder.getClient_id());
        Integer order_id = jdbcTemplate.queryForObject("SELECT id FROM clientorder WHERE title = '" + clientOrder.getTitle() + "' AND description = '" + clientOrder.getDescription() + "' AND total = " + clientOrder.getTotal() + " AND stage = '" + clientOrder.getStage() + "'", Integer.class);
        for (Integer product_id : clientOrder.getProducts_id())
            jdbcTemplate.update("UPDATE product SET order_id = ? WHERE id = ?", order_id, product_id);
    }

    @Override
    public void updateOrder(ClientOrder clientOrder) {
        jdbcTemplate.update("UPDATE clientorder SET title = ?, description = ?, total = ?, stage = ?, courier_id = ?, client_id = ? WHERE id = " + clientOrder.getId(), clientOrder.getTitle(), clientOrder.getDescription(), clientOrder.getTotal(), clientOrder.getStage(), clientOrder.getCourier_id(), clientOrder.getClient_id());
    }

    @Override
    public void deleteOrderById(Integer id) {
        jdbcTemplate.update("DELETE FROM product WHERE order_id = "+ id.toString());
        jdbcTemplate.update("DELETE FROM clientorder WHERE id = " + id);
    }

    @Override
    public void deleteAllOrder() {
        jdbcTemplate.update("TRUNCATE TABLE product");
        jdbcTemplate.update("TRUNCATE TABLE clientorder");
    }

    @Override
    public List<ClientOrder> orderBy(String field, String asc) {
        return jdbcTemplate.query("SELECT * FROM clientorder ORDER BY " + field + ' ' + asc, new BeanPropertyRowMapper<>(ClientOrder.class));
    }

    @Override
    public double sumProduct(String ids) {
        return jdbcTemplate.queryForObject("SELECT SUM(price) FROM product WHERE id IN (" + ids + ")", Double.class);
    }

    @Override
    public List<Product> getAllProduct() {
        return jdbcTemplate.query("SELECT id, title, description, price FROM product WHERE order_id IS null", new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public void createProduct(Product product) {
        jdbcTemplate.update("INSERT INTO product (title, description, price) VALUES (?, ?, ?)", product.getTitle(), product.getDescription(), product.getPrice());
    }

}
