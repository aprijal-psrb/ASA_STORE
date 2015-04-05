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
                        TextView tvNameEdit = (TextView) mView.findViewById(R.id.TextView_product_name);
                        TextView tvBrandEdit = (TextView) mView.findViewById(R.id.TextView_product_brand);
                        TextView tvPriceEdit = (TextView) mView.findViewById(R.id.TextView_product_price);
                        TextView tvNumUnitEdit = (TextView) mView.findViewById(R.id.TextView_product_num_unit);
                        TextView tvCategoryEdit = (TextView) mView.findViewById(R.id.TextView_product_category);
                        TextView tvSupplierEdit = (TextView) mView.findViewById(R.id.TextView_product_supplier);
                        TextView tvDescEdit = (TextView) mView.findViewById(R.id.TextView_product_description);
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

        return rootView;
    }

    private class SetFavorite extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... arg) {
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_favorite", arg[0]));
            ls.add(new BasicNameValuePair("id_barang", arg[1]));
            try {
                JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/set-favorite.php", "POST", ls);
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
            String id_barang = MainActivity.listDataBarang.get(params[0]).getId_barang();
            List<NameValuePair> ls = new ArrayList<>();
            ls.add(new BasicNameValuePair("id_barang", id_barang));
            try {
                JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/del-barang.php", "POST", ls);
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
                JSONObject json = MainActivity.jsonParser.makeHttpRequest("http://192.168.173.1/asa/asastore/clear-favorite.php", "POST", ls);
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
