package com.example.karol.buscador;

/**
 * Created by karol on 1/9/2017.
 */

public class Product {

    public int icon;
    public String title;
    public double precio;
    public String imgUrl;

    public Product(){
        super();
    }

    public Product(int icon, String title){
        super();
        this.icon=icon;
        this.title=title;
    }

    public Product(String imgUrl, String nombre, double  precio){
        this.imgUrl = imgUrl;
        this.title = nombre;
        this.precio = precio;
    }
}
