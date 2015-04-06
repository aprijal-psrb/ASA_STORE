<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_barang']) && isset($_POST['stok_barang']) && isset($_POST['satuan_barang']) && isset($_POST['harga_barang']) && isset($_POST['kategori_barang']) && isset($_POST['deskripsi_barang'])){
	$barang['id_user'] = $_POST['id_user'];
	$barang['id_merek'] = $_POST['id_merek'];
	$barang['id_penjual'] = $_POST['id_penjual'];
	$barang['id_gambar'] = $_POST['id_gambar'];
	$barang['nama_barang'] = $_POST['nama_barang'];
	$barang['stok_barang'] = $_POST['stok_barang'];
	$barang['satuan_barang'] = $_POST['satuan_barang'];
	$barang['harga_barang'] = $_POST['harga_barang'];
	$barang['tgl_harga_stok_barang'] = $_POST['tgl_harga_stok_barang'];
	$barang['kode_barang'] = $_POST['kode_barang'];
	$barang['lokasi_barang'] = $_POST['lokasi_barang'];
	$barang['kategori_barang'] = $_POST['kategori_barang'];
	$barang['deskripsi_barang'] = $_POST['deskripsi_barang'];
	$barang['id_favorite'] = $_POST['id_favorite'];
	array_push($response['all_barang'],$barang);
	$result = mysql_query("INSERT INTO barang(id_merek,nama_barang,harga_barang,stok_barang,satuan_barang,kategori_barang,id_penjual,deskripsi_barang) VALUES ('$id_merek','$nama_barang','$harga_barang','$stok_barang','$satuan_barang','$kategori_barang','$id_penjual','$deskripsi_barang')");
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