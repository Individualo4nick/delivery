package com.example.delivery.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Courier {
    private int id;
    private String name;
    private String surname;
    private int salary;
    private int orderCount;
    private List<Client> clients;
    private List<Transport> transports;
}
