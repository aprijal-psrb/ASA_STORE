<?php
include('koneksi.php');
$response = array();
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
	echo json_encode($response);
}else{
	$response['success'] = 0;
	$response['message'] = 'No product found';
	echo json_encode($response);
}
?>