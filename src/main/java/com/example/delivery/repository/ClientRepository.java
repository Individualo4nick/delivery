package com.example.delivery.repository;

import com.example.delivery.entity.CardData;
import com.example.delivery.entity.Client;
import com.example.delivery.entity.Support;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ClientRepository {
    List<Client> getAllClients();
    Client getClientById(Integer id);
    void createClient(Client client);
    void updateClient(Client client);
    void deleteClientById(Integer id);
    void deleteAllClients();
    List<Client> orderBy(String field, String asc);
    void createCard(CardData cardData, Integer id);

    UserDetails loadUserByUsername(String username);
//    void updateCard(CardData cardData);
}
