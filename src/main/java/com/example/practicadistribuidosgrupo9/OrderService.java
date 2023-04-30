package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    private List<OrderReport> orderReports = new ArrayList<>();

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public PurchaseOrder createOrder(User user, PurchaseOrder purchaseOrder) {
        purchaseOrder.setUser(user);
        return orderRepository.save(purchaseOrder);
    }

    public boolean reportOrder(String userEmail, String orderId, String reportMsg) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            PurchaseOrder purchaseOrder = orderRepository.findByIdAndUser(Long.parseLong(orderId), user).orElse(null);
            if (purchaseOrder != null) {
                purchaseOrder.setReported(true);
                orderReports.add(new OrderReport(user.getUserName(), orderId, reportMsg));
                orderRepository.save(purchaseOrder);
                return true;
            }
        }
        return false;
    }

    public PurchaseOrder getOrder(String userEmail, String orderId) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            return orderRepository.findByIdAndUser(Long.parseLong(orderId), user).orElse(null);
        }
        return null;
    }

    public boolean deleteOrder(String userEmail, String orderId) {
        User user = userService.getUserByEmail(userEmail);
        if (user != null) {
            PurchaseOrder purchaseOrder = orderRepository.findByIdAndUser(Long.parseLong(orderId), user).orElse(null);
            if (purchaseOrder != null) {
                orderRepository.delete(purchaseOrder);
                return true;
            }
        }
        return false;
    }

    public PurchaseOrder saveOrder(PurchaseOrder purchaseOrder) {
        return orderRepository.save(purchaseOrder);
    }

    public void addOrderReport(OrderReport orderReport) {
        orderReports.add(orderReport);
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }
}
