package com.example.delivery.service;

import com.example.delivery.entity.Support;
import com.example.delivery.repository.SupportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportService {
    private final SupportRepository supportRepository;

    public SupportService(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }
    public List<Support> getAllSupports(){
        return supportRepository.getAllSupport();
    }
    public Support getSupportById(int id){
        return supportRepository.getSupportById(id);
    }
    public void createSupport(Support support){
        supportRepository.createSupport(support);
    }
    public void updateSupport(Support support){
        supportRepository.updateSupport(support);
    }
    public void deleteSupportById(Integer id){
        supportRepository.deleteSupportById(id);
    }
    public void deleteAllSupports(){
        supportRepository.deleteAllSupport();
    }
    public List<Support> orderBy(String field, String asc){
        return supportRepository.orderBy(field, asc);
    }
}
