package com.example.delivery.controller;

import com.example.delivery.entity.Client;
import com.example.delivery.entity.Support;
import com.example.delivery.service.SupportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/support")
public class SupportController {
    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }
    @GetMapping
    public String getAllSupport(Model model){
        model.addAttribute("support", supportService.getAllSupports());
        return "all_support";
    }
    @GetMapping("/{id}")
    public String getSupportById(@PathVariable int id, Model model){
        model.addAttribute("oneSupport", supportService.getSupportById(id));
        return "support";
    }
    @GetMapping("/orderBy")
    public String getSupportWithOrder(@RequestParam String field, @RequestParam String asc, Model model){
        model.addAttribute("support", supportService.orderBy(field, asc));
        return "all_support";
    }
    @GetMapping("/create")
    public String getCreateSupport(){
        return "create_support";
    }
    @PostMapping("/create")
    public String createSupport(Support support){
        supportService.createSupport(support);
        return "redirect:/support";
    }
    @GetMapping("/update/{id}")
    public String getUpdateSupport(@PathVariable Integer id, Model model){
        Support support = supportService.getSupportById(id);
        support.setId(id);
        model.addAttribute("oneSupport", support);
        return "update_support";
    }
    @PostMapping("/update/{id}")
    public String updateSupport(@PathVariable Integer id, Support support){
        support.setId(id);
        supportService.updateSupport(support);
        return "redirect:/support/" + id;
    }
    @PostMapping("/delete/{id}")
    public String deleteSupport(@PathVariable Integer id){
        supportService.deleteSupportById(id);
        return "redirect:/support";
    }
    @PostMapping("/deleteAll")
    public String deleteAllSupport(){
        supportService.deleteAllSupports();
        return "redirect:/support";
    }
}
