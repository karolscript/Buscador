package com.example.karol.buscador.Servicio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karol on 2/9/2017.
 */

public class VolleyService {

    public void traerJson(String URL, final Context context){
        Log.i("TRACE", "INERT ACTIVITY REQUEST");
        getResponse(URL,
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        try {
                            sharedResponse(result,context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, context);
    }


    public interface VolleyCallback {
        void onSuccessResponse(String result);
    }

    public void getResponse(String url, final VolleyCallback callback, final Context context) {

        VolleySingleton.getInstance(context).getRequestQueue();

        StringRequest strreq = new StringRequest(Request.Method.GET, url, new Response.Listener < String > () {

            @Override
            public void onResponse(String Response) {

                callback.onSuccessResponse(Response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
            }
        })
        {
            @Override
            public Map< String, String > getHeaders() throws com.android.volley.AuthFailureError {
                Map < String, String > params = new HashMap< String, String >();
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(strreq);
    }

    private void sharedResponse(String response, Context context) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putString("Response", response);
        editor.commit();
    }

}
