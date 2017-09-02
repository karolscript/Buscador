package com.example.karol.buscador.Servicio;

import com.example.karol.buscador.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by karol on 1/9/2017.
 */

public class LlenadoListaProductos {


    public void llenado(String criterio){
        System.out.println("CRITERIO "+criterio);
        ApiEndPointInterface llamada = ApiClient.getClient().create(ApiEndPointInterface.class);
        Call <String> respuesta = llamada.solicitarJson(criterio);

        respuesta.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.isSuccessful())
                        System.out.println("HUBO RESPUESTA");
                    String  respuesta = response.body().toString();
                    System.out.println("RESPUESTA " + respuesta);
                }catch (Exception e){
                    System.out.println("FALLó");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("FALLÓ");
            }
        });
    }
}
