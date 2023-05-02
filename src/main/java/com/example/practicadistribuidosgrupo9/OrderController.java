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
    private CartService cartService;
    @Autowired
    private OrderRepository orderRepository;

    public static List<OrderReport> orderReports;

    static {
        orderReports = new ArrayList<>();
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        // Store the order on the map
        User currentUser = userService.getUserByEmail(user);
        List<Product> products = cartService.getProductosInCartOrderByPriceAsc(currentUser);
        Order order = new Order(o.get(ORDID).asText(), o.get("cardNumber").asText(), o.get("cardHolder").asText(), o.get("expiryDate").asText(), o.get("securityCode").asText(), currentUser, products);
        if (currentUser != null) {
            //currentUser.addOrder(order);
            orderRepository.save(order);
            cartService.deleteTodoCart(currentUser);
        }
        // Return a response to the client
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/reportOrder")
    public String reportOrder(@CookieValue(name = "user", defaultValue = "") String user, @RequestBody JsonNode o) {
        // Store the order on the map
        User currentUser = userService.getUserByEmail(user);
        if (currentUser != null) {
            String orderId = o.get(ORDID).asText();
            for (Order order : currentUser.getOrders()) {
                if (order.getOrderID().equals(orderId)) {
                    order.setReported(true);
                    OrderReport orderReport = new OrderReport(user, orderId, o.get("reportMsg").asText());
                    orderReports.add(orderReport);
                    break;
                }
            }
        }
        // Return a response to the client
        return "Ok";
    }
}
