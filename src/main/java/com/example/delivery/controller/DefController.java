package com.example.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefController {
    @GetMapping("/")
    public String getDefault(){
        return "redirect:/order/product";
    }
}
