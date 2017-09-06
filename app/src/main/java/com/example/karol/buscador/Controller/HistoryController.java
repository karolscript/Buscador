package com.example.karol.buscador.Controller;

import android.content.Context;

import com.example.karol.buscador.Service.DAOHistorical;

import org.json.JSONArray;

/**
 * Created by karol on 5/9/2017.
 */

public class HistoryController {
    public static boolean create(Context context, String filename, String jsonString){
        return DAOHistorical.create(context,filename,jsonString);
    }

    public static boolean isFilePresent(Context context, String filename){
        return DAOHistorical.isFilePresent(context,filename);
    }

    public static String read(Context context, String filename){
        return DAOHistorical.read(context,filename);
    }

    public static void saveData(String data, Context context, String fileName){
        DAOHistorical.saveData(context,data,fileName);
    }

    public static boolean isContained(JSONArray jsonArray, String entry){
        return DAOHistorical.contains(jsonArray,entry);
    }
}
