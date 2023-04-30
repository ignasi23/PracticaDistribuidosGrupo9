package com.example.practicadistribuidosgrupo9;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Id;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String password;

    private String userName;
    private Boolean adminRole;

    public User() {
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PurchaseOrder> purchaseOrders;

    @ManyToOne(cascade = CascadeType.ALL)
    private CartService cart;

    public User(String firstName, String lastName, String password, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userName = userName;
        adminRole = false;
        cart = new CartService();
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

    public List<Product> getCartProducts(){
        return cart.getProductsInCart();
    }

    public void deleteFromCart(int index) {
        cart.deleteFromCart(index);
    }

    public void addToCart(String title, BigDecimal price, String image, int quantity) {
        cart.addToCart(title, price, image, quantity);
    }

    public List<PurchaseOrder> getOrders() {
        return purchaseOrders;
    }
}
