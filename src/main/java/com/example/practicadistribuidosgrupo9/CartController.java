package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;
    //@Autowired
    //private UserService userService;

    @GetMapping("/cart")
    public String cart (Model model, @CookieValue(name = "user", defaultValue = "") String user) {
        Optional<User> userOptional = userRepository.findByUserName(user.toUpperCase());
        model.addAttribute("productos", cartService.getProductosInCart(userOptional.get()));
        model.addAttribute("total", cartService.getTotal(userOptional.get()));
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@RequestParam("product-title") String title,
                            @RequestParam("product-price") String price,
                            @RequestParam("product-quanity") int quantity,
                            @RequestParam("product-image") String image,
                            @RequestParam("submit") String submit,
                            @CookieValue(name = "user", defaultValue = "") String user) {
        Optional<User> userOptional = userRepository.findByUserName(user.toUpperCase());
        if (userOptional.get() == null) {
            return "redirect:/login?error=true";
        }
        if (submit.equals("addtocard")) {
            //cartService.addAlCart(title, new BigDecimal(price), image, quantity);///////////////
            cartService.addAlCart(title, new BigDecimal(price), image, quantity, userOptional.get());
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/{index}")
    @ResponseBody
    public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
        Optional<User> userOptional = userRepository.findByUserName(user.toUpperCase());
        cartService.deleteFromCart(index, userOptional.get());
        return ResponseEntity.noContent().build();
    }
}



