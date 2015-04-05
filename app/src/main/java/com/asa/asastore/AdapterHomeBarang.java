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
public class AdapterHomeBarang extends ArrayAdapter<DataBarang> {
    private DataBarang dataBarang;


    public AdapterHomeBarang(Context context, int resource) {
        super(context, resource);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AdapterHomeBarang(Context context, int resource, DataBarang[] objects) {
        super(context, resource, objects);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId, DataBarang[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public AdapterHomeBarang(Context context, int resource, List<DataBarang> objects) {
        super(context, resource, objects);
    }

    public AdapterHomeBarang(Context context, int resource, int textViewResourceId, List<DataBarang> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_home,null);
        }
        dataBarang = getItem(position);
        if(dataBarang != null){
            TextView productName = (TextView)view.findViewById(R.id.text_productName);
            TextView productPrice = (TextView)view.findViewById(R.id.text_product_price);
            TextView productDate = (TextView)view.findViewById(R.id.text_product_stock_date);
            TextView productCategory = (TextView)view.findViewById(R.id.text_product_category);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_status_favorite);
            productName.setText(dataBarang.getNama_barang());
            productPrice.setText("Rp "+dataBarang.getHarga_barang());
            productDate.setText(dataBarang.getTgl_harga_stok_barang());
            productCategory.setText(dataBarang.getKategori_barang());
            imageView.setBackgroundColor(Color.parseColor(getColorFavorite()));
        }
        return view;
    }

    public String getColorFavorite(){
        for(int i = 0; i < MainActivity.listDataFavorite.size(); i++){
            if(dataBarang.getId_favorite().equals(MainActivity.listDataFavorite.get(i).getId_favorite())){
                return MainActivity.listDataFavorite.get(i).getWarna_favorite();
            }
        }
        return "#00000000";
    }
}
