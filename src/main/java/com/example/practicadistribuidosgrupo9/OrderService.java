package com.example.practicadistribuidosgrupo9;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public Order createOrder(String userEmail, Order order) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            user.addOrder(order);
            user.deleteTodoCart();
            return order;
        }
        return null;
    }

    public boolean reportOrder(String userEmail, String orderId, String reportMsg) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            Order order = user.getOrders().get(orderId);
            if (order != null) {
                order.setReported(true);
                OrderController.orderReports.add(new OrderReport(userEmail, orderId, reportMsg));
                return true;
            }
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
