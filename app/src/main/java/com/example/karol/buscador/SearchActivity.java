package com.example.karol.buscador;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.karol.buscador.Servicio.LeerJson;
import com.example.karol.buscador.Servicio.VolleyService;
import com.example.karol.buscador.Servicio.VolleySingleton;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        VolleyService prueba = new VolleyService();
        prueba.traerJson(VolleySingleton.BASE_URL+"xbox"+VolleySingleton.END_URL,getBaseContext());

        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String mResponse = m.getString("Response", "");
        System.out.println("AFUERA " + mResponse);
        LeerJson lj = new LeerJson();
        lj.setJsonResponse(mResponse);
        lj.leyendo();
        ArrayList<Product> lista = lj.getListaProducts();

        ProductsListAdapter adapter = new ProductsListAdapter(this,R.layout.list_view_item,lista);
        ListView productsListView = (ListView)findViewById(R.id.products_list_view);
        View header = (View) getLayoutInflater().inflate(R.layout.products_list_view_header,null);
        productsListView.addHeaderView(header);
        productsListView.setAdapter(adapter);

    }



}
