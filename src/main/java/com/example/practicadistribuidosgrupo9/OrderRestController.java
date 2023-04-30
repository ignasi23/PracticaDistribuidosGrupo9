package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/V2/orders")
    public ResponseEntity<String> createOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @RequestBody PurchaseOrder purchaseOrder) {
        User currentUser = userService.getUserByEmail(userEmail);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        PurchaseOrder createdPurchaseOrder = orderService.createOrder(currentUser, purchaseOrder);
        if (createdPurchaseOrder != null) {
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create order", HttpStatus.BAD_REQUEST);
    }
}


