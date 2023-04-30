package com.example.practicadistribuidosgrupo9;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {

    @ManyToOne(optional = true)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne(optional = true)
    @JoinColumn(name="purchase_order_id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(precision=19, scale=2)
    private BigDecimal price;

    private String image;


    public Product() {
    }

    public Product(String title, BigDecimal price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}

