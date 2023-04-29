package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    private String orderID;
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String securityCode;
    private boolean reported = false;


    public Order(String orderID, String cardNumber, String cardHolder, String expiryDate, String securityCode, List<Product> products) {
        this.orderID = orderID;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.products = new ArrayList<>();
        for(Product p : products){
            this.products.add(p);
        }

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
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}