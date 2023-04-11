package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(userEmail, order);
        if (createdOrder != null) {
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reportOrder")
    public ResponseEntity<String> reportOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @RequestBody OrderReport orderReport) {
        boolean success = orderService.reportOrder(userEmail, orderReport.getOrderID(), orderReport.getReportMsg());
        if (success) {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @PathVariable String orderId) {
        Order order = orderService.getOrder(userEmail, orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @PathVariable String orderId) {
        boolean success = orderService.deleteOrder(userEmail, orderId);
        if (success) {
            return new ResponseEntity<>("Order deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
