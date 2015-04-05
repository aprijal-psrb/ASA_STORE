package com.asa.asastore;

public class DataBarang{
    private String 	id_barang,id_user,id_merek,id_penjual,id_gambar,nama_barang,stok_barang,satuan_barang,harga_barang,tgl_harga_stok_barang,kode_barang,lokasi_barang,kategori_barang,deskripsi_barang,id_favorite;

    public DataBarang(){}
    public void setId_barang(String id_barang){
        this.id_barang = id_barang;
    }
    public void setId_user(String id_user){
        this.id_user = id_user;
    }
    public void setId_merek(String id_merek){
        this.id_merek = id_merek;
    }
    public void setId_penjual(String id_penjual){
        this.id_penjual = id_penjual;
    }
    public void setId_gambar(String id_gambar){
        this.id_gambar = id_gambar;
    }
    public void setNama_barang(String nama_barang){
        this.nama_barang = nama_barang;
    }
    public void setStok_barang(String stok_barang){
        this.stok_barang = stok_barang;
    }
    public void setSatuan_barang(String satuan_barang){
        this.satuan_barang = satuan_barang;
    }
    public void setHarga_barang(String harga_barang){
        this.harga_barang = harga_barang;
    }
    public void setTgl_harga_stok_barang(String tgl_harga_stok_barang){
        this.tgl_harga_stok_barang = tgl_harga_stok_barang;
    }
    public void setKode_barang(String kode_barang){
        this.kode_barang = kode_barang;
    }
    public void setLokasi_barang(String lokasi_barang){
        this.lokasi_barang = lokasi_barang;
    }
    public void setKategori_barang(String kategori_barang){
        this.kategori_barang = kategori_barang;
    }
    public void setDeskripsi_barang(String deskripsi_barang){
        this.deskripsi_barang = deskripsi_barang;
    }
    public void setId_favorite(String id_favorite){
        this.id_favorite = id_favorite;
    }
    public String getId_barang(){
        return this.id_barang;
    }
    public String getId_merek(){
        return this.id_merek;
    }
    public String getId_penjual(){
        return this.id_penjual;
    }
    public String getId_gambar(){
        return this.id_gambar;
    }
    public String getNama_barang(){
        return this.nama_barang;
    }
    public String getStok_barang(){
        return this.stok_barang;
    }
    public String getSatuan_barang(){
        return this.satuan_barang;
    }
    public String getHarga_barang(){
        return this.harga_barang;
    }
    public String getTgl_harga_stok_barang(){
        return this.tgl_harga_stok_barang;
    }
    public String getKode_barang(){
        return this.kode_barang;
    }
    public String getLokasi_barang(){
        return this.lokasi_barang;
    }
    public String getKategori_barang(){
        return this.kategori_barang;
    }
    public String getDeskripsi_barang(){
        return this.deskripsi_barang;
    }
    public String getId_favorite(){
        return this.id_favorite;
    }
    /*public String getColorFavorite(){

        for(int i = 0; i < Favorite.listCategory.size(); i++){
            if(getId_favorite().equals(MainActivity.allFavorite.get(i).get(0))){
                return Favorite.listCategory.get(i).getCategoryColor();
            }
        }
        return "#00000000";
    }
    */
}