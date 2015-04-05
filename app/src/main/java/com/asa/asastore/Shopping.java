package com.asa.asastore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 30/03/2015.
 */
public class Shopping extends Fragment {
    public static ListView listViewSupplier;
    ListView listViewShopping;
    ArrayList<DataBarang> arrayListBarang;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup,Bundle bundle){
        Log.d("Shopping", "onCreateView");
        View rootView = inflater.inflate(R.layout.activity_shopping, viewGroup, false);
        listViewShopping = (ListView)rootView.findViewById(R.id.list_shopping);
        listViewSupplier = (ListView)rootView.findViewById(R.id.list_supplier);
        listViewSupplier.setAdapter(MainActivity.adapterShoppingSupplier);
        listViewSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayListBarang = new ArrayList<>();
                String id_penjual = MainActivity.listDataSupplier.get(position).getId_penjual();
                for (int i = 0; i < MainActivity.listDataBarang.size(); i++){
                    if(id_penjual.equals(MainActivity.listDataBarang.get(i).getId_penjual())){
                        arrayListBarang.add(MainActivity.listDataBarang.get(i));
                    }
                    AdapterBarang arrayAdapterBarang = new AdapterBarang(getActivity(),android.R.layout.simple_list_item_1,arrayListBarang);
                    listViewShopping.setAdapter(arrayAdapterBarang);
                }
            }
        });
        return rootView;
    }
}
