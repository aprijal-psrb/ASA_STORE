<?php
include('koneksi.php');
$response = array();
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
	echo json_encode($response);
}else{
	$response['success'] = 0;
	$response['message'] = 'No kategori found';
	echo json_encode($response);
}
?>