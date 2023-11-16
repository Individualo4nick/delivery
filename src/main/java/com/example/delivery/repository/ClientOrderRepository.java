package com.example.delivery.repository;

import com.example.delivery.entity.Client;
import com.example.delivery.entity.ClientOrder;
import com.example.delivery.entity.Product;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ClientOrderRepository {
    List<ClientOrder> getAllOrder();
    ClientOrder getOrderById(Integer id);
    void createOrder(ClientOrder clientOrder);
    void updateOrder(ClientOrder clientOrder);
    void deleteOrderById(Integer id);
    void deleteAllOrder();
    List<ClientOrder> orderBy(String field, String asc);
    double sumProduct(String ids);
    List<Product> getAllProduct();
    void createProduct(Product product);
}
