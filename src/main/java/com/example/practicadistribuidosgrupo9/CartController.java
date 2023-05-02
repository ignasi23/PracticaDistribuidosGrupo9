    package com.example.practicadistribuidosgrupo9;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import java.math.BigDecimal;
    import java.util.List;

    @Controller
    public class CartController {

        @Autowired
        private CartService cartService;

        @Autowired
        private UserRepository userRepository;

        @GetMapping("/cart")
        public String cart (Model model, @CookieValue(name = "user", defaultValue = "") String user) {
            List<User> users = userRepository.findByUserName(user.toUpperCase());
            if (users.isEmpty()) {
                return "redirect:/login?error=true";
            }
            model.addAttribute("productos", cartService.getProductosInCartOrderByPriceAsc(users.get(0)));
            model.addAttribute("total", cartService.getTotal(users.get(0)));
            return "cart";
        }

        @PostMapping("/cart")
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

        @DeleteMapping("/cart/{index}")
        @ResponseBody
        public ResponseEntity<Void> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
            List<User> users = userRepository.findByUserName(user.toUpperCase());
            if (!users.isEmpty()) {
                cartService.deleteFromCart(index, users.get(0));
            }
            return ResponseEntity.noContent().build();
        }
    }


