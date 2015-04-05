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
import java.util.Objects;

/**
 * Created by APRIJAL_PASARIBU on 03/04/2015.
 */
public class FavoriteCategoryAdapter extends ArrayAdapter<FavoriteCategory> {
    public FavoriteCategoryAdapter(Context context, int resource, List<FavoriteCategory> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View view,ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_favorite_category,null);
        }
        FavoriteCategory category = getItem(position);
        if(category != null){
            TextView productName = (TextView)view.findViewById(R.id.categoryName);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_list_category);
            productName.setText(category.getCategoryName());
            imageView.setBackgroundColor(Color.parseColor(category.getCategoryColor()));
        }
        return view;
    }
}
