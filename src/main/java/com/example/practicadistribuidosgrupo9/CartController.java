package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CartController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/cart")
    public String carrito(Model model) {
        model.addAttribute("productos", carritoService.getProductosEnCarrito());
        model.addAttribute("total", carritoService.getTotal());
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam("product-title") String title,
                            @RequestParam("product-price") String price,
                            @RequestParam("product-quanity") int quantity,
                            @RequestParam("submit") String submit) {
        if (submit.equals("addtocard")) {
            carritoService.agregarAlCarrito(title, new BigDecimal(price), quantity);
        }
        return "redirect:/cart";
    }
}



