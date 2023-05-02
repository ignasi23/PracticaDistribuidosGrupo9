package com.example.practicadistribuidosgrupo9;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private BigDecimal price;
    private String image;

    public Product(String title, BigDecimal price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}


