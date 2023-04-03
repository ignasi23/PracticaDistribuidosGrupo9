package com.example.practicadistribuidosgrupo9;

class Products {
    private String name;
    private String description;
    private int price;
    private String image;
    public Products(String name,String description, int price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    // method to get the price with 2 decimals and €
    /*public int getPrice() {
        return  this.price.toFixed(2) + "€";
    }/*

    // method to get the html representation to the product
    // hay que hacer algo para que funcione el html
    /*public String getHTML() {
        String c = '\n <div class="producto">\n';
        c= c+ '<img src="${this.imagen}" alt="${this.nombre}">\n ';
        c+= '<h3>${this.nombre}</h3>\n        <p>${this.descripcion}</p>\n        <p class="precio">${this.getPrecioFormateado()}</p>\n      </div>\n    ';
        return c
    ;
    }*/
}
//"<div class=products> <img src=" + {this.image}+
//            " alt="+{this.name}+"> <h3>" + {this.name}+"</h3> <p>" +{this.description} + "</p> <p class=precio>" +
//            this.getPrice()  + "</p>\n      </div> "