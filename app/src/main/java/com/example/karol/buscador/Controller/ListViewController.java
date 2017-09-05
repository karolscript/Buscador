package com.example.karol.buscador.Controller;

import android.content.SharedPreferences;

import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.Service.JsonInterpreter;

import java.util.ArrayList;

/**
 * Created by karol on 3/9/2017.
 */

public class ListViewController {
    private static SharedPreferences sharedPreferences;
    private static JsonInterpreter jsonInterpreter = new JsonInterpreter();


    public static ArrayList<Product> fillProductsList(String response){

        jsonInterpreter.setJsonResponse(response);
        jsonInterpreter.leyendo();
        return jsonInterpreter.getProductsList();
    }
}
