package com.example.delivery.repository;

import com.example.delivery.entity.Support;

import java.util.List;

public interface SupportRepository {
    List<Support> getAllSupport();
    Support getSupportById(Integer id);
    void createSupport(Support support);
    void updateSupport(Support support);
    void deleteSupportById(Integer id);
    void deleteAllSupport();
    List<Support> orderBy(String field, String asc);
}
