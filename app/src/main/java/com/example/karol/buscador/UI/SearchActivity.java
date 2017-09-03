package com.example.karol.buscador.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.karol.buscador.Controller.ProductSearchController;
import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.Controller.ProductsListAdapter;
import com.example.karol.buscador.R;
import com.example.karol.buscador.Service.JsonInterpreter;
import com.example.karol.buscador.Service.DAOProduct;
import com.example.karol.buscador.Service.VolleySingleton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SearchActivity extends AppCompatActivity {
    ListView productsListView;
    SearchView productSearchView;
    SharedPreferences sharedPreferences;
    CharSequence query;
    private View header;
    private ProductsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCompnents();


        // perform set on query text listener event
        productSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            // do something on text submit
                productsListView = ProductSearchController.searchProduct(getApplicationContext(),query);
                return false;
        }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes
                return false;
            }
        });



    }

    /**
     * Method tha initializes all the attributes and views once
     * the searchActivity is created.
     */
    public void initCompnents(){
        setContentView(R.layout.activity_search);
        productsListView = (ListView)findViewById(R.id.products_list_view);
        header = getLayoutInflater().inflate(R.layout.products_list_view_header,null);
        productsListView.addHeaderView(header);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout parent = (LinearLayout)findViewById(R.id.products_list_view_header);
        View headerRoot = inflater.inflate(R.layout.products_list_view_header,parent,false);
        productSearchView = (SearchView)headerRoot.findViewById(R.id.product_search_view);
        query = productSearchView.getQuery();

    }


}
