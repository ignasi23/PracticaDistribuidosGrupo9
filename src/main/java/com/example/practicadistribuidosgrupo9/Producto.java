package com.example.practicadistribuidosgrupo9;

import java.math.BigDecimal;

public class Producto {
    private String titulo;
    private BigDecimal precio;

    public Producto(String titulo, BigDecimal precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
}
