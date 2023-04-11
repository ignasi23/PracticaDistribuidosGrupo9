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
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String cart (Model model) {
        model.addAttribute("productos", cartService.getProductsInCart());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam("product-title") String title,
                            @RequestParam("product-price") String price,
                            @RequestParam("product-quanity") int quantity,
                            @RequestParam("product-image") String image,
                            @RequestParam("submit") String submit,
                            @CookieValue(name = "user", defaultValue = "") String user) {
        User currentUser = userService.getUserByEmail(user);
        if (currentUser == null) {
            return "redirect:/login?error=true";
        }
        if (submit.equals("addtocard")) {
            cartService.addAlCart(title, new BigDecimal(price), image, quantity);
            currentUser.addAlCart(title, new BigDecimal(price), image, quantity);
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/{index}")
    @ResponseBody
    public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
        User currentUser = userService.getUserByEmail(user);
        cartService.deleteFromCart(index);
        currentUser.deleteFromCart(index);
        return ResponseEntity.noContent().build();
    }
}



