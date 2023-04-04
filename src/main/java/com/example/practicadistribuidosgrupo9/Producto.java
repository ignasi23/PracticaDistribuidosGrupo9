package com.example.practicadistribuidosgrupo9;

import java.math.BigDecimal;

public class Producto {
    private String titulo;
    private BigDecimal precio;
    private String imagen;

    public Producto(String titulo, BigDecimal precio, String imagen) {
        this.titulo = titulo;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    // Agrega un setter para la imagen si lo necesitas
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}


