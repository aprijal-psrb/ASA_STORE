package com.asa.asastore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 03/04/2015.
 */
public class AdapterShoppingSupplier extends ArrayAdapter<DataSupplier> {

    public AdapterShoppingSupplier(Context context, int resource, List<DataSupplier> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(android.R.layout.simple_list_item_1,null);
        }
        DataSupplier dataSupplier = getItem(position);
        if(dataSupplier != null){
            TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
            textView1.setText(dataSupplier.getNama_toko());
        }
        return view;
    }
}
