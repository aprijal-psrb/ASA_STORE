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
public class AdapterBarang extends ArrayAdapter<DataBarang> {

    public AdapterBarang(Context context, int resource, List<DataBarang> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_home,null);
        }
        DataBarang dataBarang = getItem(position);
        if(dataBarang != null){
            TextView productName = (TextView)view.findViewById(R.id.text_productName);
            TextView productPrice = (TextView)view.findViewById(R.id.text_product_price);
            TextView productDate = (TextView)view.findViewById(R.id.text_product_stock_date);
            TextView productCategory = (TextView)view.findViewById(R.id.text_product_category);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_status_favorite);
            productName.setText(dataBarang.getNama_barang());
            productPrice.setText("Rp "+dataBarang.getHarga_barang());
            productDate.setText(dataBarang.getTgl_harga_stok_barang());
            productCategory.setText(dataBarang.getNama_kategori_barang());
            imageView.setBackgroundColor(Color.parseColor(dataBarang.getWarna_favorite()));
        }
        return view;
    }
}
