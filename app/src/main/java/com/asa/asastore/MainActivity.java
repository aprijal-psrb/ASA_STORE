package com.asa.asastore;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.ConnectionPendingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener,ActionBar.TabListener{
    public static ArrayList<ArrayList<String>> allBarang = new ArrayList<>();
    public static ArrayList<ArrayList<String>> allPenjual = new ArrayList<>();
    public static ArrayList<ArrayList<String>> allFavorite = new ArrayList<>();
    public static ArrayList<ArrayList<String>> allMerek = new ArrayList<>();
    public static ArrayList<ArrayList<String>> listHomeBarang = new ArrayList<>();
    public static ArrayList<ArrayList<String>> listShoppingSupplier = new ArrayList<>();
    public static ArrayList<ArrayList<String>> listFavoriteCategory = new ArrayList<>();
    public static AdapterHomeBarang adapterHomeBarang;
    public static AdapterShoppingSupplier adapterShoppingSupplier;
    public static AdapterFavoriteCategory adapterFavoriteCategory;
    public static List<DataBarang> listDataBarang = new ArrayList<>();
    public static List<DataSupplier> listDataSupplier = new ArrayList<>();
    public static List<DataFavorite> listDataFavorite = new ArrayList<>();
    public static List<DataMerek> listDataMerek = new ArrayList<>();
    MyPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    ActionBar actionBar;
    ProgressDialog pDialog;
    public static JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetData().execute();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.home_status).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.belanja_status).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.favorite_status).setTabListener(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.refresh:
                new GetData().execute();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        getActionBar().setSelectedNavigationItem(position);
    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_IDLE
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_DRAGGING
     * @see android.support.v4.view.ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     * @param ft  A {@link android.app.FragmentTransaction} for queuing fragment operations to execute
     *            during a tab switch. The previous tab's unselect and this tab's select will be
     *            executed in a single transaction. This FragmentTransaction does not support
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     * @param ft  A {@link android.app.FragmentTransaction} for queuing fragment operations to execute
     *            during a tab switch. This tab's unselect and the newly selected tab's select
     *            will be executed in a single transaction. This FragmentTransaction does not
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user.
     * Some applications may use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     * @param ft  A {@link android.app.FragmentTransaction} for queuing fragment operations to execute
     *            once this method returns. This FragmentTransaction does not support
     */
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        Fragment mFragment;
        /**
         * Return the Fragment associated with a specified position.
         *
         * @param i
         */
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

        /**
         * Return the number of views available.
         */
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
        new GetData().execute();
    }
    public static int TAG = 0;
    public class GetData extends AsyncTask<String,Integer,Integer>{
        int success;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //if(TAG == 0) {
            //    pDialog = new ProgressDialog(MainActivity.this);
            //    pDialog.setMessage("Please Wait");
            //    pDialog.setIndeterminate(false);
            //    pDialog.setCancelable(false);
            //    pDialog.show();
            //}
        }
        @Override
        protected Integer doInBackground(String... params) {
            //if(TAG == 1){
            //    return 2;
            //}
            List<NameValuePair> all = new ArrayList<>();
                JSONObject jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-barang.php","GET",all);
            if(jsonObject == null){
                return 1;
            }
            try{
                success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray all_barang = jsonObject.getJSONArray("all_barang");
                    //allBarang.clear();
                    //listHomeBarang.clear();
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
                        /*
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
                        allBarang.add(arrayList);
                        */
                        //listHomeBarang.add(arrayList);
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
                        dataBarang.setKategori_barang(kategori_barang);
                        dataBarang.setDeskripsi_barang(deskripsi_barang);
                        dataBarang.setId_favorite(id_favorite);
                        listDataBarang.add(dataBarang);
                    }
                }else {
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-penjual.php","GET",all);
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray penjual = jsonObject.getJSONArray("penjual");
                    //allPenjual.clear();
                    //listShoppingSupplier.clear();
                    listDataSupplier.clear();
                    for(int n = 0; n < penjual.length(); n++){
                        JSONObject c = penjual.getJSONObject(n);
                        String id_penjual = c.getString("id_penjual");
                        String nama_penjual = c.getString("nama_penjual");
                        String nama_toko = c.getString("nama_toko");
                        String alamat_toko = c.getString("alamat_toko");
                        String geolocation = c.getString("geolocation");
                        String kontak_toko = c.getString("kontak_toko");
                        String email_toko = c.getString("email_toko");
                        /*
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_penjual);
                        arrayList.add(nama_penjual);
                        arrayList.add(nama_toko);
                        arrayList.add(alamat_toko);
                        arrayList.add(geolocation);
                        arrayList.add(kontak_toko);
                        arrayList.add(email_toko);
                        allPenjual.add(arrayList);
                        listShoppingSupplier.add(arrayList);
                        */
                        DataSupplier dataSupplier = new DataSupplier();
                        dataSupplier.setId_penjual(id_penjual);
                        dataSupplier.setNama_penjual(nama_penjual);
                        dataSupplier.setNama_toko(nama_toko);
                        dataSupplier.setAlamat_toko(alamat_toko);
                        dataSupplier.setGeolocation(geolocation);
                        dataSupplier.setKontak_toko(kontak_toko);
                        dataSupplier.setEmail_toko(email_toko);
                        listDataSupplier.add(dataSupplier);
                    }
                }else{
                    return 1;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            all = new ArrayList<>();
            jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php","GET",all);
            if(jsonObject == null){
                return 1;
            }

            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray favorite = jsonObject.getJSONArray("favorite");
                    //allFavorite.clear();
                    //listFavoriteCategory.clear();
                    //Favorite.listCategory = new ArrayList<>();
                    listDataFavorite.clear();
                    for(int n = 0; n < favorite.length(); n++){
                        JSONObject c = favorite.getJSONObject(n);
                        String id_favorite = c.getString("id_favorite");
                        String warna_favorite = c.getString("warna_favorite");
                        String nama_favorite = c.getString("nama_favorite");
                        String deskripsi = c.getString("deskripsi");
                        /*
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_favorite);
                        arrayList.add(warna_favorite);
                        arrayList.add(nama_favorite);
                        arrayList.add(deskripsi);
                        allFavorite.add(arrayList);
                        FavoriteCategory temp = new FavoriteCategory();
                        temp.setCategoryName(nama_favorite);
                        temp.setCategoryColor(warna_favorite);
                        //Favorite.listCategory.add(temp);
                        listFavoriteCategory.add(arrayList);
                        color.put(id_favorite,warna_favorite);
                        */
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
            jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-merek.php","GET",all);
            if(jsonObject == null){
                return 1;
            }

            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray merek = jsonObject.getJSONArray("merek");
                    //allMerek.clear();
                    listDataMerek.clear();
                    for(int n = 0; n < merek.length(); n++){
                        JSONObject c = merek.getJSONObject(n);
                        String id_merek = c.getString("id_merek");
                        String nama_merek = c.getString("nama_merek");
                        String logo_merek = c.getString("logo_merek");
                        String deskripsi_merek = c.getString("deskripsi_merek");
                        /*
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(id_merek);
                        arrayList.add(nama_merek);
                        arrayList.add(logo_merek);
                        arrayList.add(deskripsi_merek);
                        allMerek.add(arrayList);
                        */
                        DataMerek dataMerek = new DataMerek();
                        dataMerek.setId_merek(id_merek);
                        dataMerek.setNama_merek(nama_merek);
                        dataMerek.setLogo_merek(logo_merek);
                        dataMerek.setDeskripsi_merek(deskripsi_merek);
                        listDataMerek.add(dataMerek);
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
                AlertDialog.Builder dial = new AlertDialog.Builder(MainActivity.this);
                dial.setMessage("Can't connect to server!").setNeutralButton("Retry",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new GetData().execute();
                    }
                }).setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
            if(arg == 0){
/*
                Home.arrayListBarang = new ArrayList<>();
                for (int i = 0; i < MainActivity.allBarang.size(); i++){
                    Home.arrayListBarang.add(MainActivity.allBarang.get(i).get(5).toString());
                }
                //Home.arrayAdapterBarang = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Home.arrayListBarang);
                adapterBarangHome = new HomeBarangAdapter(MainActivity.this,R.layout.list_item_home,listBarang);
                //Home.listViewBarang.setAdapter(Home.arrayAdapterBarang);
                Home.listViewBarang.setAdapter(adapterBarangHome);
                Shopping.arrayListSupplier = new ArrayList<>();
                for (int i = 0; i < MainActivity.allPenjual.size(); i++){
                    Shopping.arrayListSupplier.add(MainActivity.allPenjual.get(i).get(2).toString());
                }
                Shopping.arrayAdapterPenjual = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Shopping.arrayListSupplier);
                //Shopping.listViewSupplier.setAdapter(Shopping.arrayAdapterPenjual);/*
                Favorite.arrayListCategory = new ArrayList<>();
                for (int i = 0; i < MainActivity.allFavorite.size(); i++){
                    Favorite.arrayListCategory.add(MainActivity.allFavorite.get(i).get(2).toString());
                }
                //Favorite.arrayAdapterCategory = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Favorite.arrayListCategory);
                //Favorite.listViewCategory.setAdapter(Favorite.arrayAdapterCategory);   <<<<<   Error!
                TAG = 1;
                //pDialog.dismiss();
*/
                adapterHomeBarang = new AdapterHomeBarang(MainActivity.this,R.id.layout_item_home,listDataBarang);
                Home.listViewBarang.setAdapter(adapterHomeBarang);

                adapterShoppingSupplier = new AdapterShoppingSupplier(MainActivity.this,android.R.layout.simple_list_item_1,listDataSupplier);
                Shopping.listViewSupplier.setAdapter(adapterShoppingSupplier);

                adapterFavoriteCategory = new AdapterFavoriteCategory(MainActivity.this,R.layout.list_item_favorite_category,listDataFavorite);


            }
            /*
            else if(arg == 2) {
                Home.adapterBarangHome.notifyDataSetChanged();
                Shopping.arrayAdapterPenjual.notifyDataSetChanged();
                Favorite.arrayAdapterCategory.notifyDataSetChanged();
                //pDialog.dismiss();
            }else{
                //pDialog.dismiss();
            }
            */
        }
    }

}

