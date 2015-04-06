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
	echo json_encode($response);
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
?>