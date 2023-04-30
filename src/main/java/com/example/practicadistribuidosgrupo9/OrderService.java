package com.example.practicadistribuidosgrupo9;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(String userEmail, Order order) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user != null) {
            order.setUser(user);
            user.addOrder(order);
            userRepository.save(user);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean reportOrder(String userEmail, String orderId, String reportMsg) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            Optional<Order> optionalOrder = orderRepository.findByOrderID(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                order.setReported(true);
                orderRepository.save(order);
                OrderController.orderReports.add(new OrderReport(userEmail, orderId, reportMsg));
                return true;
            }
        }
        return false;
    }

    public Order getOrder(String userEmail, String orderId) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            return orderRepository.findByOrderID(orderId).orElse(null);
        }
        return null;
    }

    public boolean deleteOrder(String userEmail, String orderId) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            Optional<Order> optionalOrder = orderRepository.findByOrderID(orderId);
            if (optionalOrder.isPresent()) {
                orderRepository.delete(optionalOrder.get());
                return true;
            }
        }
        return false;
    }
}

