package com.example.practicadistribuidosgrupo9;
import java.math.BigDecimal;

public class Producto {
    private String titulo;
    private BigDecimal precio;

    public Producto(String titulo) {
        this.titulo = titulo;
        this.precio = new BigDecimal("25.00");
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
}