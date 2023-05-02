package com.example.practicadistribuidosgrupo9;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private UserRepository userRepository;
    public CartService() {// this is for create new carts
    }

    public void addAlCart(String title, BigDecimal price, String image, int quantity, User user) {
        for (int i = 0; i < quantity; i++) {
            user.getCart().add(new Product(title, price, image));
        }
    }

    public List<Product> getProductsInCart(User user) {
        return user.getCart();
    }

    public BigDecimal getTotal(User user) {
        return user.getCart().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deleteFromCart(int index, User user) {
        if (index >= 0 && index < user.getCart().size()) {
            user.getCart().remove(index);
        }
    }

    public void deleteTodoCart(User user) {
        user.getCart().clear();
    }
}

