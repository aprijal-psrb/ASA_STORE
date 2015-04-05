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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

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
    //View rootView = null;
    static ListView listViewBarang, listViewRecent;
    //static ArrayList<String> arrayListBarang, arrayListRecent;
    //static ArrayAdapter arrayAdapterBarang, arrayAdapterRecent;
    //static HomeBarangAdapter adapterBarangHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("Home", "onCreateView");
        View rootView = inflater.inflate(R.layout.activity_home, viewGroup, false);
        listViewBarang = (ListView) rootView.findViewById(R.id.list_home);
        listViewRecent = (ListView) rootView.findViewById(R.id.list_recently);
        //MainActivity.adapterHomeBarang = new AdapterHomeBarang(getActivity(),R.layout.list_item_home,MainActivity.listHomeBarang);
        //listViewBarang.setAdapter(MainActivity.adapterHomeBarang);
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
                TextView tvName = (TextView) mView.findViewById(R.id.TextView_product_name);
                TextView tvBrand = (TextView) mView.findViewById(R.id.TextView_product_brand);
                TextView tvPrice = (TextView) mView.findViewById(R.id.TextView_product_price);
                TextView tvNumUnit = (TextView) mView.findViewById(R.id.TextView_product_num_unit);
                TextView tvCategory = (TextView) mView.findViewById(R.id.TextView_product_category);
                TextView tvSupplier = (TextView) mView.findViewById(R.id.TextView_product_supplier);
                TextView tvDesc = (TextView) mView.findViewById(R.id.TextView_product_description);
                tvName.setText(MainActivity.allBarang.get(position).get(5));
                for (int i = 0; i < MainActivity.allMerek.size(); i++) {
                    if (MainActivity.allMerek.get(i).get(0).equals(MainActivity.listHomeBarang.get(position).get(2))) {
                        tvBrand.setText(MainActivity.allMerek.get(i).get(1));
                    }
                }
                tvPrice.setText("Rp " + MainActivity.listHomeBarang.get(position).get(8));
                tvNumUnit.setText(MainActivity.listHomeBarang.get(position).get(6) + " " + MainActivity.listHomeBarang.get(position).get(7));
                tvCategory.setText(MainActivity.listHomeBarang.get(position).get(12));
                for (int i = 0; i < MainActivity.listShoppingSupplier.size(); i++) {
                    if (MainActivity.listShoppingSupplier.get(i).get(0).equals(MainActivity.listHomeBarang.get(position).get(3))) {
                        tvSupplier.setText(MainActivity.listShoppingSupplier.get(i).get(2));
                    }
                }
                tvDesc.setText(MainActivity.listHomeBarang.get(position).get(13));
                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder edit = new AlertDialog.Builder(getActivity());
                        View mView = inflater.inflate(R.layout.edit_product_detail, null);
                        TextView tvName = (TextView) mView.findViewById(R.id.TextView_product_name);
                        TextView tvBrand = (TextView) mView.findViewById(R.id.TextView_product_brand);
                        TextView tvPrice = (TextView) mView.findViewById(R.id.TextView_product_price);
                        TextView tvNumUnit = (TextView) mView.findViewById(R.id.TextView_product_num_unit);
                        TextView tvCategory = (TextView) mView.findViewById(R.id.TextView_product_category);
                        TextView tvSupplier = (TextView) mView.findViewById(R.id.TextView_product_supplier);
                        TextView tvDesc = (TextView) mView.findViewById(R.id.TextView_product_description);
                        tvName.setText(MainActivity.listHomeBarang.get(position).get(5));
                        for (int i = 0; i < MainActivity.allMerek.size(); i++) {
                            if (MainActivity.allMerek.get(i).get(0).equals(MainActivity.listHomeBarang.get(position).get(2))) {
                                tvBrand.setText(MainActivity.allMerek.get(i).get(1));
                            }
                        }
                        tvPrice.setText(MainActivity.listHomeBarang.get(position).get(8));
                        tvNumUnit.setText(MainActivity.listHomeBarang.get(position).get(6));
                        tvCategory.setText(MainActivity.listHomeBarang.get(position).get(12));
                        for (int i = 0; i < MainActivity.listShoppingSupplier.size(); i++) {
                            if (MainActivity.listShoppingSupplier.get(i).get(0).equals(MainActivity.listHomeBarang.get(position).get(3))) {
                                tvSupplier.setText(MainActivity.listShoppingSupplier.get(i).get(2));
                            }
                        }
                        tvDesc.setText(MainActivity.listHomeBarang.get(position).get(13));
                        edit.setView(mView);
                        edit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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
                                        String id_favorite = MainActivity.listFavoriteCategory.get(which).get(0);
                                        String id_barang = MainActivity.listHomeBarang.get(position).get(0);
                                        new SetFavorite().execute(id_favorite, id_barang);
                                        MainActivity.listHomeBarang.get(position).set(14,id_favorite);
                                    }
                                });
                                builder.show();
                                return true;
                            case R.id.clearFavorite:
                                String id_barang = MainActivity.listHomeBarang.get(position).get(0);
                                MainActivity.listHomeBarang.get(position).set(14,"");
                                new ClearFavorite().execute(id_barang);
                                return true;
                            case R.id.deleteHome:
                                AlertDialog.Builder dia = new AlertDialog.Builder(getActivity());
                                dia.setMessage("Are you sure!!!").setPositiveButton("YES",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new DeleteData().execute(position);
                                        MainActivity.listHomeBarang.remove(position);
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

        return rootView;
    }
