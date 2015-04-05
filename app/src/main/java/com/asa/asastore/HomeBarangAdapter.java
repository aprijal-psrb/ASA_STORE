package com.asa.asastore;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asa.asastore.MainActivity;
import com.asa.asastore.R;

import java.util.List;

class HomeBarangAdapter extends ArrayAdapter<Barang> {
    public HomeBarangAdapter(Context context, int textViewResourceId){
        super(context,textViewResourceId);
    }
    public HomeBarangAdapter(Context context, int resource, List<Barang> items) {
        super(context, resource, items);
    }
    @Override
    public View getView(int position, View view,ViewGroup parent){
        if(view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_home,null);
        }
        Barang barang = getItem(position);
        if(barang != null){
            TextView productName = (TextView)view.findViewById(R.id.text_productName);
            TextView productPrice = (TextView)view.findViewById(R.id.text_product_price);
            TextView productDate = (TextView)view.findViewById(R.id.text_product_stock_date);
            TextView productCategory = (TextView)view.findViewById(R.id.text_product_category);
            ImageView imageView = (ImageView)view.findViewById(R.id.image_status_favorite);
            productName.setText(barang.getNama_barang());
            productPrice.setText("Rp "+barang.getHarga_barang());
            productDate.setText(barang.getTgl_harga_stok_barang());
            productCategory.setText(barang.getKategori_barang());
            //imageView.setBackgroundColor(Color.parseColor(barang.getColorFavorite()));

        }
        return view;
    }

}
