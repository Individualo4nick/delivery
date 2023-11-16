package com.example.delivery.service;

import com.example.delivery.entity.CardData;
import com.example.delivery.entity.Client;
import com.example.delivery.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getAllClients(){
        return clientRepository.getAllClients();
    }
    public Client getClientById(int id){
        return clientRepository.getClientById(id);
    }
    public void createClient(Client client){
        clientRepository.createClient(client);
    }
    public void updateClient(Client client){
        clientRepository.updateClient(client);
    }
    public void deleteClientById(Integer id){
        clientRepository.deleteClientById(id);
    }
    public void deleteAllClients(){
        clientRepository.deleteAllClients();
    }
    public List<Client> orderBy(String field, String asc){
        return clientRepository.orderBy(field, asc);
    }
    public void createCard(CardData cardData, Integer id){
        clientRepository.createCard(cardData, id);
    }
//    public void updateCard(CardData cardData){
//        clientRepository.updateCard(cardData);
//    }
}
