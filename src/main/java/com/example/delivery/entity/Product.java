package com.example.delivery.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
}
