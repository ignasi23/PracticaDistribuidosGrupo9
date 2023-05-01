package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;

    private String userName;
    private Boolean adminRole;

    @Transient
    private CartService cart = new CartService();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public User() {
    }


    public User(String firstName, String lastName, String password, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdminRole() {
        return adminRole;
    }
    public void setAdminRole() {
        adminRole = true;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
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

