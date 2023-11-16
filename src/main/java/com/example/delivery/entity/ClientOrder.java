package com.example.delivery.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ClientOrder {
    private Integer id;
    private String title;
    private String description;
    private double total;
    private String stage;
    private Courier courier;
    private Integer courier_id;
    private Client client;
    private Integer client_id;
    private List<Product> products;
    private List<Integer> products_id;
}
