package com.example.practicadistribuidosgrupo9;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private List<Producto> carrito = new ArrayList<>();

    public void agregarAlCarrito(String titulo, BigDecimal precio, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            carrito.add(new Producto(titulo, precio));
        }
    }

    public List<Producto> getProductosEnCarrito() {
        return carrito;
    }

    public BigDecimal getTotal() {return carrito.stream()
            .map(Producto::getPrecio)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

