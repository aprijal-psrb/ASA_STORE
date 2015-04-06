<?php
include('koneksi.php');
$response = array();
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
	echo json_encode($response);
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
?>