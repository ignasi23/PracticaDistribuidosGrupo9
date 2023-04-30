package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Service
public class CartService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private List<Product> cart = new ArrayList<>();

    public CartService() {
    }

    public void addToCart(String title, BigDecimal price, String image, int quantity) {
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

    public void deleteAllFromCart() {
        cart.clear();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


