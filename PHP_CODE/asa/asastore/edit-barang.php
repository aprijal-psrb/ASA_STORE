<?php
include('koneksi.php');

$response = array();
if(isset($_POST['id_barang']) && isset($_POST['nama_barang']) && isset($_POST['harga_barang']) && isset($_POST['stok_barang']) && isset($_POST['kategori_barang']) && isset($_POST['deskripsi_barang'])){
	$id_barang = $_POST['id_barang'];
	$nama_barang = $_POST['nama_barang'];
	$id_merek = $_POST['id_merek'];
	$harga_barang = $_POST['harga_barang'];
	$stok_barang = $_POST['stok_barang'];
	$kategori_barang = $_POST['kategori_barang'];
	$id_penjual = $_POST['id_penjual'];
	$deskripsi_barang = $_POST['deskripsi_barang'];
	$result = mysql_query("UPDATE `barang` SET `nama_barang`='$nama_barang',`id_merek`='$id_merek',`harga_barang`='$harga_barang',`stok_barang`='$stok_barang',`kategori_barang`='$kategori_barang',`id_penjual`='$id_penjual',`deskripsi_barang`='$deskripsi_barang' WHERE `id_barang`='$id_barang' ");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Barang Diubah.";
		echo json_encode($response);
	} else{
		$response["success"] = 0;
		$response["message"] = "Oops! An error occurred.";
		echo json_encode($response);
	}
}else{
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
	echo json_encode($response);
}
?>