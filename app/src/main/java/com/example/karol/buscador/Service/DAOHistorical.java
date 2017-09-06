package com.example.karol.buscador.Service;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by karol on 5/9/2017.
 */

public class DAOHistorical {
    public static String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    public static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_APPEND);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            Log.e("TAG",fileNotFound.getLocalizedMessage());
            return false;
        } catch (IOException ioException) {
            Log.e("TAG",ioException.getLocalizedMessage());
            return false;
        }

    }

    public static boolean isFilePresent(Context context, String fileName) {
        Log.d("TRACE","isFilePresent()");
        try {
            File[] files =  new File(context.getFilesDir().getPath().toString()).listFiles();
            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());
                if (files[i].getName().equals(fileName)) {  // the resouce exists...
                    return true;
                }
            }
             return false;
        }catch (Exception e){
             Log.e("ERROR",e.getLocalizedMessage());
             return false;
        }
    }

    public static void saveData(Context context, String mJsonResponse, String fileName) {
        Log.i("TRACE","---Hemos entrado en saveData()----");
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(mJsonResponse.getBytes());
            fos.write(("").getBytes());
            fos.close();
            } catch (IOException e) {
                e.getLocalizedMessage();
        }
    }

    public static boolean contains(JSONArray jsonArray, String entry){
        for (int i=0;i<jsonArray.length();i++){
            try {
                if (jsonArray.getString(i).equals(entry))
                    return true;
            } catch (JSONException e) {
                Log.e("DAOHistorical",e.getLocalizedMessage());
            }
        }
        return false;
    }
}
