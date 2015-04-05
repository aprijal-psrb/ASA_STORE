package com.asa.asastore;

import android.accounts.NetworkErrorException;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaActionSound;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.asa.ambilwarna.AmbilWarnaDialog;

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
public class Favorite extends Fragment {
    //static View rootView;
    static ListView listViewCategory,listViewFavorite;
    //static ArrayList<String> arrayListCategory,arrayListFavorite;
    //static ArrayList<FavoriteCategory> listCategory;
    //static ArrayAdapter arrayAdapterCategory,arrayAdapterFavorite;
    JSONParser jsonParser = new JSONParser();
    String mColor;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup,Bundle bundle){
        Log.d("Favorite", "onCreateView");
        View rootView = inflater.inflate(R.layout.activity_favorite, viewGroup, false);
        listViewCategory = (ListView)rootView.findViewById(R.id.list_category);
        listViewFavorite = (ListView)rootView.findViewById(R.id.list_favorite);
        //listViewCategory.setAdapter(arrayAdapterCategory);
        //categoryAdapter = new FavoriteCategoryAdapter(getActivity(),R.layout.list_item_favorite_category,listCategory);
        listViewCategory.setAdapter(MainActivity.adapterFavoriteCategory);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<ArrayList<String>> arrayListFavorite = new ArrayList<>();
                String id_favorite = MainActivity.listFavoriteCategory.get(position).get(0);
                for (int i = 0; i < MainActivity.listHomeBarang.size(); i++) {
                    if (MainActivity.listHomeBarang.get(i).get(14).equals(id_favorite)) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add("");
                        list.add(MainActivity.listHomeBarang.get(i).get(5));
                        list.add("");
                        list.add("");
                        list.add(MainActivity.listHomeBarang.get(i).get(8));
                        list.add(MainActivity.listHomeBarang.get(i).get(9));
                        list.add("");
                        list.add("");
                        list.add(MainActivity.listHomeBarang.get(i).get(12));
                        list.add("");
                        list.add(MainActivity.listHomeBarang.get(i).get(14));
                        arrayListFavorite.add(list);

                    }
                    AdapterHomeBarang adapter = new AdapterHomeBarang(getActivity(),R.layout.list_item_home,arrayListFavorite);
                    listViewFavorite.setAdapter(adapter);
                }
            }
        });

        listViewCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(),view);
                popupMenu.inflate(R.menu.menu_favorite);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View view = inflater.inflate(R.layout.dialog, null);
                                final EditText editText = (EditText)view.findViewById(R.id.editTextCategory);
                                final ImageView imgColor = (ImageView)view.findViewById(R.id.categoryColor);
                                mColor = MainActivity.adapterFavoriteCategory.getItem(position).get(1);
                                Log.d("COLOR","DARI ================= "+mColor);
                                imgColor.setBackgroundColor(Color.parseColor(mColor));
                                imgColor.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AmbilWarnaDialog dialog = new AmbilWarnaDialog(getActivity(),Color.parseColor(mColor),new AmbilWarnaDialog.OnAmbilWarnaListener() {
                                            @Override
                                            public void onCancel(AmbilWarnaDialog dialog) {

                                            }

                                            @Override
                                            public void onOk(AmbilWarnaDialog dialog, int color) {
                                                imgColor.setBackgroundColor(color);
                                                mColor = String.format("#%08x",color);
                                            }
                                        });dialog.show();
                                    }
                                });
                                editText.setText(MainActivity.listFavoriteCategory.get(position).get(2));
                                builder.setView(view)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                String id_favorite = MainActivity.adapterFavoriteCategory.getItem(position).get(0);
                                                String nama_favorite = editText.getText().toString();
                                                new SetCategory().execute(id_favorite, mColor, nama_favorite, "" + position);

                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        }).setTitle("Edit Category").show();
                                return true;
                            case R.id.delete:
                                AlertDialog.Builder dia = new AlertDialog.Builder(getActivity());
                                dia.setMessage("Are you sure!!!").setPositiveButton("YES",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        new DeleteData().execute(position);
                                        MainActivity.listFavoriteCategory.remove(position);
                                        MainActivity.adapterFavoriteCategory.notifyDataSetChanged();
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
        Button btnTambah = (Button)rootView.findViewById(R.id.btnTambahCategory);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View view = inflater.inflate(R.layout.dialog, null);
                mColor = "#ff000000";
                final EditText editText = (EditText)view.findViewById(R.id.editTextCategory);
                final ImageView imgColor = (ImageView)view.findViewById(R.id.categoryColor);
                imgColor.setBackgroundColor(Color.parseColor(mColor));
                imgColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AmbilWarnaDialog dialog = new AmbilWarnaDialog(getActivity(),Color.parseColor(mColor),new AmbilWarnaDialog.OnAmbilWarnaListener() {
                            @Override
                            public void onCancel(AmbilWarnaDialog dialog) {

                            }

                            @Override
                            public void onOk(AmbilWarnaDialog dialog, int color) {
                                imgColor.setBackgroundColor(color);
                                mColor = String.format("#%08x",color);
                            }
                        });dialog.show();
                    }
                });
                builder.setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {


                                new AddCategory().execute(mColor,editText.getText().toString());
                                //new GetData().execute();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }).setTitle("Add Category").show();
            }
        });
        return rootView;
    }

    public class AddCategory extends AsyncTask<String,Void,Integer>{
        @Override
        protected void onPreExecute(){

        }
        @Override
        protected Integer doInBackground(String... arg) {
            String color = arg[0];
            String nama_favorite = arg[1];
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("warna_favorite",color));
            params.add(new BasicNameValuePair("nama_favorite",nama_favorite));
            try{
            JSONParser jsonParser = new JSONParser();
            JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-favorite_category.php","POST",params);
            }catch (Exception e){
                e.printStackTrace();
            }

            List<NameValuePair> all = new ArrayList<>();
            JSONObject jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php","GET",all);
            if(jsonObject == null){
                return 0;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            try{
                int success = jsonObject.getInt("success");
                if (success == 1){
                    JSONArray barang = jsonObject.getJSONArray("favorite");
                    for(int n = (barang.length()-1); n < barang.length(); n++){
                        JSONObject c = barang.getJSONObject(n);
                        String id_favorite = c.getString("id_favorite");
                        String warna_favorite = c.getString("warna_favorite");
                        String namafavorite = c.getString("nama_favorite");
                        String deskripsi = c.getString("deskripsi");
                        arrayList = new ArrayList<>();
                        arrayList.add(id_favorite);
                        arrayList.add(warna_favorite);
                        arrayList.add(namafavorite);
                        arrayList.add(deskripsi);
                    }
                }else{
                    return 0;
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            MainActivity.listFavoriteCategory.add(arrayList);
            return null;
        }
        @Override
        protected void onPostExecute(Integer arg){
            MainActivity.adapterFavoriteCategory.notifyDataSetChanged();

        }
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
            JSONObject jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-barang.php","GET",all);
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
            jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-penjual.php","GET",all);
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
            jsonObject = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php","GET",all);
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
            //arrayListCategory = new ArrayList<>();
            for (int i = 0; i < MainActivity.allFavorite.size(); i++){
                //arrayListCategory.add(MainActivity.allFavorite.get(i).get(2));
            }
            //arrayAdapterCategory = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayListCategory);
            //listViewCategory.setAdapter(arrayAdapterCategory);
        }
    }

    private class DeleteData extends AsyncTask<Integer,Void,Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            String id_favorite = MainActivity.allFavorite.get(params[0]).get(0).toString();
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite",id_favorite));
            try{
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/del-favorite_category.php","POST",ls);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    private class SetCategory extends AsyncTask<String,Void,Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            int position = Integer.valueOf(params[3]);
            MainActivity.listFavoriteCategory.get(position).set(0,params[0]);
            MainActivity.listFavoriteCategory.get(position).set(1,params[1]);
            MainActivity.listFavoriteCategory.get(position).set(2,params[2]);

            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite",params[0]));
            ls.add(new BasicNameValuePair("warna_favorite",params[1]));
            ls.add(new BasicNameValuePair("nama_favorite",params[2]));
            try{
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/update-favorite.php","POST",ls);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Integer arg){
            //MainActivity mainActivity = new MainActivity();
            //mainActivity.getData();
            MainActivity.adapterFavoriteCategory.notifyDataSetChanged();
        }
    }
}
