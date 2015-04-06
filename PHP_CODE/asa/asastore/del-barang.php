<?php
include('koneksi.php');

$response = array();
if(isset($_POST['id_barang'])){
	$id_barang = $_POST['id_barang'];
	$result = mysql_query("DELETE FROM `barang` WHERE `id_barang` = '$id_barang'");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Barang deleted.";
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