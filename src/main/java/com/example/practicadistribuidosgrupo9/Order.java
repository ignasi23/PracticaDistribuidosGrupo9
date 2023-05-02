package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderID;
    private BigDecimal total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Product> products;

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String securityCode;
    private boolean reported = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String orderID, String cardNumber, String cardHolder, String expiryDate, String securityCode, User user, List<Product> products) {
        this.orderID = orderID;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.products = new ArrayList<>();
        for(Product p : products){
            p.setOrder(this);
            this.products.add(p);
        }
        this.total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.user = user;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderID='" + orderID + '\'' +
                ", total=" + total +
                ", products=" + products +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", reported=" + reported +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderID() {
        return orderID;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public void setReported(boolean b){
        this.reported = b;
    }
    public boolean getReported(){
        return this.reported;
    }
}