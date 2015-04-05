package com.asa.asastore;

import android.content.Context;
import android.graphics.Color;
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
public class AdapterFavoriteCategory extends ArrayAdapter<ArrayList<String>> {
    public AdapterFavoriteCategory(Context context, int resource) {
        super(context, resource);
    }

    public AdapterFavoriteCategory(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AdapterFavoriteCategory(Context context, int resource, ArrayList<String>[] objects) {
        super(context, resource, objects);
    }

    public AdapterFavoriteCategory(Context context, int resource, int textViewResourceId, ArrayList<String>[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AdapterFavoriteCategory(Context context, int resource, List<ArrayList<String>> objects) {
        super(context, resource, objects);
    }

    public AdapterFavoriteCategory(Context context, int resource, int textViewResourceId, List<ArrayList<String>> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View view,ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_favorite_category,null);
        }
        List<String> list = getItem(position);
        String id_favorite = list.get(0);
        String warna_favorite = list.get(1);
        String nama_favorite = list.get(2);
        String deskripsi = list.get(3);
        if(list != null){
            TextView namaFavorite = (TextView)view.findViewById(R.id.categoryName);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_list_category);
            namaFavorite.setText(nama_favorite);
            imageView.setBackgroundColor(Color.parseColor(warna_favorite));
        }
        return view;
    }

}