/*
    public class GetData extends AsyncTask<String, Integer, Integer> {
        int success;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {
            List<NameValuePair> all = new ArrayList<>();
            JSONObject jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-barang.php", "GET", all);
            Log.d("BARANG======", "==================" + jsonObject.toString());
            if (jsonObject == null) {
                return 0;
            }
            try {
                success = jsonObject.getInt("success");
                if (success == 1) {
                    JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                    MainActivity.allBarang = new ArrayList<>();
                    for (int n = 0; n < all_barang.length(); n++) {
                        JSONObject c = all_barang.getJSONObject(n);
                        String id_barang = c.getString("id_barang");
                        String id_user = c.getString("id_user");
                        String id_merek = c.getString("id_merek");
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
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_barang);
                        arrayList.add(id_user);
                        arrayList.add(id_merek);
                        arrayList.add(id_penjual);
                        arrayList.add(id_gambar);
                        arrayList.add(nama_barang);
                        arrayList.add(stok_barang);
                        arrayList.add(satuan_barang);
                        arrayList.add(harga_barang);
                        arrayList.add(tgl_harga_stok_barang);
                        arrayList.add(kode_barang);
                        arrayList.add(lokasi_barang);
                        arrayList.add(kategori_barang);
                        arrayList.add(deskripsi_barang);
                        arrayList.add(id_favorite);
                        MainActivity.allBarang.add(arrayList);
                    }
                } else {
                    return 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-penjual.php", "GET", all);
            Log.d("PENJUAL======", "==================" + jsonObject.toString());
            try {
                int success = jsonObject.getInt("success");
                if (success == 1) {
                    JSONArray penjual = jsonObject.getJSONArray("penjual");
                    MainActivity.listShoppingSupplier = new ArrayList<>();
                    for (int n = 0; n < penjual.length(); n++) {
                        JSONObject c = penjual.getJSONObject(n);
                        String id_penjual = c.getString("id_penjual");
                        String nama_penjual = c.getString("nama_penjual");
                        String nama_toko = c.getString("nama_toko");
                        String alamat_toko = c.getString("alamat_toko");
                        String geolocation = c.getString("geolocation");
                        String kontak_toko = c.getString("kontak_toko");
                        String email_toko = c.getString("email_toko");
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_penjual);
                        arrayList.add(nama_penjual);
                        arrayList.add(nama_toko);
                        arrayList.add(alamat_toko);
                        arrayList.add(geolocation);
                        arrayList.add(kontak_toko);
                        arrayList.add(email_toko);
                        MainActivity.listShoppingSupplier.add(arrayList);
                    }
                } else {
                    return 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php", "GET", all);
            Log.d("FAVORITE======", "==================" + jsonObject.toString());
            if (jsonObject == null) {
                return 0;
            }

            try {
                int success = jsonObject.getInt("success");
                if (success == 1) {
                    JSONArray barang = jsonObject.getJSONArray("favorite");
                    MainActivity.allFavorite = new ArrayList<>();

                    for (int n = 0; n < barang.length(); n++) {
                        JSONObject c = barang.getJSONObject(n);
                        String id_favorite = c.getString("id_favorite");
                        String warna_favorite = c.getString("warna_favorite");
                        String nama_favorite = c.getString("nama_favorite");
                        String deskripsi = c.getString("deskripsi");
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_favorite);
                        arrayList.add(warna_favorite);
                        arrayList.add(nama_favorite);
                        arrayList.add(deskripsi);
                        MainActivity.allFavorite.add(arrayList);
                    }
                } else {
                    return 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-merek.php", "GET", all);
            if (jsonObject == null) {
                return 0;
            }

            try {
                int success = jsonObject.getInt("success");
                if (success == 1) {
                    JSONArray barang = jsonObject.getJSONArray("merek");
                    MainActivity.allMerek.clear();

                    for (int n = 0; n < barang.length(); n++) {
                        JSONObject c = barang.getJSONObject(n);
                        String id_merek = c.getString("id_merek");
                        String nama_merek = c.getString("nama_merek");
                        String logo_merek = c.getString("logo_merek");
                        String deskripsi_merek = c.getString("deskripsi_merek");
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_merek);
                        arrayList.add(nama_merek);
                        arrayList.add(logo_merek);
                        arrayList.add(deskripsi_merek);
                        MainActivity.allMerek.add(arrayList);
                    }
                } else {
                    return 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return success;
        }

        @Override
        protected void onPostExecute(Integer arg) {
            arrayListBarang = new ArrayList<>();
            for (int i = 0; i < MainActivity.allBarang.size(); i++) {
                arrayListBarang.add(MainActivity.allBarang.get(i).get(5).toString());
            }
            arrayAdapterBarang = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayListBarang);
            listViewBarang.setAdapter(arrayAdapterBarang);
        }
    }
*/
    private class SetFavorite extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... arg) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite", arg[0]));
            ls.add(new BasicNameValuePair("id_barang", arg[1]));
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/set-favorite.php", "POST", ls);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    @Override
    public void onPostExecute(Integer arg){
        MainActivity.adapterHomeBarang.notifyDataSetChanged();
    }
}

    private class DeleteData extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            String id_barang = MainActivity.allBarang.get(params[0]).get(0);
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang", id_barang));
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/del-barang.php", "POST", ls);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        public void onPostExecute(Integer arg){
            MainActivity.adapterHomeBarang.notifyDataSetChanged();
        }
    }

    private class ClearFavorite extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... params) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang",params[0]));
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/clear-favorite.php", "POST", ls);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onPostExecute(Void arg){
            MainActivity.adapterHomeBarang.notifyDataSetChanged();
        }
    }
}
