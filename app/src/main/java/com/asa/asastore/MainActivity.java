package com.asa.asastore;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,ActionBar.TabListener{
	public static String URL = "http://192.168.137.1/asa/asastore/";
    public static JSONParser jsonParser = new JSONParser();
    public static AdapterBarang adapterHomeBarang;
    public static AdapterShoppingSupplier adapterShoppingSupplier;
    public static AdapterFavoriteCategory adapterFavoriteCategory;
    public static AdapterMerek adapterDataMerek;
    public static ArrayAdapter adapterMerek;
    public static ArrayAdapter adapterNamaToko;
    public static ArrayAdapter adapterNamaKategori;
    public static List<DataBarang> listDataBarang = new ArrayList<>();
    public static List<DataSupplier> listDataSupplier = new ArrayList<>();
    public static List<DataFavorite> listDataFavorite = new ArrayList<>();
    public static List<DataMerek> listDataMerek = new ArrayList<>();
    public static List<DataKategori> listDataKategori = new ArrayList<>();
    public static List<String> listMerek = new ArrayList<>();
    public static List<String> listNamaToko = new ArrayList<>();
    public static List<String> listNamaKategori = new ArrayList<>();
    public static SharedPreferences savedData;
    public static SharedPreferences.Editor savedDataEditor;
    MyPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new GetData().execute(0);
        getData();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.home_status).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.belanja_status).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.favorite_status).setTabListener(this));
        savedData = getApplicationContext().getSharedPreferences("savedData", MODE_PRIVATE);
        savedDataEditor = savedData.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.refresh:
                //new GetData().execute(1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        getActionBar().setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new Home();
                case 1:
                    return new Shopping();
                case 2:
                    return new Favorite();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    public void getData(){
    	JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET, URL+"get-all_data.php", null, new Response.Listener<JSONObject>() {
    		@Override
    		public void onResponse(JSONObject jsonObject){
    			Log.d("jsonObject", jsonObject.toString());
    			try{
                    int success = jsonObject.getInt("success");
                    if (success == 1){
                        JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                        savedDataEditor.putString("all_barang", all_barang.toString());
                        savedDataEditor.apply();
                        listDataBarang.clear();
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
                            listDataBarang.add(dataBarang);
                        }
                        
                        JSONArray penjual = jsonObject.getJSONArray("penjual");
                        savedDataEditor.putString("penjual", penjual.toString());
                        savedDataEditor.apply();
                        listDataSupplier.clear();
                        listNamaToko.clear();
                        for(int n = 0; n < penjual.length(); n++){
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
                            listDataSupplier.add(dataSupplier);
                            listNamaToko.add(nama_toko);
                        }
                        
                        JSONArray favorite = jsonObject.getJSONArray("favorite");
                        savedDataEditor.putString("favorite", favorite.toString());
                        savedDataEditor.apply();
                        listDataFavorite.clear();
                        for(int n = 0; n < favorite.length(); n++){
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
                            listDataFavorite.add(dataFavorite);
                        }
                        
                        JSONArray merek = jsonObject.getJSONArray("merek");
                        savedDataEditor.putString("merek", merek.toString());
                        savedDataEditor.apply();
                        listDataMerek.clear();
                        listMerek.clear();
                        for(int n = 0; n < merek.length(); n++){
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
                            listDataMerek.add(dataMerek);
                            listMerek.add(nama_merek);
                        }
                        
                        JSONArray kategori = jsonObject.getJSONArray("kategori");
                        savedDataEditor.putString("kategori", kategori.toString());
                        savedDataEditor.apply();
                        listDataKategori.clear();
                        listNamaKategori.clear();
                        for(int n = 0; n < kategori.length(); n++){
                            JSONObject c = kategori.getJSONObject(n);
                            String id = c.getString("id");
                            String nama_kategori = c.getString("nama_kategori");
                            DataKategori dataKategori = new DataKategori();
                            dataKategori.setId_Kategori(id);
                            dataKategori.setNama_Kategori(nama_kategori);
                            listDataKategori.add(dataKategori);
                            listNamaKategori.add(nama_kategori);
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                adapterHomeBarang = new AdapterBarang(MainActivity.this, R.id.layout_item_home, listDataBarang);
                Home.listViewBarang.setAdapter(adapterHomeBarang);
                adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this, android.R.layout.simple_list_item_1, listDataSupplier);
                Shopping.listViewSupplier.setAdapter(adapterShoppingSupplier);
                adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this, R.layout.list_item_favorite_category, listDataFavorite);
                adapterDataMerek = new AdapterMerek(MainActivity.this,android.R.layout.simple_list_item_1,listDataMerek);
                adapterMerek = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listMerek);
                adapterNamaToko = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaToko);
                adapterNamaKategori = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaKategori);
    		}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	AppController.getInstance().addToRequestQueue(jsonRequest, "getBarang");
    }
    /*
    public class GetData extends AsyncTask<Integer,Integer,Integer>{
        private int TAG;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Integer... params) {
            TAG = params[0];
            List<NameValuePair> all = new ArrayList<>();
                JSONObject jsonObject = jsonParser.makeHttpRequest(URL+"get-barang.php","GET",all);
            if(jsonObject == null){
                return 1;
            }
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                    savedDataEditor.putString("all_barang", all_barang.toString());
                    savedDataEditor.apply();
                    listDataBarang.clear();
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
                        listDataBarang.add(dataBarang);
                    }
                }else {
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest(URL+"get-penjual.php","GET",all);
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray penjual = jsonObject.getJSONArray("penjual");
                    savedDataEditor.putString("penjual", penjual.toString());
                    savedDataEditor.apply();
                    listDataSupplier.clear();
                    listNamaToko.clear();
                    for(int n = 0; n < penjual.length(); n++){
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
                        listDataSupplier.add(dataSupplier);
                        listNamaToko.add(nama_toko);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest(URL+"get-favorite.php","GET",all);
            if(jsonObject == null){
                return 1;
            }
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray favorite = jsonObject.getJSONArray("favorite");
                    savedDataEditor.putString("favorite", favorite.toString());
                    savedDataEditor.apply();
                    listDataFavorite.clear();
                    for(int n = 0; n < favorite.length(); n++){
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
                        listDataFavorite.add(dataFavorite);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest(URL+"get-merek.php","GET",all);
            if(jsonObject == null){
                return 1;
            }
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray merek = jsonObject.getJSONArray("merek");
                    savedDataEditor.putString("merek", merek.toString());
                    savedDataEditor.apply();
                    listDataMerek.clear();
                    listMerek.clear();
                    for(int n = 0; n < merek.length(); n++){
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
                        listDataMerek.add(dataMerek);
                        listMerek.add(nama_merek);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest(URL+"get-kategori.php","GET",all);
            if(jsonObject == null){
                return 1;
            }
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray kategori = jsonObject.getJSONArray("kategori");
                    savedDataEditor.putString("kategori", kategori.toString());
                    savedDataEditor.apply();
                    listDataKategori.clear();
                    listNamaKategori.clear();
                    for(int n = 0; n < kategori.length(); n++){
                        JSONObject c = kategori.getJSONObject(n);
                        String id = c.getString("id");
                        String nama_kategori = c.getString("nama_kategori");
                        DataKategori dataKategori = new DataKategori();
                        dataKategori.setId_Kategori(id);
                        dataKategori.setNama_Kategori(nama_kategori);
                        listDataKategori.add(dataKategori);
                        listNamaKategori.add(nama_kategori);
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
            if(arg == 1){
            	Toast.makeText(getApplicationContext(), "Get Save Data",Toast.LENGTH_SHORT).show();
                try {
					JSONArray all_barang = new JSONArray(savedData.getString("all_barang", ""));
					JSONArray penjual = new JSONArray(savedData.getString("penjual", ""));
					JSONArray favorite = new JSONArray(savedData.getString("favorite", ""));
					JSONArray merek = new JSONArray(savedData.getString("merek", ""));
					JSONArray kategori = new JSONArray(savedData.getString("kategori", ""));
					listDataBarang.clear();
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
                        listDataBarang.add(dataBarang);
                    }
                    listDataSupplier.clear();
                    listNamaToko.clear();
                    for(int n = 0; n < penjual.length(); n++){
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
                        listDataSupplier.add(dataSupplier);
                        listNamaToko.add(nama_toko);
                    }
                    listDataFavorite.clear();
                    for(int n = 0; n < favorite.length(); n++){
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
                        listDataFavorite.add(dataFavorite);
                    }
                    listDataMerek.clear();
                    listMerek.clear();
                    for(int n = 0; n < merek.length(); n++){
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
                        listDataMerek.add(dataMerek);
                        listMerek.add(nama_merek);
                    }
                    listDataKategori.clear();
                    listNamaKategori.clear();
                    for(int n = 0; n < kategori.length(); n++){
                        JSONObject c = kategori.getJSONObject(n);
                        String id = c.getString("id");
                        String nama_kategori = c.getString("nama_kategori");
                        DataKategori dataKategori = new DataKategori();
                        dataKategori.setId_Kategori(id);
                        dataKategori.setNama_Kategori(nama_kategori);
                        listDataKategori.add(dataKategori);
                        listNamaKategori.add(nama_kategori);
                    }
				} catch (JSONException e) {
					e.printStackTrace();
				}
                if(TAG == 0) {
                	adapterHomeBarang = new AdapterBarang(MainActivity.this, R.id.layout_item_home, listDataBarang);
                    Home.listViewBarang.setAdapter(adapterHomeBarang);
                    adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this, android.R.layout.simple_list_item_1, listDataSupplier);
                    Shopping.listViewSupplier.setAdapter(adapterShoppingSupplier);
                    adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this, R.layout.list_item_favorite_category, listDataFavorite);
                    adapterDataMerek = new AdapterMerek(MainActivity.this,android.R.layout.simple_list_item_1,listDataMerek);
                    adapterMerek = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listMerek);
                    adapterNamaToko = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaToko);
                    adapterNamaKategori = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaKategori);
                }else{
                    adapterHomeBarang.notifyDataSetChanged();
                    adapterShoppingSupplier.notifyDataSetChanged();
                    adapterFavoriteCategory.notifyDataSetChanged();
                }
            }
            if(arg == 0){
                if(TAG == 0) {
                    adapterHomeBarang = new AdapterBarang(MainActivity.this, R.id.layout_item_home, listDataBarang);
                    Home.listViewBarang.setAdapter(adapterHomeBarang);
                    adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this, android.R.layout.simple_list_item_1, listDataSupplier);
                    Shopping.listViewSupplier.setAdapter(adapterShoppingSupplier);
                    adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this, R.layout.list_item_favorite_category, listDataFavorite);
                    adapterDataMerek = new AdapterMerek(MainActivity.this,android.R.layout.simple_list_item_1,listDataMerek);
                    adapterMerek = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listMerek);
                    adapterNamaToko = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaToko);
                    adapterNamaKategori = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,listNamaKategori);
                }else{
                    adapterHomeBarang.notifyDataSetChanged();
                    adapterShoppingSupplier.notifyDataSetChanged();
                    adapterFavoriteCategory.notifyDataSetChanged();
                }
            }
        }
    }
    */
}

