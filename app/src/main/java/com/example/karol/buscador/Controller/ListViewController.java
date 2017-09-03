package com.example.karol.buscador.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.Service.DAOProduct;
import com.example.karol.buscador.Service.JsonInterpreter;
import com.example.karol.buscador.Service.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by karol on 3/9/2017.
 */

public class ListViewController {
    private static DAOProduct daoProduct = new DAOProduct();
    private static SharedPreferences sharedPreferences;
    private static JsonInterpreter jsonInterpreter = new JsonInterpreter();

    public static ArrayList<Product> fillProductsList(Context context, String product){
        daoProduct.getJsonList(product,context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String response = sharedPreferences.getString("Response", "");
        System.out.println("CONTROLLER " + response);
        jsonInterpreter.setJsonResponse(response);
        jsonInterpreter.leyendo();
        return jsonInterpreter.getProductsList();
    }
}
