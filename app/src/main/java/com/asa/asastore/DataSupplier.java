package com.asa.asastore;

/**
 * Created by APRIJAL_PASARIBU on 05/04/2015.
 */
public class DataSupplier {
    private String id_penjual, nama_penjual, nama_toko, alamat_toko, geolocation, kontak_toko, email_toko;

    public void setId_penjual(String id_penjual){
        this.id_penjual = id_penjual;
    }

    public void setNama_penjual(String nama_penjual){
        this.nama_penjual = nama_penjual;
    }

    public void setNama_toko(String nama_toko){
        this.nama_toko = nama_toko;
    }

    public void setAlamat_toko(String alamat_toko){
        this.alamat_toko = alamat_toko;
    }

    public void setGeolocation(String geolocation){
        this.geolocation = geolocation;
    }

    public void setKontak_toko(String kontak_toko){
        this.kontak_toko = kontak_toko;
    }

    public void setEmail_toko(String email_toko){
        this.email_toko = email_toko;
    }

    public String getId_penjual(){
        return this.id_penjual;
    }

    public String getNama_penjual(){
        return this.nama_penjual;
    }

    public String getNama_toko(){
        return this.nama_toko;
    }

    public String getAlamat_toko(){
        return this.alamat_toko;
    }

    public String getGeolocation(){
        return this.geolocation;
    }

    public String getKontak_toko(){
        return this.kontak_toko;
    }

    public String getEmail_toko(){
        return this.email_toko;
    }
}
