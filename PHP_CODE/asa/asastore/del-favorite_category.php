<?php
include('koneksi.php');

$response = array();
if(isset($_POST['id_favorite'])){
	$id_favorite = $_POST['id_favorite'];
	$result = mysql_query("DELETE FROM `favorite` WHERE `id_favorite` = '$id_favorite'");
	if($result){
		$response["success"] = 1;
		$response["message"] = "Favorite deleted.";
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