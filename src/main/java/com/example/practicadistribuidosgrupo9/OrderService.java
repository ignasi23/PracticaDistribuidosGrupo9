package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public Order createOrder(User user, Order order) {
        order.setUser(user);
        return orderRepository.save(order);
    }

    public boolean reportOrder(User user, String orderId, String reportMsg) {
        Order order = orderRepository.findById(Long.parseLong(orderId)).orElse(null);
        if (order != null && order.getUser().equals(user)) {
            order.setReported(true);
            OrderController.orderReports.add(new OrderReport(user.getEmail(), orderId, reportMsg));
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public Order getOrder(String userEmail, String orderId) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            return user.getOrders().get(orderId);
        }
        return null;
    }

    public boolean deleteOrder(String userEmail, String orderId) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            return user.getOrders().remove(orderId) != null;
        }
        return false;
    }
}