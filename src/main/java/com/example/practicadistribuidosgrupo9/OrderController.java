package com.example.practicadistribuidosgrupo9;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    public static final String ORDID = "orderID";

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        User currentUser = userService.getUserByEmail(user);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Product> products = currentUser.getCartProducts();
        Order order = new Order(o.get(ORDID).asText(), o.get("cardNumber").asText(), o.get("cardHolder").asText(), o.get("expiryDate").asText(), o.get("securityCode").asText(), products);
        order.setUser(currentUser);
        orderService.saveOrder(order);
        currentUser.deleteTodoCart();
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/reportOrder")
    public ResponseEntity<String> reportOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        User currentUser = userService.getUserByEmail(user);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean success = orderService.reportOrder(currentUser, o.get(ORDID).asText(), o.get("reportMsg").asText());
        if (success) {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

}
