package com.asa.asastore;

/**
 * Created by APRIJAL_PASARIBU on 05/04/2015.
 */
public class DataMerek {
    private String id_merek, nama_merek, logo_merek, deskripsi_merek;

    public void setId_merek(String id_merek){
        this.id_merek = id_merek;
    }

    public void setNama_merek(String nama_merek){
        this.nama_merek = nama_merek;
    }

    public void setLogo_merek(String logo_merek){
        this.logo_merek = logo_merek;
    }

    public void setDeskripsi_merek(String deskripsi_merek){
        this.deskripsi_merek = deskripsi_merek;
    }

    public String getId_merek(){
        return this.id_merek;
    }

    public String getNama_merek(){
        return this.nama_merek;
    }

    public String getLogo_merek(){
        return this.logo_merek;
    }

    public String getDeskripsi_merek(){
        return this.deskripsi_merek;
    }
}
