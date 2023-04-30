package com.example.practicadistribuidosgrupo9;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long>, JpaSpecificationExecutor<User> {
    Optional<PurchaseOrder> findByIdAndUser(Long id, User user);
}