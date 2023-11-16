package com.example.delivery.controller;

import com.example.delivery.entity.Courier;
import com.example.delivery.entity.Transport;
import com.example.delivery.service.CourierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courier")
public class CourierController {
    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }
    @GetMapping
    public String getAllCourier(Model model){
        model.addAttribute("courier", courierService.getAllCourier());
        return "all_courier";
    }
    @GetMapping("/{id}")
    public String getCourierById(@PathVariable int id, Model model){
        model.addAttribute("oneCourier", courierService.getCourierById(id));
        return "courier";
    }
    @GetMapping("/orderBy")
    public String getCourierWithOrder(@RequestParam String field, @RequestParam String asc, Model model){
        model.addAttribute("courier", courierService.orderBy(field, asc));
        return "all_courier";
    }
    @GetMapping("/create")
    public String getCreateCourier(){
        return "create_courier";
    }
    @PostMapping("/create")
    public String createCourier(Courier courier){
        courierService.createCourier(courier);
        return "redirect:/courier";
    }
    @GetMapping("/update/{id}")
    public String getUpdateCourier(@PathVariable Integer id, Model model){
        Courier courier = courierService.getCourierById(id);
        courier.setId(id);
        model.addAttribute("oneCourier", courier);
        return "update_courier";
    }
    @PostMapping("/update/{id}")
    public String updateCourier(@PathVariable Integer id, Courier courier){
        courier.setId(id);
        courierService.updateCourier(courier);
        return "redirect:/courier/" + id;
    }
    @GetMapping("/delete/{id}")
    public String deleteCourier(@PathVariable Integer id){
        courierService.deleteCourierById(id);
        return "redirect:/courier";
    }
    @PostMapping("/deleteAll")
    public String deleteAllCourier(){
        courierService.deleteAllCourier();
        return "redirect:/courier";
    }
    @PostMapping("/create/transport")
    public String createTransport(Transport transport){
        courierService.createTransport(transport);
        return "redirect:/courier/" + transport.getCourier_id();
    }
    @PostMapping("/delete/transport")
    public String updateTransport(Integer id, Integer courierId){
        courierService.deleteTransport(id);
        return "redirect:/courier/" + courierId;
    }
}
