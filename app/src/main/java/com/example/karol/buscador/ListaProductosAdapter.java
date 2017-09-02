package com.example.karol.buscador;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by karol on 1/9/2017.
 */

public class ListaProductosAdapter extends ArrayAdapter<Producto> {
    Context context;
    int layoitResourceId;

    public ListaProductosAdapter(Context context, int layoitResourceId, List<Producto> productos){
        super(context,layoitResourceId,productos);

    };

}
