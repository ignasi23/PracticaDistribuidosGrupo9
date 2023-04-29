package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private List<Product> cart = new ArrayList<>();

    public CartService() {// this is for create new carts
    }

    public void addAlCart(String title, BigDecimal price, String image, int quantity) {
        for (int i = 0; i < quantity; i++) {
            cart.add(new Product(title, price, image));
        }
    }

    public List<Product> getProductsInCart() {
        return cart;
    }

    public BigDecimal getTotal() {
        return cart.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deleteFromCart(int index) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
    }

    public void deleteTodoCart() {
        cart.clear();
    }
}

