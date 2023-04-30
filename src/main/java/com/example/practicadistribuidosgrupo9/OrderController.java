package com.example.practicadistribuidosgrupo9;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    public static final String ORDER_ID = "orderID";
    public static List<OrderReport> orderReports = OrderReportStorage.orderReports;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<PurchaseOrder> createOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        User currentUser = userService.getUserByEmail(user);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Product> products = currentUser.getCartProducts();
        PurchaseOrder purchaseOrder = new PurchaseOrder(o.get(ORDER_ID).asText(), o.get("cardNumber").asText(), o.get("cardHolder").asText(), o.get("expiryDate").asText(), o.get("securityCode").asText(), products);
        purchaseOrder.setUser(currentUser);
        orderService.saveOrder(purchaseOrder);
        return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
    }

    @PostMapping("/reportOrder")
    public ResponseEntity<String> reportOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        User currentUser = userService.getUserByEmail(user);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean success = orderService.reportOrder(user, o.get(ORDER_ID).asText(), o.get("reportMsg").asText());
        if (success) {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
