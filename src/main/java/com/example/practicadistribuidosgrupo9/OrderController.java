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

    public static List<OrderReport> orderReports;
    static {orderReports = new ArrayList<>();}
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@CookieValue(name = "user", defaultValue = "") String user , @RequestBody JsonNode o) {
        // Almacenar el pedido en el mapa
        User currentUser = AuthController.users.get(user.toUpperCase());
        List<Producto> products = currentUser.getCarritoProducts();
        Order order = new Order(o.get("orderID").asText(), o.get("cardNumber").asText(), o.get("cardHolder").asText(), o.get("expiryDate").asText(), o.get("securityCode").asText(), products);
        if(currentUser != null){
            currentUser.addOrder(order);
            currentUser.eliminarTodoCarrito();
        }
        // Devolver una respuesta al cliente
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("/reportOrder")
    public String reportOrder(@CookieValue(name = "user", defaultValue = "") String user , @RequestBody JsonNode o) {
        // Almacenar el pedido en el mapa
        User currentUser = AuthController.users.get(user.toUpperCase());
        if(currentUser != null){
            currentUser.getOrders().get(o.get("orderID").asText()).setReported(true);
            OrderReport orderReport = new OrderReport(user, o.get("orderID").asText(), o.get("reportMsg").asText());
            orderReports.add(orderReport);
        }
        // Devolver una respuesta al cliente
        return "Ok";
    }
}
