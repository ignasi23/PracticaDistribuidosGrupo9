//package com.example.practicadistribuidosgrupo9;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class CartRestController {
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserService userService;
//
//    @ResponseStatus (HttpStatus.OK)
//    @GetMapping("/cart")
//    public ResponseEntity<Map<String, Object>> getCart(@CookieValue(name = "user", defaultValue = "") String user) {
//        User currentUser = userService.getUserByEmail(user);
//        if (currentUser == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        Map<String, Object> cart = new HashMap<>();
//        cart.put("productos", cartService.getProductsInCart(user));
//        cart.put("total", cartService.getTotal());
//        cart.put("message", "Cart retrieved successfully.");
//        return ResponseEntity.ok(cart);
//    }
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/cart")
//    public ResponseEntity<Map<String, String>> addToCart(@RequestParam("product-title") String title,
//                                                         @RequestParam("product-price") String price,
//                                                         @RequestParam("product-quantity") int quantity,
//                                                         @RequestParam("product-image") String image,
//                                                         @RequestParam("submit") String submit,
//                                                         @CookieValue(name = "user", defaultValue = "") String user) {
//        User currentUser = userService.getUserByEmail(user);
//        if (currentUser == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        if (submit.equals("addtocard")) {
//            cartService.addAlCart(title, new BigDecimal(price), image, quantity);
//            currentUser.addAlCart(title, new BigDecimal(price), image, quantity);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "Product added to cart successfully.");
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//    //We did not put the method PUT because our car can not do it.
//    @ResponseStatus (HttpStatus.OK)
//    @DeleteMapping("/cart/{index}")
//    public ResponseEntity<Map<String, String>> removeFromCart(@PathVariable("index") int index, @CookieValue(name = "user", defaultValue = "") String user) {
//        User currentUser = userService.getUserByEmail(user);
//        if (currentUser == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        cartService.deleteFromCart(index);
//        currentUser.deleteFromCart(index);
//        Map<String, String> response = new HashMap<>();
//        response.put("message", "Product removed from cart successfully.");
//        return ResponseEntity.ok(response);
//    }
//}
