package com.asa.asastore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

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
    ListView listViewCategory,listViewFavorite;
    String mColor;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup,Bundle bundle){
        View rootView = inflater.inflate(R.layout.activity_favorite, viewGroup, false);
        listViewCategory = (ListView)rootView.findViewById(R.id.list_category);
        listViewFavorite = (ListView)rootView.findViewById(R.id.list_favorite);
        listViewCategory.setAdapter(MainActivity.adapterFavoriteCategory);
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<DataBarang> arrayListFavorite = new ArrayList<>();
                String id_favorite = MainActivity.listDataFavorite.get(position).getId_favorite();
                for (int i = 0; i < MainActivity.listDataBarang.size(); i++) {
                    if (MainActivity.listDataBarang.get(i).getId_favorite().equals(id_favorite)) {
                        arrayListFavorite.add(MainActivity.listDataBarang.get(i));
                    }
                    AdapterBarang adapter = new AdapterBarang(getActivity(),R.layout.list_item_home,arrayListFavorite);
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
                                mColor = MainActivity.listDataFavorite.get(position).getWarna_favorite();
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
                                editText.setText(MainActivity.listDataFavorite.get(position).getNama_favorite());
                                builder.setView(view)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                String id_favorite = MainActivity.listDataFavorite.get(position).getId_favorite();
                                                String nama_favorite = editText.getText().toString();
                                                new EditCategory().execute(id_favorite, mColor, nama_favorite, "" + position);

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

    private class AddCategory extends AsyncTask<String,Void,Integer>{
        @Override
        protected Integer doInBackground(String... arg) {
            String color = arg[0];
            String nama_favorite = arg[1];
            List<NameValuePair> post = new ArrayList<>();
            post.add(new BasicNameValuePair("warna_favorite",color));
            post.add(new BasicNameValuePair("nama_favorite",nama_favorite));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/add-favorite_category.php","POST",post);
            if(json == null){
                return 1;
            }
            List<NameValuePair> get = new ArrayList<>();
            JSONObject jsonObject = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/get-favorite.php","GET",get);
            if(jsonObject == null){
                return 1;
            }
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
                        DataFavorite data = new DataFavorite();
                        data.setId_favorite(id_favorite);
                        data.setWarna_favorite(warna_favorite);
                        data.setNama_favorite(namafavorite);
                        data.setDeskripsi(deskripsi);
                        MainActivity.listDataFavorite.add(data);
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
            if(arg == 1) {
                Toast.makeText(getActivity(),"Error Occurred!",Toast.LENGTH_SHORT).show();
            }else{
                MainActivity.adapterFavoriteCategory.notifyDataSetChanged();
            }
        }
    }

    private class DeleteData extends AsyncTask<Integer,Void,Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            String id_favorite = MainActivity.listDataFavorite.get(params[0]).getId_favorite();
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite",id_favorite));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/del-favorite_category.php","POST",ls);
                if(json == null){
                    return null;
                }
            return params[0];
        }
        @Override
        protected void onPostExecute(Integer arg){
            if(arg == null) {
                Toast.makeText(getActivity(),"Error Occurred!",Toast.LENGTH_SHORT).show();
            }else{
                MainActivity.listDataFavorite.remove((int) arg);
                MainActivity.adapterFavoriteCategory.notifyDataSetChanged();
            }
        }
    }

    private class EditCategory extends AsyncTask<String,Void,String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite",params[0]));
            ls.add(new BasicNameValuePair("warna_favorite",params[1]));
            ls.add(new BasicNameValuePair("nama_favorite",params[2]));
            JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/edit-favorite.php","POST",ls);
            if(json == null){
                return null;
            }
            return new String[]{params[0],params[1],params[2],params[3]};
        }
        @Override
        protected void onPostExecute(String[] arg){
            if(arg == null) {
                Toast.makeText(getActivity(),"Error Occurred!",Toast.LENGTH_SHORT).show();
            }else{
                int position = Integer.valueOf(arg[3]);
                MainActivity.listDataFavorite.get(position).setId_favorite(arg[0]);
                MainActivity.listDataFavorite.get(position).setWarna_favorite(arg[1]);
                MainActivity.listDataFavorite.get(position).setNama_favorite(arg[2]);
                MainActivity.adapterFavoriteCategory.notifyDataSetChanged();
            }
        }
    }
}
