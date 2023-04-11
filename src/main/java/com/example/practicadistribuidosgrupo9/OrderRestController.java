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

    @PostMapping("V2/orders")
    public ResponseEntity<String> createOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(userEmail, order);
        if (createdOrder != null) {
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create order", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("V2/reportOrder")
    public ResponseEntity<String> reportOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @RequestBody OrderReport orderReport) {
        boolean success = orderService.reportOrder(userEmail, orderReport.getOrderID(), orderReport.getReportMsg());
        if (success) {
            return new ResponseEntity<>("Order reported successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to report order", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<String> getOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @PathVariable String orderId) {
        Order order = orderService.getOrder(userEmail, orderId);
        if (order != null) {
            return new ResponseEntity<>("Order found: " + order.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@CookieValue(name = "user", defaultValue = "") String userEmail, @PathVariable String orderId) {
        boolean success = orderService.deleteOrder(userEmail, orderId);
        if (success) {
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete order", HttpStatus.NOT_FOUND);
    }
}

