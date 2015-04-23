package com.asa.asastore;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    // Sesuaikan dengan IP localhost.
	public static String URL = "http://192.168.173.1/asa/asastore/";

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

    ProgressDialog pDialog;
    MyPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setCancelable(false);
        savedData = getApplicationContext().getSharedPreferences("savedData", MODE_PRIVATE);

        // Pengaturan Tab dan SwipeTab
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {getActionBar().setSelectedNavigationItem(position);}});
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}
        };
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.home_status).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.belanja_status).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.favorite_status).setTabListener(tabListener));

        // Mengambil data dengan ketentuan :
        if(isNetworkConnected()) {
            getOnlineData(); // Ketika Online (selain mobile network karena belum langsung ke internet)
        }else{
            Toast.makeText(getApplicationContext(),"Tidak Terhubung Dengan Jaringan",Toast.LENGTH_LONG).show();
            getOfflineData(); // Mengambil data private prefences
        }
    }

    public void getOnlineData(){
    	pDialog.setMessage("Get Data, Please wait...");
    	pDialog.show();

        // Permintaan Jaringan
    	JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET, URL+"get-all_data.php", null, new Response.Listener<JSONObject>() {
    		@Override
    		public void onResponse(JSONObject jsonObject){

                // Menyimpan data ke private Prefences
                savedDataEditor = savedData.edit();
                savedDataEditor.putString("all_data", jsonObject.toString());
                savedDataEditor.apply();

                // Mengisi tiap list
    			JSONParser.parse(jsonObject.toString());

                // Meng set List
                adapterHomeBarang = new AdapterBarang(MainActivity.this, R.id.layout_item_home, listDataBarang);
                Home.listViewBarang.setAdapter(adapterHomeBarang);
                adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this, android.R.layout.simple_list_item_1, listDataSupplier);
                Shopping.listViewSupplier.setAdapter(adapterShoppingSupplier);
                adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this, R.layout.list_item_favorite_category, listDataFavorite);
                adapterDataMerek = new AdapterMerek(MainActivity.this,android.R.layout.simple_list_item_1, listDataMerek);
                adapterMerek = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listMerek);
                adapterNamaToko = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listNamaToko);
                adapterNamaKategori = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listNamaKategori);
                pDialog.dismiss();

    		}}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(getApplicationContext(),arg0.toString(),Toast.LENGTH_LONG).show();
				pDialog.dismiss();
			}
		});

        // Permintaan Dilakukan
    	AppController.getInstance().addToRequestQueue(jsonRequest, "get-all_barang");
    }

    public void getOfflineData(){
        String json = savedData.getString("all_data", null);
        if(json == null)return;

        // Mengisi tiap list
        JSONParser.parse(json);

        // Meng set List
        adapterHomeBarang = new AdapterBarang(MainActivity.this, R.id.layout_item_home, listDataBarang);
        adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this, android.R.layout.simple_list_item_1, listDataSupplier);
        adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this, R.layout.list_item_favorite_category, listDataFavorite);
        adapterDataMerek = new AdapterMerek(MainActivity.this,android.R.layout.simple_list_item_1, listDataMerek);
        adapterMerek = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listMerek);
        adapterNamaToko = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listNamaToko);
        adapterNamaKategori = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item, listNamaKategori);
    }

    public boolean isNetworkConnected(){
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        // Apakah jaringan tersedia selain jaringan mobile (karena webserver belum di internet)
        if(conMgr.getActiveNetworkInfo() != null && !mobile.isConnected()){
            return true;
        }else {
            return false;
        }
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
                getOnlineData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
