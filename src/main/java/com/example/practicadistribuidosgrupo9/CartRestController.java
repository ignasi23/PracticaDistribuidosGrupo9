package com.example.practicadistribuidosgrupo9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

@RequestMapping("/api/")
@RestController
public class CartRestController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart() {
        Map<String, Object> cart = new HashMap<>();
        cart.put("productos", cartService.getProductsInCart());
        cart.put("total", cartService.getTotal());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/cart")
    public ResponseEntity<Map<String, String>> addToCart(@RequestParam("product-title") String title,
                                                         @RequestParam("product-price") String price,
                                                         @RequestParam("product-quantity") int quantity,
                                                         @RequestParam("product-image") String image,
                                                         @RequestParam("submit") String submit,
                                                         @CookieValue(name = "user", defaultValue = "") String user) {
        User currentUser = UserController.users.get(user.toUpperCase());
        if (submit.equals("addtocard")) {
            cartService.addAlCart(title, new BigDecimal(price), image, quantity);
            currentUser.addAlCart(title, new BigDecimal(price), image, quantity);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product added to cart successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/cart/{index}")
    public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
        User currentUser = UserController.users.get(user.toUpperCase());
        cartService.deleteFromCart(index);
        currentUser.deleteFromCart(index);
        return ResponseEntity.noContent().build();
    }


}


