/*
 * Copyright (c) 2015 APRIJAL PASARIBU.
 */

package com.asa.asastore;

/**
 * Created by APRIJAL_PASARIBU on 12/04/2015.
 */
public class DataKategori {
    private String mId, mNama_Kategori;

    public DataKategori(){}

    public void setId_Kategori(String id_kategori){
        this.mId = id_kategori;
    }

    public void setNama_Kategori(String nama_kategori){
        this.mNama_Kategori = nama_kategori;
    }

    public String getId_Kategori(){
        return this.mId;
    }

    public String getNama_Kategori(){
        return this.mNama_Kategori;
    }
}
