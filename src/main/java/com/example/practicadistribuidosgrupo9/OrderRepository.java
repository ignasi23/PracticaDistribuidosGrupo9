package com.example.practicadistribuidosgrupo9;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderID(String orderID);
    List<Order> findByUser(User user);
}
