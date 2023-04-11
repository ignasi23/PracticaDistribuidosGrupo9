package com.example.practicadistribuidosgrupo9;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    public static final String ORDID = "orderID";

    public static List<OrderReport> orderReports;
    static {orderReports = new ArrayList<>();}
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@CookieValue(name = "user", defaultValue = "") String user , @RequestBody JsonNode o) {
        // Store the order on the map
        User currentUser = UserController.users.get(user.toUpperCase());
        List<Product> products = currentUser.getCartProducts();
        Order order = new Order(o.get(ORDID).asText(), o.get("cardNumber").asText(), o.get("cardHolder").asText(), o.get("expiryDate").asText(), o.get("securityCode").asText(), products);
        if(currentUser != null){
            currentUser.addOrder(order);
            currentUser.deleteTodoCart();
        }
        // Return a response to the client
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/reportOrder")
    public String reportOrder(@CookieValue(name = "user", defaultValue = "") String user , @RequestBody JsonNode o) {
        // Store the order on the map
        User currentUser = UserController.users.get(user.toUpperCase());
        if(currentUser != null){
            currentUser.getOrders().get(o.get(ORDID).asText()).setReported(true);
            OrderReport orderReport = new OrderReport(user, o.get(ORDID).asText(), o.get("reportMsg").asText());
            orderReports.add(orderReport);
        }
        // Return a response to the client
        return "Ok";    // Neman wtf
    }

}
