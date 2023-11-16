package com.example.delivery.repository;

import com.example.delivery.entity.*;

import java.util.List;

public interface CourierRepository {
    List<Courier> getAllCourier();
    Courier getCourierById(Integer id);
    void createCourier(Courier courier);
    void updateCourier(Courier courier);
    void deleteCourierById(Integer id);
    void deleteAllCourier();
    List<Courier> orderBy(String field, String asc);
    void createTransport(Transport transport);
    void deleteTransport(Integer id);
    int getCountCourier();
}
