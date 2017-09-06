package com.example.karol.buscador.Model;

/**
 * Created by karol on 1/9/2017.
 */

public class Product {

    public String title;
    public double price;
    public String imgUrl;

    public Product(){
        super();
    }

    public Product(Double price, String title, String imgUrl){
        super();
        this.title=title;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Product(String imgUrl, String nombre, double  price){
        this.imgUrl = imgUrl;
        this.title = nombre;
        this.price = price;
    }
}
