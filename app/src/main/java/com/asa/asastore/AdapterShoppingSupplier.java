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
public class AdapterShoppingSupplier extends ArrayAdapter<ArrayList<String>> {
    public AdapterShoppingSupplier(Context context, int resource) {
        super(context, resource);
    }

    public AdapterShoppingSupplier(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AdapterShoppingSupplier(Context context, int resource, ArrayList<String>[] objects) {
        super(context, resource, objects);
    }

    public AdapterShoppingSupplier(Context context, int resource, int textViewResourceId, ArrayList<String>[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AdapterShoppingSupplier(Context context, int resource, List<ArrayList<String>> objects) {
        super(context, resource, objects);
    }

    public AdapterShoppingSupplier(Context context, int resource, int textViewResourceId, List<ArrayList<String>> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(android.R.layout.simple_list_item_1,null);
        }
        ArrayList<String> list = getItem(position);
        if(list != null){
            TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
            textView1.setText(list.get(2));
        }
        return view;
    }
}
