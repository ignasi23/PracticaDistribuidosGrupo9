package com.example.practicadistribuidosgrupo9;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private Boolean adminRole;
    private Map<String, Order> ordersMap = new ConcurrentHashMap<>();
    private CartService cart = new CartService();


    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        adminRole = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdminRole() {
        return adminRole;
    }
    public void setAdminRole() {
        adminRole = true;
    }
    public void addOrder(Order order) {
        this.ordersMap.put(order.getOrderID(), order);
    }
    public Map<String, Order> getOrders() {
        return ordersMap;
    }

    public List<Product> getCartProducts(){
        return cart.getProductsInCart();
    }

    public void deleteTodoCart(){
        cart.deleteTodoCart();
    }

    public void deleteFromCart(int index) {
        cart.deleteFromCart(index);
    }

    public void addAlCart(String title, BigDecimal price, String image, int quantity) {
        cart.addAlCart(title, price, image, quantity);
    }
}

