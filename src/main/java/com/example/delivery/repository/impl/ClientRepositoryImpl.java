package com.example.delivery.repository.impl;

import com.example.delivery.entity.CardData;
import com.example.delivery.entity.TypeSale;
import com.example.delivery.entity.User;
import com.example.delivery.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.delivery.entity.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> getAllClients() {
        return jdbcTemplate.query("SELECT id, name, surname, address, order_count FROM client", new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public Client getClientById(Integer id) {
        Client client = jdbcTemplate.queryForObject("SELECT id, name, surname, address, type_sale_id, card_data_id FROM client WHERE id = " + id.toString(), new BeanPropertyRowMapper<>(Client.class));
        if (client.getCardDataId() != null) {
            CardData cardData = jdbcTemplate.queryForObject("SELECT card_number, card_date FROM card_data WHERE id = " + client.getCardDataId().toString(), new BeanPropertyRowMapper<>(CardData.class));
            client.setCardData(cardData);
        }
        TypeSale typeSale = jdbcTemplate.queryForObject("SELECT title FROM type_sale WHERE id = " + client.getTypeSaleId().toString(), new BeanPropertyRowMapper<>(TypeSale.class));
        client.setTypeSale(typeSale);
        return client;
    }

    @Override
    public void createClient(Client client) {
        jdbcTemplate.update("INSERT INTO client(name, surname, address) VALUES (?, ?, ?)", client.getName(), client.getSurname(), client.getAddress());
    }

    @Override
    public void updateClient(Client client) {
        jdbcTemplate.update("UPDATE client SET name = ?, surname = ?, address = ?, card_data_id = ? WHERE id = " + client.getId(), client.getName(), client.getSurname(), client.getAddress(), client.getCardDataId());
    }

    @Override
    public void deleteClientById(Integer id) {
        jdbcTemplate.update("UPDATE clientorder SET client_id = null WHERE client_id = "+id.toString());
        jdbcTemplate.update("DELETE FROM client_and_courier WHERE client_id = "+ id);
        Integer card_id = jdbcTemplate.queryForObject("SELECT card_data_id FROM client WHERE id = " + id.toString(), Integer.class);
        if (card_id != null){
            jdbcTemplate.update("DELETE FROM card_data WHERE id = "+ card_id);
        }
        jdbcTemplate.update("DELETE FROM client WHERE id = " + id);
    }

    @Override
    public void deleteAllClients() {
        jdbcTemplate.update("UPDATE clientorder SET client_id = null");
        jdbcTemplate.update("DELETE FROM client_and_courier");
        jdbcTemplate.update("TRUNCATE TABLE client");
        jdbcTemplate.update("TRUNCATE TABLE card_data");
    }

    @Override
    public List<Client> orderBy(String field, String asc) {
        return jdbcTemplate.query("SELECT id, name, surname, address FROM client ORDER BY " + field + ' ' + asc, new BeanPropertyRowMapper<>(Client.class));
    }

    @Override
    public void createCard(CardData cardData, Integer id) {
        Integer card_id = jdbcTemplate.queryForObject("SELECT card_data_id FROM client WHERE id = " + id.toString(), Integer.class);
        if (card_id != null){
            card_id = jdbcTemplate.queryForObject("SELECT card_data_id FROM client WHERE id = " + id.toString(), Integer.class);
            jdbcTemplate.update("UPDATE client SET card_data_id = null WHERE id = " + id);
            jdbcTemplate.update("DELETE FROM card_data WHERE id = " + card_id.toString());
        }
        jdbcTemplate.update("INSERT INTO card_data(card_number, card_date) VALUES (?, ?)", cardData.getCardNumber(), cardData.getCardDate());
        card_id = jdbcTemplate.queryForObject("SELECT id FROM card_data WHERE card_number = '" + cardData.getCardNumber() + "' AND card_date = '" + cardData.getCardDate() + "'", Integer.class);
        jdbcTemplate.update("UPDATE client SET card_data_id = " + card_id + " WHERE id = " + id);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = jdbcTemplate.queryForObject("SELECT username, password, roleUser FROM user WHERE username = '" + username + "'", new BeanPropertyRowMapper<>(User.class));
        return user;
    }
//    @Override
//    public void updateCard(CardData cardData) {
//        jdbcTemplate.update("UPDATE card_data SET card_number = ?, card_date = ? WHERE id = " + cardData.getId());
//    }
}

