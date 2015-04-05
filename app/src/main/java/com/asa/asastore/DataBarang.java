package com.asa.asastore;

public class DataBarang{
    private String 	id_barang,id_user,id_merek,id_penjual,id_gambar,nama_barang,stok_barang,satuan_barang,harga_barang,tgl_harga_stok_barang,kode_barang,lokasi_barang,kategori_barang,deskripsi_barang,id_favorite;

    public DataBarang(){}
    public void setId_barang(String arg){
        this.id_barang = arg;
    }
    public void setId_user(String arg){
        this.id_user = arg;
    }
    public void setId_merek(String arg){
        this.id_merek = arg;
    }
    public void setId_penjual(String arg){
        this.id_penjual = arg;
    }
    public void setId_gambar(String arg){
        this.id_gambar = arg;
    }
    public void setNama_barang(String arg){
        this.nama_barang = arg;
    }
    public void setStok_barang(String arg){
        this.stok_barang = arg;
    }
    public void setSatuan_barang(String arg){
        this.satuan_barang = arg;
    }
    public void setHarga_barang(String arg){
        this.harga_barang = arg;
    }
    public void setTgl_harga_stok_barang(String arg){
        this.tgl_harga_stok_barang = arg;
    }
    public void setKode_barang(String arg){
        this.kode_barang = arg;
    }
    public void setLokasi_barang(String arg){
        this.lokasi_barang = arg;
    }
    public void setKategori_barang(String arg){
        this.kategori_barang = arg;
    }
    public void setDeskripsi_barang(String arg){
        this.deskripsi_barang = arg;
    }
    public void setId_favorite(String arg){
        this.id_favorite = arg;
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