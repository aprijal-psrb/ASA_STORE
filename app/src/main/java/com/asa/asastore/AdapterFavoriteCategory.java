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
public class AdapterFavoriteCategory extends ArrayAdapter<DataFavorite> {

    public AdapterFavoriteCategory(Context context, int resource, List<DataFavorite> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view,ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_favorite_category,null);
        }
        DataFavorite dataFavorite = getItem(position);
        String id_favorite = dataFavorite.getId_favorite();
        String warna_favorite = dataFavorite.getWarna_favorite();
        String nama_favorite = dataFavorite.getNama_favorite();
        String deskripsi = dataFavorite.getDeskripsi();
        if(dataFavorite != null){
            TextView namaFavorite = (TextView)view.findViewById(R.id.categoryName);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_list_category);
            namaFavorite.setText(nama_favorite);
            imageView.setBackgroundColor(Color.parseColor(warna_favorite));
        }
        return view;
    }
}
