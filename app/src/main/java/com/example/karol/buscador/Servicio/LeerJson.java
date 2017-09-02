package com.example.karol.buscador.Servicio;

import android.app.Activity;
import android.content.Context;

import com.example.karol.buscador.Producto;
import com.example.karol.buscador.R;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by karol on 1/9/2017.
 */

public class LeerJson extends Activity {

    private ArrayList<Producto> listaProductos = new ArrayList<Producto>();

    public void leyendo(Context context){
        try {
            InputStream busqueda;
            busqueda = context.getResources().openRawResource(R.raw.busquedareloj);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int reads = busqueda.read();

            while (reads != -1) {
                baos.write(reads);
                reads = busqueda.read();
            }
            byte[] jsonData = baos.toByteArray();

            System.out.println("TAMAÑO " + jsonData.length);

            String testV = new JSONObject(new String(jsonData)).toString();

            System.out.println("JSON ES " + testV);


            JSONObject nodo = new JSONObject(new String(jsonData));

            JSONArray jArr = nodo.getJSONArray("contents");

            JSONObject nodo2 = jArr.getJSONObject(0);




            JsonFactory factory = new JsonFactory();

            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode rootNode = mapper.readTree(nodo2.toString());

            Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {

                Map.Entry<String,JsonNode> field = fieldsIterator.next();

                    if (field.getValue() instanceof ArrayNode){

                        System.out.println(field.getKey() + " ES ARREGLO");
                        Iterator<JsonNode> mainContentIterator = field.getValue().elements();
                        while (mainContentIterator.hasNext()) {
                            JsonNode mainContent = mainContentIterator.next();
                            System.out.println("Content Key: " + mainContent.toString()/* + "\tValue:" + mainContent.getValue()*/);

                            if ((mainContent.has("contents")) && (mainContent.get("contents") instanceof ArrayNode)){
                                Iterator<JsonNode> contentsIterator = mainContent.get("contents").elements();
                                while (contentsIterator.hasNext()) {
                                    JsonNode contents = contentsIterator.next();
                                    System.out.println("contents " + contents.toString());
                                    if(contents.has("records")){
                                        System.out.println("AQUÍ ESTAN");
                                        if (contents.get("records") instanceof ArrayNode){
                                            Iterator <JsonNode> recordsIterator = contents.get("records").elements();
                                            while (recordsIterator.hasNext()){
                                                JsonNode record = recordsIterator.next();
                                                if (record.has("attributes")){
                                                    System.out.println("ATRIBUTOS " + record.findValue("attributes").toString());
                                                    Producto producto =
                                                       new Producto(record.findValue("attributes").findValue("product.smallImage").toString()
                                                               .substring(1,record.findValue("attributes").findValue("product.smallImage").toString().length()-1),
                                                                    record.findValue("attributes").findValue("product.displayName").toString()
                                                                            .substring(1,record.findValue("attributes").findValue("product.displayName").toString().length()-1),
                                                               Double.parseDouble(record.findValue("attributes").findValue("sku.list_Price").toString()
                                                                            .substring(2,record.findValue("attributes").findValue("sku.list_Price").toString().length()-2))
                                                               );
                                                    System.out.println("Nombre "+ record.findValue("attributes").findValue("product.displayName").toString()
                                                            .substring(1,record.findValue("attributes").findValue("product.displayName").toString().length()-1));
                                                    System.out.println("Imagen URL "+ record.findValue("attributes").findValue("product.smallImage").toString()
                                                            .substring(1,record.findValue("attributes").findValue("product.smallImage").toString().length()-1));
                                                    System.out.println("Precio "+ record.findValue("attributes").findValue("sku.list_Price").toString()
                                                            .substring(2,record.findValue("attributes").findValue("sku.list_Price").toString().length()-2));
                                                    if (producto!=null){
                                                        listaProductos.add(producto);
                                                        System.out.println("LISTA PRODUCTO "+ listaProductos.size());
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                          /*  else
                                System.out.println("contents NO ES ARRAY " + mainContent.toString());*/



                        }
                    }

                System.out.println("Field Key: " + field.getKey() + "\tValue:" + field.getValue());
            }

            } catch (Exception e1) {
            e1.printStackTrace();
        }
     }

}
