package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    private int quantity;

    public CartItem() {
    }

    public CartItem(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    // Constructor, getters, and setters go here
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    public Product getProduct() {
        return product;
    }


    public Long getId() {
        return id;
    }

//    public void setOrder(Order order) {
//        this.order = order;
//    }
}