package com.example.practicadistribuidosgrupo9;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    List<CartItem> findByUserOrderByProductPriceAsc(User user);
    void deleteById(Long id);
}
