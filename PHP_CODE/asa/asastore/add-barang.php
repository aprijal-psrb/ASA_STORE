<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_favorite']) && isset($_POST['warna_favorite'])){
	$warna_favorite = $_POST['warna_favorite'];
	$nama_favorite = $_POST['nama_favorite'];
	$deskripsi = $_POST['deskripsi'];
	$result = mysql_query("INSERT INTO favorite(warna_favorite,nama_favorite,deskripsi) VALUES ('$warna_favorite','$nama_favorite','$deskripsi')");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Favorite added.";
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