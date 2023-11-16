package com.example.delivery.controller;

import com.example.delivery.entity.ClientOrder;
import com.example.delivery.entity.Product;
import com.example.delivery.service.ClientOrderService;
import com.example.delivery.service.CourierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Controller
@RequestMapping("/order")
public class ClientOrderController {
    private final ClientOrderService clientOrderService;
    private final CourierService courierService;

    public ClientOrderController(ClientOrderService clientOrderService, CourierService courierService) {
        this.clientOrderService = clientOrderService;
        this.courierService = courierService;
    }
    @GetMapping
    public String getAllOrder(Model model){
        model.addAttribute("orders", clientOrderService.getAllOrder());
        return "all_orders";
    }
    @GetMapping("/{id}")
    public String getOrderById(@PathVariable int id, Model model){
        model.addAttribute("oneOrder", clientOrderService.getOrderById(id));
        return "order";
    }
    @GetMapping("/orderBy")
    public String getOrderWithOrder(@RequestParam String field, @RequestParam String asc, Model model){
        model.addAttribute("orders", clientOrderService.orderBy(field, asc));
        return "all_orders";
    }
    @GetMapping("/create")
    public String getCreateOrder(){
        return "create_order";
    }
    @PostMapping("/create")
    public String createOrder(ClientOrder clientOrder, String ids){
        ArrayList<Integer> productsId = new ArrayList<>();
        Arrays.stream(ids.split(", "))
                .map(Integer::valueOf)
                .forEach(productsId::add);
        clientOrder.setProducts_id(productsId);
        clientOrder.setTotal(clientOrderService.getSumProducts(ids));
        clientOrder.setStage("new");
        Random random = new Random();
        clientOrder.setCourier_id(random.nextInt(courierService.getCountCourier()) + 1);
        clientOrderService.createOrder(clientOrder);
        return "redirect:/order";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id){
        clientOrderService.deleteOrderById(id);
        return "redirect:/order";
    }
    @PostMapping("/deleteAll")
    public String deleteAllOrder(){
        clientOrderService.deleteAllOrder();
        return "redirect:/order";
    }
    @GetMapping("/product")
    public String getAllProduct(Model model){
        model.addAttribute("products", clientOrderService.getAllProduct());
        return "all_product";
    }
    @GetMapping("/create/product")
    public String getCreateProduct(){
        return "create_product";
    }
    @PostMapping("/create/product")
    public String createProduct(Product product){
        clientOrderService.createProduct(product);
        return "redirect:/order/product";
    }
}
