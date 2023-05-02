package com.example.practicadistribuidosgrupo9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Product> getCart(@CookieValue(name = "user", defaultValue = "") String user) {
        List<User> users = userRepository.findByUserName(user.toUpperCase());
        if (users.isEmpty()) {
            return null;
        }
        return cartService.getProductosInCartOrderByPriceAsc(users.get(0));
    }

    @PostMapping
    public String addToCart(@RequestParam("product-title") String title,
                            @RequestParam("product-price") String price,
                            @RequestParam("product-quanity") int quantity,
                            @RequestParam("product-image") String image,
                            @RequestParam("submit") String submit,
                            @CookieValue(name = "user", defaultValue = "") String user) {
        List<User> users = userRepository.findByUserName(user.toUpperCase());
        if (users.isEmpty()) {
            return "redirect:/login?error=true";
        }
        if (submit.equals("addtocard")) {
            cartService.addAlCart(title, new BigDecimal(price), image, quantity, users.get(0));
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
        List<User> users = userRepository.findByUserName(user.toUpperCase());
        if (!users.isEmpty()) {
            cartService.deleteFromCart(index, users.get(0));
        }
        return ResponseEntity.noContent().build();
    }
}
