package com.example.karol.buscador.Controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karol.buscador.Model.Product;
import com.example.karol.buscador.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by karol on 1/9/2017.
 */

public class ProductsListAdapter extends ArrayAdapter<Product> {
    Context context;
    int layoutResourceId;
    ArrayList<Product> data = new ArrayList<Product>();

    public ProductsListAdapter(Context context, ArrayList<Product> data, int resourceId){
        super(context,resourceId,data);
        this.context=context;
        this.data = data;
        this.layoutResourceId =resourceId;

    }

    public View getView (int pos, View convertView, ViewGroup parent){
        View row = convertView;
        ProductHolder holder = null;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new ProductHolder();
            holder.image = row.findViewById(R.id.item_image);
            holder.name = row.findViewById(R.id.item_name);
            holder.price = row.findViewById(R.id.item_price);
            row.setTag(holder);
        }
        else{
            holder = (ProductHolder)row.getTag();
        }

        Product product = data.get(pos);
        holder.name.setText(product.title);
        holder.price.setText(String.valueOf(product.price));
        new DownloadImageTask(holder.image).execute(product.imgUrl);
        //holder.image.setImageBitmap();
        return row;
    }

    static public class ProductHolder{
        ImageView image;
        TextView name;
        TextView price;
    }

     private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", "image download error");
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            //set image of your imageview
            bmImage.setImageBitmap(result);
            //close
        }
    }
}
