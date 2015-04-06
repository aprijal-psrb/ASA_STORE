<?php
include('koneksi.php');
$response = array();
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
	echo json_encode($response);
}else{
	$response['success'] = 0;
	$response['message'] = 'No merek found';
	echo json_encode($response);
}
?>