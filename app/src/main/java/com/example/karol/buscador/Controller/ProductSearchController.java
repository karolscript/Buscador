package com.example.karol.buscador.Controller;

import android.content.Context;
import android.widget.ListView;

import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.R;

import java.util.ArrayList;

/**
 * Created by karol on 3/9/2017.
 */

public class ProductSearchController {
    private static ArrayList<Product> foundProductsList = new ArrayList<>();

    public static ArrayList<Product> searchProduct( String json){
        foundProductsList= ListViewController.fillProductsList(json);
        return foundProductsList;
    }
}
