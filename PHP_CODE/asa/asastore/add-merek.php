<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_merek'])){
	$nama_merek = $_POST['nama_merek'];
	$result = mysql_query("INSERT INTO merek(nama_merek) VALUES ('$nama_merek')");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Merek added.";
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