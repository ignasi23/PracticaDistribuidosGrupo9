package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartService() {// this is for create new carts
    }

    public void addAlCart(String title, BigDecimal price, String image, int quantity, User user) {
        for (int i = 0; i < quantity; i++) {
            Product product = new Product(title, price, image);
            productRepository.save(product); // Save the product before saving the cart item
            CartItem cartItem = new CartItem(user, product);
            //user.getCart().add(cartItem);
            cartRepository.save(cartItem);
        }
    }

    public List<CartItem> getCartItemsInCart(User user) {
        return cartRepository.findByUser(user);
    }

    public void deleteTodoCart(User user) {
        for(CartItem item : cartRepository.findByUser(user)){
            Long id = item.getId();
            cartRepository.deleteById(id);
        }
    }

    public void deleteFromCart(int index, User user) {
        if (index >= 0 && index < cartRepository.findByUser(user).size()) {
            Long id = cartRepository.findByUser(user).get(index).getId();
            cartRepository.deleteById(id);
        }
    }

    public List<Product> getProductosInCart(User user) {
        List<Product> productsList = new ArrayList<>();
        for(CartItem item : cartRepository.findByUser(user)){
            productsList.add(item.getProduct());
        }
        return productsList;
    }

    public BigDecimal getTotal(User user) {
        return cartRepository.findByUser(user).stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

