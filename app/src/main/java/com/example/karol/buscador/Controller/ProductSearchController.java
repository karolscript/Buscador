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
    private static ListView foundProductsListView;
    private static ProductsListAdapter adapter;

    public static ListView searchProduct(Context context, String product){
        foundProductsList= ListViewController.fillProductsList(context,product);
        adapter = new ProductsListAdapter(context, R.layout.list_view_item, foundProductsList);
        foundProductsListView.setAdapter(adapter);
        return foundProductsListView;
    }
}
