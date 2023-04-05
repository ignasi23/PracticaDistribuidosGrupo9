package com.example.practicadistribuidosgrupo9;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Producto> productos;
    private String orderID;
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String securityCode;
    private boolean reported = false;


    public Order(String orderID, String cardNumber, String cardHolder, String expiryDate, String securityCode, List<Producto> productos) {
        this.orderID = orderID;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.productos = new ArrayList<>();
        for(Producto p : productos){
            this.productos.add(p);
        }

    }

    public String getOrderID() {
        return orderID;
    }

    public List<Producto> getProductos(){
        return productos;
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