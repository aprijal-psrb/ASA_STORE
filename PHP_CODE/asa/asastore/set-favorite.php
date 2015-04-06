<?php
include('koneksi.php');

$response = array();
if(isset($_POST['id_favorite'])){
	$id_favorite = $_POST['id_favorite'];
	$id_barang = $_POST['id_barang'];
	$result = mysql_query("UPDATE `barang` SET `id_favorite` = '$id_favorite' WHERE `id_barang` = '$id_barang' ");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Barang di favoritkan.";
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