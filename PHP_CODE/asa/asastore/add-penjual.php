<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_toko'])){
	$nama_toko = $_POST['nama_toko'];
	$alamat_toko = $_POST['alamat_toko'];
	$kontak_toko = $_POST['kontak_toko'];
	$result = mysql_query("INSERT INTO penjual(nama_toko,alamat_toko,kontak_toko) VALUES ('$nama_toko','$alamat_toko','$kontak_toko')");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Penjual added.";
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