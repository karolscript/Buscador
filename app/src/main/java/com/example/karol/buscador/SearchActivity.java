package com.example.karol.buscador;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.karol.buscador.Servicio.VolleyService;

public class SearchActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Producto datos_productos[] = new Producto[]{};
        //acá me traigo del API los productos

       /* LlenadoListaProductos ll = new LlenadoListaProductos();
        ll.llenado("Reloj");*/

        VolleyService prueba = new VolleyService();
        prueba.traerJson(ApiClient.BASE_URL+"Reloj"+ApiClient.END_URL,getBaseContext());



        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String mResponse = m.getString("Response", "");
        System.out.println("AFUERA " + mResponse);
        //LeerJson lj = new LeerJson();
       // lj.leyendo(this.getApplicationContext());

    }



}
