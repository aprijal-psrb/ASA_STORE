<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_favorite']) && isset($_POST['id_favorite']) && isset($_POST['warna_favorite'])){
	$nama_favorite = $_POST['nama_favorite'];
	$id_favorite = $_POST['id_favorite'];
	$warna_favorite = $_POST['warna_favorite'];
	$result = mysql_query("UPDATE `favorite` SET `warna_favorite`='$warna_favorite',`nama_favorite`='$nama_favorite' WHERE `id_favorite`='$id_favorite' ");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Favorite Diubah.";
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