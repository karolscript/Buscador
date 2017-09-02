package com.example.karol.buscador;

/**
 * Created by karol on 1/9/2017.
 */

public class Producto {

    public int icon;
    public String title;
    public double precio;
    public String imgUrl;

    public Producto(){
        super();
    }

    public Producto(int icon, String title){
        super();
        this.icon=icon;
        this.title=title;
    }

    public Producto (String imgUrl, String nombre, double  precio){
        this.imgUrl = imgUrl;
        this.title = nombre;
        this.precio = precio;
    }
}
