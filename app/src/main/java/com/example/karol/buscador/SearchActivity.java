package com.example.karol.buscador;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.karol.buscador.Servicio.LeerJson;
import com.example.karol.buscador.Servicio.VolleyService;
import com.example.karol.buscador.Servicio.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Producto> lista = lj.getListaProductos();

    }



}
