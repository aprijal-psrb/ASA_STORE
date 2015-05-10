package com.asa.asastore;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by APRIJAL_PASARIBU on 29/03/2015.
 */
public class JSONParser {

    public static void parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int success = jsonObject.getInt("success");
            if (success == 1) {
                JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                MainActivity.listDataBarang.clear();
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
                    DataBarang dataBarang = new DataBarang();
                    dataBarang.setId_barang(id_barang);
                    dataBarang.setId_user(id_user);
                    dataBarang.setId_merek(id_merek);
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

                JSONArray penjual = jsonObject.getJSONArray("penjual");
                MainActivity.listDataSupplier.clear();
                MainActivity.listNamaToko.clear();
                for (int n = 0; n < penjual.length(); n++) {
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

                JSONArray favorite = jsonObject.getJSONArray("favorite");
                MainActivity.listDataFavorite.clear();
                for (int n = 0; n < favorite.length(); n++) {
                    JSONObject c = favorite.getJSONObject(n);
                    String id_favorite = c.getString("id_favorite");
                    String warna_favorite = c.getString("warna_favorite");
                    String nama_favorite = c.getString("nama_favorite");
                    String deskripsi = c.getString("deskripsi");
                    DataFavorite dataFavorite = new DataFavorite();
                    dataFavorite.setId_favorite(id_favorite);
                    dataFavorite.setWarna_favorite(warna_favorite);
                    dataFavorite.setNama_favorite(nama_favorite);
                    dataFavorite.setDeskripsi(deskripsi);
                    MainActivity.listDataFavorite.add(dataFavorite);
                }

                JSONArray merek = jsonObject.getJSONArray("merek");
                MainActivity.listDataMerek.clear();
                MainActivity.listMerek.clear();
                for (int n = 0; n < merek.length(); n++) {
                    JSONObject c = merek.getJSONObject(n);
                    String id_merek = c.getString("id_merek");
                    String nama_merek = c.getString("nama_merek");
                    String logo_merek = c.getString("logo_merek");
                    String deskripsi_merek = c.getString("deskripsi_merek");
                    DataMerek dataMerek = new DataMerek();
                    dataMerek.setId_merek(id_merek);
                    dataMerek.setNama_merek(nama_merek);
                    dataMerek.setLogo_merek(logo_merek);
                    dataMerek.setDeskripsi_merek(deskripsi_merek);
                    MainActivity.listDataMerek.add(dataMerek);
                    MainActivity.listMerek.add(nama_merek);
                }

                JSONArray kategori = jsonObject.getJSONArray("kategori");
                MainActivity.listDataKategori.clear();
                MainActivity.listNamaKategori.clear();
                for (int n = 0; n < kategori.length(); n++) {
                    JSONObject c = kategori.getJSONObject(n);
                    String id = c.getString("id");
                    String nama_kategori = c.getString("nama_kategori");
                    DataKategori dataKategori = new DataKategori();
                    dataKategori.setId_Kategori(id);
                    dataKategori.setNama_Kategori(nama_kategori);
                    MainActivity.listDataKategori.add(dataKategori);
                    MainActivity.listNamaKategori.add(nama_kategori);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONParser(){}

    public JSONObject makeHttpRequest(String url,String method,List<NameValuePair> params){
        try{
            if(method == "POST"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }else if(method == "GET"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        }catch (Exception e){
            Log.e("Buffer Error", "Error converting result " + e.toString());
            return null;
        }
        try{
            jObj = new JSONObject(json);
        }catch (JSONException e){
            Log.e("JSON Parser","Error parsing data "+e.toString());
        }
        return jObj;
    }
}
