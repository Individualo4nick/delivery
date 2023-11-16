package com.example.delivery.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {
    private int id;
    private String name;
    private String surname;
    private String address;
    private TypeSale typeSale;
    private Integer typeSaleId;
    private int orderCount;
    private CardData cardData;
    private Integer cardDataId;
    private List<Courier> couriers;
    private List<Support> support;
}
