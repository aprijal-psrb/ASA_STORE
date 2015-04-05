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
    View rootView;
    static ArrayList<String> arrayListBarang,arrayListSupplier,arrayListId;
    static ListView listViewShopping,listViewSupplier;
    static ArrayAdapter arrayAdapterPenjual,arrayAdapterBarang;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup,Bundle bundle){
        Log.d("Shopping", "onCreateView");
        rootView = inflater.inflate(R.layout.activity_shopping, viewGroup, false);
        listViewShopping = (ListView)rootView.findViewById(R.id.list_shopping);
        listViewSupplier = (ListView)rootView.findViewById(R.id.list_supplier);
        listViewSupplier.setAdapter(MainActivity.adapterShoppingSupplier);
        listViewSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayListBarang = new ArrayList<>();
                int id_penjual = Integer.valueOf(MainActivity.allPenjual.get(position).get(0));
                for (int i = 0; i < MainActivity.allBarang.size(); i++){
                    if(Integer.valueOf(MainActivity.allBarang.get(i).get(3)) == id_penjual){
                        arrayListBarang.add(MainActivity.allBarang.get(i).get(5));
                    }
                    arrayAdapterBarang = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayListBarang);
                    listViewShopping.setAdapter(arrayAdapterBarang);
                }
            }
        });
        return rootView;
    }


    public class GetData extends AsyncTask<String,Integer,Integer> {
        int success;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(String... params) {
            List<NameValuePair> all = new ArrayList<>();
            JSONObject jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-barang.php","GET",all);
            Log.d("BARANG======","=================="+jsonObject.toString());
            if(jsonObject == null){
                return 0;
            }
            try{
                success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                    MainActivity.allBarang = new ArrayList<>();
                    for(int n = 0; n < all_barang.length(); n++){
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
                }else {
                    return 0;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-penjual.php","GET",all);
            Log.d("PENJUAL======","=================="+jsonObject.toString());
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray penjual = jsonObject.getJSONArray("penjual");
                    MainActivity.allPenjual = new ArrayList<>();
                    for(int n = 0; n < penjual.length(); n++){
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
                        MainActivity.allPenjual.add(arrayList);
                    }
                }else{
                    return 0;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php","GET",all);
            Log.d("FAVORITE======","=================="+jsonObject.toString());
            if(jsonObject == null){
                return 0;
            }

            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray barang = jsonObject.getJSONArray("favorite");
                    MainActivity.allFavorite = new ArrayList<>();

                    for(int n = 0; n < barang.length(); n++){
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
                }else{
                    return 0;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            return success;
        }
        @Override
        protected void onPostExecute(Integer arg){
            arrayListSupplier = new ArrayList<>();
            for (int i = 0; i < MainActivity.allPenjual.size(); i++){
                arrayListSupplier.add(MainActivity.allPenjual.get(i).get(2));
            }
            arrayAdapterPenjual = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayListSupplier);
            listViewSupplier.setAdapter(arrayAdapterPenjual);
        }
    }

}
