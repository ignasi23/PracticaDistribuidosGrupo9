package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
                            @RequestParam("product-image") String image,
                            @RequestParam("submit") String submit) {
        if (submit.equals("addtocard")) {
            carritoService.agregarAlCarrito(title, new BigDecimal(price), image, quantity);
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/{index}")
    @ResponseBody
    public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index) {
        carritoService.eliminarDelCarrito(index);
        return ResponseEntity.noContent().build();
    }
}



