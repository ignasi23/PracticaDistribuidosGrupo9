package com.example.practicadistribuidosgrupo9;
//ñ
import java.math.BigDecimal;

public class Product {
    private String title;
    private BigDecimal price;
    private String image;

    public Product(String title, BigDecimal price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
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
}


