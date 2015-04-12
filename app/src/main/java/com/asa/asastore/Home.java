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
import android.text.format.Time;
import android.text.style.TtsSpan;
import android.util.Log;
import android.util.TimeUtils;
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
import java.util.Date;
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
                tvCategory.setText(MainActivity.listDataBarang.get(position).getNama_kategori_barang());
                tvSupplier.setText(MainActivity.listDataBarang.get(position).getNama_toko());
                tvDesc.setText(MainActivity.listDataBarang.get(position).getDeskripsi_barang());
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder edit = new AlertDialog.Builder(getActivity());
                        View mView = inflater.inflate(R.layout.edit_product_detail, null);
                        final EditText tvNameEdit = (EditText) mView.findViewById(R.id.editText_product_name);
                        final Spinner spinnerBrand = (Spinner) mView.findViewById(R.id.spinner_product_brand);
                        final EditText tvPriceEdit = (EditText) mView.findViewById(R.id.editText_product_price);
                        final EditText tvNumUnitEdit = (EditText) mView.findViewById(R.id.editText_product_num_unit);
                        final Spinner spinnerKategori = (Spinner) mView.findViewById(R.id.spinner_product_category);
                        final Spinner spinnerPenjual = (Spinner) mView.findViewById(R.id.spinner_product_supplier);
                        final EditText tvDescEdit = (EditText) mView.findViewById(R.id.editText_product_description);
                        tvNameEdit.setText(tvName.getText().toString());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout
                                .simple_spinner_dropdown_item,MainActivity.listMerek);
                        spinnerBrand.setAdapter(adapter);
                        tvPriceEdit.setText(price);
                        tvNumUnitEdit.setText(stok);
                        spinnerKategori.setAdapter(MainActivity.adapterNamaKategori);
                        spinnerPenjual.setAdapter(MainActivity.adapterNamaToko);
                        tvDescEdit.setText(tvDesc.getText().toString());
                        edit.setView(mView);
                        edit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBarang barang = new DataBarang();
                                barang.setId_barang(MainActivity.listDataBarang.get(position).getId_barang());
                                barang.setNama_barang(tvNameEdit.getText().toString());
                                barang.setId_merek(MainActivity.listDataMerek.get(spinnerBrand.getSelectedItemPosition()).getId_merek());
                                barang.setHarga_barang(tvPriceEdit.getText().toString());
                                barang.setStok_barang(tvNumUnitEdit.getText().toString());
                                barang.setId_kategori_barang(MainActivity.listDataKategori.get(spinnerKategori.getSelectedItemPosition())
                                        .getId_Kategori());
                                barang.setId_penjual(MainActivity.listDataSupplier.get(spinnerPenjual.getSelectedItemPosition()).getId_penjual());
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
                final Button btnAddCategory = (Button) mView.findViewById(R.id.btn_add_category);
                btnAddCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = getActivity().getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog_category, null);
                        final EditText newCateg = (EditText)view.findViewById(R.id.editTextCategory);
                        builder.setTitle("Add Category");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AddKategoriBarang().execute(newCateg.getText().toString());
                            }
                        });
                        builder.setNegativeButton("Cancel", null);
                        builder.setView(view);
                        builder.show();
                    }
                });
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
                listSatuan.add("Buah");
                ArrayAdapter adapterSatuan = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listSatuan);
                satuan.setAdapter(adapterSatuan);
                kategori.setAdapter(MainActivity.adapterNamaKategori);
                penjual.setAdapter(MainActivity.adapterNamaToko);
                dialog.setView(mView);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBarang barang = new DataBarang();
                        barang.setNama_barang(nama.getText().toString());
                        barang.setNama_merek(merek.getText().toString());
                        barang.setHarga_barang(harga.getText().toString());
                        barang.setStok_barang(jumlahBarang.getText().toString());
                        barang.setSatuan_barang(satuan.getText().toString());
                        barang.setId_kategori_barang(MainActivity.listDataKategori.get(kategori.getSelectedItemPosition()).getId_Kategori
                                ());
                        barang.setId_penjual(MainActivity.listDataSupplier.get(penjual.getSelectedItemPosition()).getId_penjual());
                        barang.setDeskripsi_barang(deskripsi.getText().toString());
                        new NewBarang().execute(barang);
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
        int pos;
        @Override
        protected Integer doInBackground(Integer... params) {
            pos = params[0];
            String id_barang = MainActivity.listDataBarang.get(pos).getId_barang();
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
                MainActivity.listDataBarang.remove(pos);
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
            ls.add(new BasicNameValuePair("id_merek",barang.getId_merek()));
            ls.add(new BasicNameValuePair("harga_barang",barang.getHarga_barang()));
            ls.add(new BasicNameValuePair("stok_barang",barang.getStok_barang()));
            ls.add(new BasicNameValuePair("kategori_barang",barang.getId_kategori_barang()));
            ls.add(new BasicNameValuePair("id_penjual",barang.getId_penjual()));
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
                MainActivity.listDataBarang.get(barang.getPosition()).setId_merek(barang.getId_merek());
                MainActivity.listDataBarang.get(barang.getPosition()).setHarga_barang(barang.getHarga_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setStok_barang(barang.getStok_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setId_kategori_barang(barang.getId_kategori_barang());
                MainActivity.listDataBarang.get(barang.getPosition()).setId_penjual(barang.getId_penjual());
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
            barang = params[0];
            List<NameValuePair> ls = new ArrayList<>();
            String nama_merek = barang.getNama_merek();
            String id_merek = barang.getId_merek();
            if(id_merek == null){
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("nama_merek",nama_merek));
                JSONObject jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-merek.php","POST",list);
                if(jsonObject == null){
                    return 1;
                }
                List<NameValuePair> get = new ArrayList<>();
                JSONObject objMerek = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-merek.php","GET",get);
                if(objMerek == null){
                    return 1;
                }
                try{
                    int success = objMerek.getInt("success");
                    if (success == 1){
                        JSONArray merekArray = objMerek.getJSONArray("merek");
                        for(int n = (merekArray.length()-1); n < merekArray.length(); n++){
                            JSONObject c = merekArray.getJSONObject(n);
                            String id_merekGet = c.getString("id_merek");
                            String nama_merekGet = c.getString("nama_merek");
                            String logo_merekGet = c.getString("logo_merek");
                            String deskripsi_merekGet = c.getString("deskripsi_merek");
                            DataMerek dataMerek = new DataMerek();
                            dataMerek.setId_merek(id_merekGet);
                            dataMerek.setNama_merek(nama_merekGet);
                            dataMerek.setLogo_merek(logo_merekGet);
                            dataMerek.setDeskripsi_merek(deskripsi_merekGet);
                            MainActivity.listDataMerek.add(dataMerek);
                            MainActivity.listMerek.add(nama_merekGet);
                            ls.add(new BasicNameValuePair("id_merek", id_merekGet));
                        }
                    }else{
                        return 1;
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }else {
                ls.add(new BasicNameValuePair("id_merek", id_merek));
            }
            Time time = new Time(Time.getCurrentTimezone());
            time.setToNow();
            String date = time.format("%Y-%m-%d");
            ls.add(new BasicNameValuePair("id_penjual",barang.getId_penjual()));
            ls.add(new BasicNameValuePair("nama_barang",barang.getNama_barang()));
            ls.add(new BasicNameValuePair("harga_barang",barang.getHarga_barang()));
            ls.add(new BasicNameValuePair("tgl_harga_stok_barang",date));
            ls.add(new BasicNameValuePair("stok_barang",barang.getStok_barang()));
            ls.add(new BasicNameValuePair("satuan_barang",barang.getSatuan_barang()));
            ls.add(new BasicNameValuePair("kategori_barang",barang.getId_kategori_barang()));
            ls.add(new BasicNameValuePair("deskripsi_barang",barang.getDeskripsi_barang()));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-barang.php","POST",ls);
            if(json == null){
                return 1;
            }
            List<NameValuePair> get = new ArrayList<>();
            JSONObject objBarang = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-barang.php","GET",get);
            if(objBarang == null){
                return 1;
            }
            try{
                int success = objBarang.getInt("success");
                if (success == 1){
                    JSONArray barang = objBarang.getJSONArray("all_barang");
                    for(int n = (barang.length()-1); n < barang.length(); n++){
                        JSONObject c = barang.getJSONObject(n);
                        String id_barang = c.getString("id_barang");
                        String id_user = c.getString("id_user");
                        String id_merekGet = c.getString("id_merek");
                        String id_penjual = c.getString("id_penjual");
                        String id_gambar = c.getString("id_gambar");
                        String nama_barang = c.getString("nama_barang");
                        String stok_barang = c.getString("stok_barang");
                        String satuan_barang = c.getString("satuan_barang");
                        String harga_barang = c.getString("harga_barang");
                        String tgl_harga_stok_barang = c.getString("tgl_harga_stok_barang");
                        String kode_barang = c.getString("kode_barang");
                        String lokasi_barang = c.getString("lokasi_barang");
                        String kategori_barang = c.getString("kategori_barang");
                        String deskripsi_barang = c.getString("deskripsi_barang");
                        String id_favorite = c.getString("id_favorite");
                        DataBarang dataBarang = new DataBarang();
                        dataBarang.setId_barang(id_barang);
                        dataBarang.setId_user(id_user);
                        dataBarang.setId_merek(id_merekGet);
                        dataBarang.setId_penjual(id_penjual);
                        dataBarang.setId_gambar(id_gambar);
                        dataBarang.setNama_barang(nama_barang);
                        dataBarang.setStok_barang(stok_barang);
                        dataBarang.setSatuan_barang(satuan_barang);
                        dataBarang.setHarga_barang(harga_barang);
                        dataBarang.setTgl_harga_stok_barang(tgl_harga_stok_barang);
                        dataBarang.setKode_barang(kode_barang);
                        dataBarang.setLokasi_barang(lokasi_barang);
                        dataBarang.setId_kategori_barang(kategori_barang);
                        dataBarang.setDeskripsi_barang(deskripsi_barang);
                        dataBarang.setId_favorite(id_favorite);
                        MainActivity.listDataBarang.add(dataBarang);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer arg){
            if(arg == 0){
                MainActivity.adapterHomeBarang.notifyDataSetChanged();
                MainActivity.adapterMerek.notifyDataSetChanged();
                MainActivity.adapterDataMerek.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class NewSupplier extends AsyncTask<DataSupplier,Void,Integer>{
        DataSupplier dataSupplier;
        @Override
        protected Integer doInBackground(DataSupplier... params) {
            dataSupplier = params[0];
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("nama_toko",dataSupplier.getNama_toko()));
            list.add(new BasicNameValuePair("alamat_toko",dataSupplier.getAlamat_toko()));
            list.add(new BasicNameValuePair("kontak_toko",dataSupplier.getKontak_toko()));
            JSONObject object = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-penjual.php","POST",list);
            if(object == null) return 1;
            list.clear();
            object = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-penjual.php","GET",list);
            try{
                int success = object.getInt("success");
                if (success == 1){
                    JSONArray penjual = object.getJSONArray("penjual");
                    for(int n = (penjual.length()-1); n < penjual.length(); n++){
                        JSONObject c = penjual.getJSONObject(n);
                        String id_penjual = c.getString("id_penjual");
                        String nama_penjual = c.getString("nama_penjual");
                        String nama_toko = c.getString("nama_toko");
                        String alamat_toko = c.getString("alamat_toko");
                        String geolocation = c.getString("geolocation");
                        String kontak_toko = c.getString("kontak_toko");
                        String email_toko = c.getString("email_toko");
                        DataSupplier dataSupplier = new DataSupplier();
                        dataSupplier.setId_penjual(id_penjual);
                        dataSupplier.setNama_penjual(nama_penjual);
                        dataSupplier.setNama_toko(nama_toko);
                        dataSupplier.setAlamat_toko(alamat_toko);
                        dataSupplier.setGeolocation(geolocation);
                        dataSupplier.setKontak_toko(kontak_toko);
                        dataSupplier.setEmail_toko(email_toko);
                        MainActivity.listDataSupplier.add(dataSupplier);
                        MainActivity.listNamaToko.add(nama_toko);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
                return 1;
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer out){
            if(out == 0){
                MainActivity.adapterShoppingSupplier.notifyDataSetChanged();
                MainActivity.adapterMerek.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AddKategoriBarang extends AsyncTask<String,Void,Integer>{

        @Override
        protected Integer doInBackground(String... params) {
            List<NameValuePair> list = new ArrayList<>();
            Log.d("PARAMS[0]","================== "+params[0]);
            list.add(new BasicNameValuePair("nama_kategori",params[0]));
            JSONObject object = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-kategori.php","POST",list);
            if(object == null) return 1;
            list = new ArrayList<>();
            object = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-kategori.php","GET",list);
            if(object == null){
                return 1;
            }
            try{
                int success = object.getInt("success");
                if (success == 1){
                    JSONArray kategori = object.getJSONArray("kategori");
                        JSONObject c = kategori.getJSONObject(kategori.length()-1);
                        String id = c.getString("id");
                        String nama_kategori = c.getString("nama_kategori");
                        DataKategori dataKategori = new DataKategori();
                        dataKategori.setId_Kategori(id);
                        dataKategori.setNama_Kategori(nama_kategori);
                        MainActivity.listDataKategori.add(dataKategori);
                        MainActivity.listNamaKategori.add(nama_kategori);
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer out){
            if(out == 0){
                MainActivity.adapterNamaKategori.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"Error Occurred!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
