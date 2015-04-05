package com.asa.asastore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 05/04/2015.
 */
public class AdapterMerek extends ArrayAdapter<DataMerek> {
    DataMerek merek;

    public AdapterMerek(Context context, int resource, List<DataMerek> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(android.R.layout.simple_list_item_1,null);
        }
        merek = getItem(position);
        if(merek != null){
            TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
            textView1.setText(merek.getNama_merek());
        }
        return view;
    }
}
