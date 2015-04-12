<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_kategori'])){
	$nama_kategori = $_POST['nama_kategori'];
	$result = mysql_query("INSERT INTO kategori(nama_kategori) VALUES ('$nama_kategori')");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Kategori added.";
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