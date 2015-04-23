<?php
include('koneksi.php');
$response = array();
$result = mysql_query("SELECT * FROM barang") or die(mysql_error());
if(mysql_num_rows($result) > 0){
	$response['all_barang'] = array();
	while($row = mysql_fetch_array($result)){
		$barang = array();
		$barang['id_barang'] = $row['id_barang'];
		$barang['id_user'] = $row['id_user'];
		$barang['id_merek'] = $row['id_merek'];
		$barang['id_penjual'] = $row['id_penjual'];
		$barang['id_gambar'] = $row['id_gambar'];
		$barang['nama_barang'] = $row['nama_barang'];
		$barang['stok_barang'] = $row['stok_barang'];
		$barang['satuan_barang'] = $row['satuan_barang'];
		$barang['harga_barang'] = $row['harga_barang'];
		$barang['tgl_harga_stok_barang'] = $row['tgl_harga_stok_barang'];
		$barang['kode_barang'] = $row['kode_barang'];
		$barang['lokasi_barang'] = $row['lokasi_barang'];
		$barang['kategori_barang'] = $row['kategori_barang'];
		$barang['deskripsi_barang'] = $row['deskripsi_barang'];
		$barang['id_favorite'] = $row['id_favorite'];
		array_push($response['all_barang'],$barang);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
$result = mysql_query("SELECT * FROM favorite") or die(mysql_error());
if(mysql_num_rows($result) > 0){
	$response['favorite'] = array();
	while($row = mysql_fetch_array($result)){
		$favorite = array();
		$favorite['id_favorite'] = $row['id_favorite'];
		$favorite['warna_favorite'] = $row['warna_favorite'];
		$favorite['nama_favorite'] = $row['nama_favorite'];
		$favorite['deskripsi'] = $row['deskripsi'];
		array_push($response['favorite'],$favorite);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
$result = mysql_query("SELECT * FROM kategori") or die(mysql_error());
if(mysql_num_rows($result) > 0){
	$response['kategori'] = array();
	while($row = mysql_fetch_array($result)){
		$kategori = array();
		$kategori['id'] = $row['id'];
		$kategori['nama_kategori'] = $row['nama_kategori'];
		array_push($response['kategori'],$kategori);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = 'No kategori found';
	echo json_encode($response);
}
$result = mysql_query("SELECT * FROM merek") or die(mysql_error());
if(mysql_num_rows($result) > 0){
	$response['merek'] = array();
	while($row = mysql_fetch_array($result)){
		$merek = array();
		$merek['id_merek'] = $row['id_merek'];
		$merek['nama_merek'] = $row['nama_merek'];
		$merek['logo_merek'] = $row['logo_merek'];
		$merek['deskripsi_merek'] = $row['deskripsi_merek'];
		
		array_push($response['merek'],$merek);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = 'No merek found';
	echo json_encode($response);
}
$result = mysql_query("SELECT * FROM penjual") or die(mysql_error());
if(mysql_num_rows($result) > 0){
	$response['penjual'] = array();
	while($row = mysql_fetch_array($result)){
		$penjual = array();
		$penjual['id_penjual'] = $row['id_penjual'];
		$penjual['nama_penjual'] = $row['nama_penjual'];
		$penjual['nama_toko'] = $row['nama_toko'];
		$penjual['alamat_toko'] = $row['alamat_toko'];
		$penjual['geolocation'] = $row['geolocation'];
		$penjual['kontak_toko'] = $row['kontak_toko'];
		$penjual['email_toko'] = $row['email_toko'];
		array_push($response['penjual'],$penjual);
	}
	$response['success'] = 1;
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
	echo json_encode($response);
?>