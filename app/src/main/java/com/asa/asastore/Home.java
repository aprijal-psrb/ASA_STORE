package com.asa.asastore;

import android.accounts.NetworkErrorException;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.ConnectionClosedException;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.ConnectException;
import java.net.NetPermission;
import java.net.NetworkInterface;
import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 30/03/2015.
 */
public class Home extends Fragment {
    static ListView listViewBarang, listViewRecent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View rootView = inflater.inflate(R.layout.activity_home, viewGroup, false);
        listViewBarang = (ListView) rootView.findViewById(R.id.list_home);
        listViewRecent = (ListView) rootView.findViewById(R.id.list_recently);
        listViewBarang.setAdapter(MainActivity.adapterHomeBarang);
        listViewBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final LayoutInflater inflater = getActivity().getLayoutInflater();
                final View mView = inflater.inflate(R.layout.product_detail, null);
                builder.setView(mView).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setTitle("Product Details");
                final TextView tvName = (TextView) mView.findViewById(R.id.TextView_product_name);
                final TextView tvBrand = (TextView) mView.findViewById(R.id.TextView_product_brand);
                TextView tvPrice = (TextView) mView.findViewById(R.id.TextView_product_price);
                TextView tvNumUnit = (TextView) mView.findViewById(R.id.TextView_product_num_unit);
                final TextView tvCategory = (TextView) mView.findViewById(R.id.TextView_product_category);
                final TextView tvSupplier = (TextView) mView.findViewById(R.id.TextView_product_supplier);
                final TextView tvDesc = (TextView) mView.findViewById(R.id.TextView_product_description);
                tvName.setText(MainActivity.listDataBarang.get(position).getNama_barang());
                tvBrand.setText(MainActivity.listDataBarang.get(position).getNama_merek());
                final String price = MainActivity.listDataBarang.get(position).getHarga_barang();
                tvPrice.setText("Rp " + price);
                final String stok = MainActivity.listDataBarang.get(position).getStok_barang();
                String satuan = MainActivity.listDataBarang.get(position).getSatuan_barang();
                tvNumUnit.setText(stok + " " + satuan);
                tvCategory.setText(MainActivity.listDataBarang.get(position).getKategori_barang());
                tvSupplier.setText(MainActivity.listDataBarang.get(position).getNama_toko());
                tvDesc.setText(MainActivity.listDataBarang.get(position).getDeskripsi_barang());
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder edit = new AlertDialog.Builder(getActivity());
                        View mView = inflater.inflate(R.layout.edit_product_detail, null);
                        final EditText tvNameEdit = (EditText) mView.findViewById(R.id.editText_product_name);
                        EditText tvBrandEdit = (EditText) mView.findViewById(R.id.editText_product_brand);
                        final EditText tvPriceEdit = (EditText) mView.findViewById(R.id.editText_product_price);
                        final EditText tvNumUnitEdit = (EditText) mView.findViewById(R.id.editText_product_num_unit);
                        final EditText tvCategoryEdit = (EditText) mView.findViewById(R.id.editText_product_category);
                        EditText tvSupplierEdit = (EditText) mView.findViewById(R.id.editText_product_supplier);
                        final EditText tvDescEdit = (EditText) mView.findViewById(R.id.editText_product_description);
                        tvNameEdit.setText(tvName.getText().toString());
                        tvBrandEdit.setText(tvBrand.getText().toString());
                        tvPriceEdit.setText(price);
                        tvNumUnitEdit.setText(stok);
                        tvCategoryEdit.setText(tvCategory.getText().toString());
                        tvSupplierEdit.setText(tvSupplier.getText().toString());
                        tvDescEdit.setText(tvDesc.getText().toString());
                        edit.setView(mView);
                        edit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBarang barang = new DataBarang();
                                barang.setId_barang(MainActivity.listDataBarang.get(position).getId_barang());
                                barang.setNama_barang(tvNameEdit.getText().toString());
                                barang.setHarga_barang(tvPriceEdit.getText().toString());
                                barang.setStok_barang(tvNumUnitEdit.getText().toString());
                                barang.setKategori_barang(tvCategoryEdit.getText().toString());
                                barang.setDeskripsi_barang(tvDescEdit.getText().toString());
                                barang.setPosition(position);
                                new EditBarang().execute(barang);
                            }
                        });
                        edit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        edit.show();
                    }
                });
                builder.show();
            }
        });
        listViewBarang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.inflate(R.menu.menu_home);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.addToFavorite:
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setAdapter(MainActivity.adapterFavoriteCategory, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String id_favorite = MainActivity.listDataFavorite.get(which).getId_favorite();
                                        String id_barang = MainActivity.listDataBarang.get(position).getId_barang();
                                        new SetFavorite().execute(id_favorite, id_barang);
                                        MainActivity.listDataBarang.get(position).setId_favorite(id_favorite);
                                    }
                                });
                                builder.show();
                                return true;
                            case R.id.clearFavorite:
                                String id_barang = MainActivity.listDataBarang.get(position).getId_barang();
                                MainActivity.listDataBarang.get(position).setId_favorite("");
                                new ClearFavorite().execute(id_barang);
                                return true;
                            case R.id.deleteHome:
                                AlertDialog.Builder dia = new AlertDialog.Builder(getActivity());
                                dia.setMessage("Are you sure!!!").setPositiveButton("YES",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new DeleteData().execute(position);
                                        MainActivity.listDataBarang.remove(position);
                                    }
                                }).setNegativeButton("NO",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
                return true;
            }

        });
        Button btnTambahBarang = (Button)rootView.findViewById(R.id.btnTambahBarang);
        btnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = getActivity().getLayoutInflater();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                View mView = inflater.inflate(R.layout.add_data_barang, null);
                final EditText nama = (EditText) mView.findViewById(R.id.editText_product_name);
                final AutoCompleteTextView merek = (AutoCompleteTextView)mView.findViewById(R.id.TextView_product_brand);
                final EditText harga = (EditText) mView.findViewById(R.id.editText_product_price);
                final EditText jumlahBarang = (EditText) mView.findViewById(R.id.editText_product_stock);
                final AutoCompleteTextView satuan = (AutoCompleteTextView) mView.findViewById(R.id.autoComp_product_unit);
                final Spinner kategori = (Spinner) mView.findViewById(R.id.spinner_product_category);
                final Spinner penjual = (Spinner) mView.findViewById(R.id.spinner_supplier);
                final Button btnAddSupplier = (Button)mView.findViewById(R.id.btn_add_supplier);
                final EditText deskripsi = (EditText) mView.findViewById(R.id.editText_product_description);
                btnAddSupplier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder tambahDialog = new AlertDialog.Builder(getActivity());
                        View viewTambah = inflater.inflate(R.layout.add_data_supplier,null);
                        final EditText namaToko = (EditText)viewTambah.findViewById(R.id.editText_supplier_name);
                        final EditText alamatToko = (EditText)viewTambah.findViewById(R.id.editText_supplier_address);
                        final EditText kontakToko = (EditText)viewTambah.findViewById(R.id.editText_supplier_contact);
                        tambahDialog.setView(viewTambah);
                        tambahDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataSupplier supplier = new DataSupplier();
                                supplier.setNama_toko(namaToko.getText().toString());
                                supplier.setAlamat_toko(alamatToko.getText().toString());
                                supplier.setKontak_toko(kontakToko.getText().toString());
                                new NewSupplier().execute(supplier);
                            }
                        });
                        tambahDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        tambahDialog.show();
                    }
                });
                merek.setAdapter(MainActivity.adapterMerek);
                List<String> listSatuan = new ArrayList<>();
                listSatuan.add("Unit");
                listSatuan.add("Kotak");
                listSatuan.add("Lusin");
                listSatuan.add("Botol");
                ArrayAdapter adapterSatuan = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listSatuan);
                satuan.setAdapter(adapterSatuan);
                List<String> listKategori = new ArrayList<>();
                listKategori.add("Elektronik");
                listKategori.add("Automotif");
                ArrayAdapter adapterKategory = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listKategori);
                kategori.setAdapter(adapterKategory);
                penjual.setAdapter(MainActivity.adapterNamaToko);
                dialog.setView(mView);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBarang barang = new DataBarang();
                        barang.setNama_barang(nama.getText().toString());
                        barang.setMerek_barang(merek.getText().toString());
                        barang.setHarga_barang(harga.getText().toString());
                        barang.setStok_barang(jumlahBarang.getText().toString());
                        barang.setSatuan_barang(satuan.getText().toString());
                        barang.setKategori_barang(kategori.getSelectedItem().toString());
                        barang.setId_penjual(penjual.getSelectedItem().toString());
                        barang.setDeskripsi_barang(deskripsi.getText().toString());
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        return rootView;
    }

    private class SetFavorite extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... arg) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite", arg[0]));
            ls.add(new BasicNameValuePair("id_barang", arg[1]));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/set-favorite.php", "POST", ls);
            if(json == null){
                return 1;
            }
            return 0;
        }
        @Override
        public void onPostExecute(Integer arg){
            if(arg == 0) {
                MainActivity.adapterHomeBarang.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteData extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            String id_barang = MainActivity.listDataBarang.get(params[0]).getId_barang();
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang", id_barang));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/del-barang.php", "POST", ls);
            if(json == null){
                return 1;
            }
            return 0;
        }
        @Override
        public void onPostExecute(Integer arg){
            if(arg == 0) {
                MainActivity.adapterHomeBarang.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ClearFavorite extends AsyncTask<String,Void,Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang",params[0]));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/clear-favorite.php", "POST", ls);
            if(json == null){
                return 1;
            }
            return 0;
        }
        @Override
        public void onPostExecute(Integer arg){
            if(arg == 0) {
                MainActivity.adapterHomeBarang.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class EditBarang extends AsyncTask<DataBarang,Void,Integer>{
        DataBarang barang = new DataBarang();
        @Override
        protected Integer doInBackground(DataBarang... params){
            barang = params[0];
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang",barang.getId_barang()));
            ls.add(new BasicNameValuePair("nama_barang",barang.getNama_barang()));
            ls.add(new BasicNameValuePair("harga_barang",barang.getHarga_barang()));
            ls.add(new BasicNameValuePair("stok_barang",barang.getStok_barang()));
            ls.add(new BasicNameValuePair("kategori_barang",barang.getKategori_barang()));
            ls.add(new BasicNameValuePair("deskripsi_barang",barang.getDeskripsi_barang()));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/edit-barang.php","POST",ls);
            if(json == null){
                return 1;
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer arg){
            if(arg == 0){
                MainActivity.listDataBarang.get(barang.getPosition()).setNama_barang(barang.getNama_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setHarga_barang(barang.getHarga_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setStok_barang(barang.getStok_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setKategori_barang(barang.getKategori_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setDeskripsi_barang(barang.getDeskripsi_barang());
                MainActivity.adapterHomeBarang.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class NewBarang extends AsyncTask<DataBarang,Void,Integer>{
        DataBarang barang;
        @Override
        protected Integer doInBackground(DataBarang... params) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("nama_barang",barang.getNama_barang()));
            ls.add(new BasicNameValuePair("harga_barang",barang.getHarga_barang()));
            ls.add(new BasicNameValuePair("stok_barang",barang.getStok_barang()));
            ls.add(new BasicNameValuePair("kategori_barang",barang.getKategori_barang()));
            ls.add(new BasicNameValuePair("deskripsi_barang",barang.getDeskripsi_barang()));
            //JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/edit-barang.php","POST",ls);
            //if(json == null){
            //    return 1;
            //}
            return 0;
        }
    }

    private class NewSupplier extends AsyncTask<DataSupplier,Void,Integer>{

        @Override
        protected Integer doInBackground(DataSupplier... params) {
            return null;
        }
    }
}
