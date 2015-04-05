package com.asa.asastore;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 03/04/2015.
 */
public class AdapterHomeBarang extends ArrayAdapter<ArrayList<String>> {
    private ArrayList<String> list;


    public AdapterHomeBarang(Context context, int resource) {
        super(context, resource);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AdapterHomeBarang(Context context, int resource, ArrayList<String>[] objects) {
        super(context, resource, objects);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId, ArrayList<String>[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AdapterHomeBarang(Context context, int resource, List<ArrayList<String>> objects) {
        super(context, resource, objects);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId, List<ArrayList<String>> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_home,null);
        }
        list = getItem(position);
        if(list != null){
            TextView productName = (TextView)view.findViewById(R.id.text_productName);
            TextView productPrice = (TextView)view.findViewById(R.id.text_product_price);
            TextView productDate = (TextView)view.findViewById(R.id.text_product_stock_date);
            TextView productCategory = (TextView)view.findViewById(R.id.text_product_category);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_status_favorite);
            productName.setText(list.get(5));
            productPrice.setText("Rp "+list.get(8));
            productDate.setText(list.get(9));
            productCategory.setText(list.get(12));
            imageView.setBackgroundColor(Color.parseColor(getColorFavorite()));
        }
        return view;
    }

    public String getColorFavorite(){
        for(int i = 0; i < MainActivity.listFavoriteCategory.size(); i++){
            if(list.get(14).equals(MainActivity.listFavoriteCategory.get(i).get(0))){
                return MainActivity.listFavoriteCategory.get(i).get(1);
            }
        }
        return "#00000000";
    }
}
