package com.example.karol.buscador.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.karol.buscador.Controller.ListViewController;
import com.example.karol.buscador.Controller.ProductSearchController;
import com.example.karol.buscador.Controller.ProductsListAdapter;
import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.R;
import com.example.karol.buscador.Service.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    ListView productsListView;
    SearchView productSearchView;
    private View header;
    Context context;
    ListViewController listViewController = new ListViewController();
    ProductSearchController productSearchController = new ProductSearchController();
    ArrayList<Product> foundProductsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //initComponents();
        productsListView = (ListView) findViewById(R.id.products_list_view);
        header = (View )getLayoutInflater().inflate(R.layout.products_list_view_header, null);
        productsListView.addHeaderView(header);
        productSearchView = (SearchView)findViewById(R.id.product_search_view);
        context = this;
        // perform set on query text listener event
        productSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            // do something on text submit

                foundProductsList.clear();
                getJsonList(query,context);

                return false;
        }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes
                return false;
            }
        });

    }
    /**
     * Method tha initializes all the attributes and views once
     * the searchActivity is created.
     */
    public void initComponents(){

       /* LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout headerParent = (LinearLayout)findViewById(R.id.products_list_view_header);
        LinearLayout activityParent = (LinearLayout)findViewById(R.id.activity_layout);
        headerRoot = inflater.inflate(R.layout.products_list_view_header,headerParent,true);
        productSearchView = headerRoot.findViewById(R.id.product_search_view);
        headerTextView = headerRoot.findViewById(R.id.header_text_view);
        View view = inflater.inflate(R.layout.activity_search,activityParent,true);
        productsListView = view.findViewById(R.id.products_list_view);
        productsListView.addHeaderView(headerRoot);*/




       // query = productSearchView.getQuery();

    }

    public void getJsonList(String product, final Context context){
        String URL = VolleySingleton.BASE_URL+product+VolleySingleton.END_URL;
        Log.i("TRACE", "geJsonList()");
        getResponse(URL,
                new SearchActivity.VolleyCallback() {
                    @Override
                    public void onSuccessResponse(String result) {
                        try {
                            Log.i("onSuccesResponse","Succesful");
                            foundProductsList = listViewController.fillProductsList(result);
                            ProductsListAdapter adapter = new ProductsListAdapter(context, R.layout.list_view_item, foundProductsList);
                            productsListView.setAdapter(adapter);
                        } catch (Exception e) {
                            //otro = "error";
                            e.printStackTrace();
                        }
                    }
                }, context);
    }

    public interface VolleyCallback {
        void onSuccessResponse(String result);
    }

    public void getResponse(String url, final SearchActivity.VolleyCallback callback, final Context context) {

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
        VolleySingleton.getInstance(context).getRequestQueue().add(strreq);
    }


}
