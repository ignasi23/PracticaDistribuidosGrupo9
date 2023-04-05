package com.example.practicadistribuidosgrupo9;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class OrderController {

    private static final Map<String, Order> ordersMap = new ConcurrentHashMap<>();
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Almacenar el pedido en el mapa
        ordersMap.put(order.getOrderID(), order);

        // Imprimir la información del pedido y el mapa actualizado
        System.out.println("Pedido recibido: " + order.getOrderID());
        System.out.println("Mapa de pedidos actualizado: " + ordersMap);

        // Devolver una respuesta al cliente
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/orders")
    public ResponseEntity<Map<String, Order>> getAllOrders() {
        return new ResponseEntity<>(ordersMap, HttpStatus.OK);
    }
}