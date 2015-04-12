<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_barang'])){
	$id_user = $_POST['id_user'];
	$id_merek = $_POST['id_merek'];
	$id_penjual = $_POST['id_penjual'];
	$id_gambar = $_POST['id_gambar'];
	$nama_barang = $_POST['nama_barang'];
	$stok_barang = $_POST['stok_barang'];
	$satuan_barang = $_POST['satuan_barang'];
	$harga_barang = $_POST['harga_barang'];
	$tgl_harga_stok_barang = $_POST['tgl_harga_stok_barang'];
	$kode_barang = $_POST['kode_barang'];
	$lokasi_barang = $_POST['lokasi_barang'];
	$kategori_barang = $_POST['kategori_barang'];
	$deskripsi_barang = $_POST['deskripsi_barang'];
	$id_favorite = $_POST['id_favorite'];
	$result = mysql_query("INSERT INTO barang(id_merek,nama_barang,harga_barang,stok_barang,satuan_barang,kategori_barang,id_penjual,deskripsi_barang,tgl_harga_stok_barang) VALUES ('$id_merek','$nama_barang','$harga_barang','$stok_barang','$satuan_barang','$kategori_barang','$id_penjual','$deskripsi_barang','$tgl_harga_stok_barang')");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Barang added.";
		echo json_encode($response);
	} else{
		$response["success"] = 0;
		$response["message"] = "Oops! An error occured.";
		echo json_encode($response);
	}
}else{
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
	echo json_encode($response);
}
?>