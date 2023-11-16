package com.example.delivery.controller;

import com.example.delivery.config.SecurityConfig;
import com.example.delivery.entity.CardData;
import com.example.delivery.entity.Client;
import com.example.delivery.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public String getAllClient(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "all_client";
    }
    @GetMapping("/{id}")
    public String getClientById(@PathVariable int id, Model model){
        model.addAttribute("oneClient", clientService.getClientById(id));
        return "client";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @GetMapping("/orderBy")
    public String getClientWithOrder(@RequestParam String field, @RequestParam String asc, Model model){
        model.addAttribute("clients", clientService.orderBy(field, asc));
        return "all_client";
    }
    @GetMapping("/create")
    public String getCreateClient(){
        return "create_client";
    }
    @PostMapping("/create")
    public String createClient(Client client){
        clientService.createClient(client);
        return "redirect:/client";
    }
    @GetMapping("/update/{id}")
    public String getUpdateClient(@PathVariable Integer id, Model model){
        Client client = clientService.getClientById(id);
        client.setId(id);
        model.addAttribute("oneClient", client);
        return "update_client";
    }
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Integer id, Client client){
        client.setId(id);
        clientService.updateClient(client);
        return "redirect:/client/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Integer id){
        clientService.deleteClientById(id);
        return "redirect:/client";
    }
    @PostMapping("/deleteAll")
    public String deleteAllClient() {
        clientService.deleteAllClients();
        return "redirect:/client";
    }
    @PostMapping("/create/card/{id}")
    public String createCard(@PathVariable Integer id, CardData cardData){
        clientService.createCard(cardData, id);
        return "redirect:/client/" + id;
    }
//    @PostMapping("/update/card")
//    @ResponseBody
//    public String updateCard(CardData cardData){
//        clientService.updateCard(cardData);
//        return "successfully";
//    }
}
