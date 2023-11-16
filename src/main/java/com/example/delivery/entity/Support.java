package com.example.delivery.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Support {
    private int id;
    private String name;
    private String surname;
    private double salary;
    private String number;
    private List<Client> clients;
}
