package com.asa.asastore;

/**
 * Created by APRIJAL_PASARIBU on 05/04/2015.
 */
public class DataFavorite {
    private String id_favorite, warna_favorite, nama_favorite, deskripsi;

    public void setId_favorite(String id_favorite){
        this.id_favorite = id_favorite;
    }

    public void setWarna_favorite(String warna_favorite){
        this.warna_favorite = warna_favorite;
    }

    public void setNama_favorite(String nama_favorite){
        this.nama_favorite = nama_favorite;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getId_favorite(){
        return this.id_favorite;
    }

    public String getWarna_favorite(){
        return this.warna_favorite;
    }

    public String getNama_favorite(){
        return this.nama_favorite;
    }

    public String getDeskripsi(){
        return this.deskripsi;
    }
}
