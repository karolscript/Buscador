package com.example.karol.buscador.Service;

import android.app.Activity;

import com.example.karol.buscador.Model.Product;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by karol on 1/9/2017.
 */

public class JsonInterpreter extends Activity {
    private ArrayList<Product> productsList = new ArrayList<>();
    private String jsonResponse;


    /**
     * Reads JsonObbject and organizes it in order
     * to fill the list of products.
     */
    public void readAndTransform(){
        try {

            JSONObject mainNode = new JSONObject(getJsonResponse());

            if (mainNode.has("contents")){
                if (mainNode.get("contents") instanceof JSONArray) {
                    JSONArray mainNodeJSONArray = mainNode.getJSONArray("contents");
                    JSONObject node = mainNodeJSONArray.getJSONObject(0);
                    fillListFromJson(node);
                }
                else
                {
                    fillListFromJson(mainNode);
                }
            }

            } catch (Exception e1) {
            e1.printStackTrace();
        }
     }


    /**
     * @return the list of products.
     */
    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    /**
     * @return the JsonResponse
     */
    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public void fillListFromJson(JSONObject node) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(node.toString());

        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String,JsonNode> field = fieldsIterator.next();

            if (field.getValue() instanceof ArrayNode){
                Iterator<JsonNode> mainContentIterator = field.getValue().elements();
                while (mainContentIterator.hasNext()) {
                    JsonNode mainContent = mainContentIterator.next();
                    if ((mainContent.has("contents")) && (mainContent.get("contents") instanceof ArrayNode)){
                        Iterator<JsonNode> contentsIterator = mainContent.get("contents").elements();
                        while (contentsIterator.hasNext()) {
                            JsonNode contents = contentsIterator.next();
                            if(contents.has("records")){
                                if (contents.get("records") instanceof ArrayNode){
                                    Iterator <JsonNode> recordsIterator = contents.get("records").elements();
                                    while (recordsIterator.hasNext()){
                                        JsonNode record = recordsIterator.next();
                                        if (record.has("attributes")){
                                            System.out.println("ATRIBUTOS " + record.findValue("attributes").toString());
                                            Product product =
                                                    new Product(record.findValue("attributes").findValue("product.smallImage").toString()
                                                            .substring(2,record.findValue("attributes").findValue("product.smallImage").toString().length()-2),
                                                            record.findValue("attributes").findValue("product.displayName").toString()
                                                                    .substring(1,record.findValue("attributes").findValue("product.displayName").toString().length()-1),
                                                            Double.parseDouble(record.findValue("attributes").findValue("sku.list_Price").toString()
                                                                    .substring(2,record.findValue("attributes").findValue("sku.list_Price").toString().length()-2))
                                                    );
                                            System.out.println("Nombre "+ product.title);
                                            System.out.println("Imagen URL "+ product.imgUrl);
                                            System.out.println("Precio "+ product.price);
                                            if (product !=null){
                                                productsList.add(product);
                                                System.out.println("LISTA PRODUCTO "+ productsList.size());
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

    }
}
