package com.example.delivery.service;

import com.example.delivery.entity.ClientOrder;
import com.example.delivery.entity.Product;
import com.example.delivery.repository.ClientOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrderService {
    private final ClientOrderRepository clientOrderRepository;

    public ClientOrderService(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }
    public List<ClientOrder> getAllOrder(){
        return clientOrderRepository.getAllOrder();
    }
    public ClientOrder getOrderById(int id){
        return clientOrderRepository.getOrderById(id);
    }
    public void createOrder(ClientOrder clientOrder){
        clientOrderRepository.createOrder(clientOrder);
    }
    public void updateOrder(ClientOrder clientOrder){
        clientOrderRepository.updateOrder(clientOrder);
    }
    public void deleteOrderById(Integer id){
        clientOrderRepository.deleteOrderById(id);
    }
    public void deleteAllOrder(){
        clientOrderRepository.deleteAllOrder();
    }
    public List<ClientOrder> orderBy(String field, String asc){
        return clientOrderRepository.orderBy(field, asc);
    }
    public double getSumProducts(String ids){
        return clientOrderRepository.sumProduct(ids);
    }
    public List<Product> getAllProduct(){
        return clientOrderRepository.getAllProduct();
    }
    public void createProduct(Product product){
        clientOrderRepository.createProduct(product);
    }
}
