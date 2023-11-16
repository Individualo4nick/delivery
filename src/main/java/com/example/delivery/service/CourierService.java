package com.example.delivery.service;

import com.example.delivery.entity.Courier;
import com.example.delivery.entity.Transport;
import com.example.delivery.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {
    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }
    public List<Courier> getAllCourier(){
        return courierRepository.getAllCourier();
    }
    public Courier getCourierById(int id){
        return courierRepository.getCourierById(id);
    }
    public void createCourier(Courier courier){
        courierRepository.createCourier(courier);
    }
    public void updateCourier(Courier courier){
        courierRepository.updateCourier(courier);
    }
    public void deleteCourierById(Integer id){
        courierRepository.deleteCourierById(id);
    }
    public void deleteAllCourier(){
        courierRepository.deleteAllCourier();
    }
    public List<Courier> orderBy(String field, String asc){
        return courierRepository.orderBy(field, asc);
    }
    public void createTransport(Transport transport){
        courierRepository.createTransport(transport);
    }
    public void deleteTransport(Integer id){
        courierRepository.deleteTransport(id);
    }
    public int getCountCourier(){
        return courierRepository.getCountCourier();
    }
}
