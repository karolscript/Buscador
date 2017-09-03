package com.example.karol.buscador.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.R;

import java.util.ArrayList;

/**
 * Created by karol on 1/9/2017.
 */

public class ProductsListAdapter extends ArrayAdapter<Product> {
    Context context;
    int layoutResourceId;
    ArrayList<Product> data = null;

    public ProductsListAdapter(Context context, int layoutResourceId, ArrayList<Product> data){
        super(context,layoutResourceId,data);
        this.context=context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;

    };

    public View getView (int pos, View convertView, ViewGroup parent){
        View row = convertView;
        ProductHolder holder = null;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new ProductHolder();
            holder.image = (ImageView) row.findViewById(R.id.itemImage);
            holder.text = (TextView) row.findViewById(R.id.itemName);
            row.setTag(holder);
        }
        else{
            holder = (ProductHolder)row.getTag();
        }

        Product product = data.get(pos);
        holder.text.setText(product.title);
        holder.image.setImageResource(product.icon);


        return row;
    }

    static public class ProductHolder{
        ImageView image;
        TextView text;
    }
}
