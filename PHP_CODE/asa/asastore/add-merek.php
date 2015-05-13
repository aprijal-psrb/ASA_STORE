<?php
include('koneksi.php');

$response = array();
if(isset($_POST['nama_merek'])){
	$nama_merek = $_POST['nama_merek'];
	$result = mysql_query("INSERT INTO merek(nama_merek) VALUES ('$nama_merek')");
	if($result){
		$res = mysql_query("SELECT * FROM `merek` WHERE `nama_merek`='$nama_merek'");
		if(mysql_num_rows($res) > 0){
			$response['merek'] = array();
			while($row = mysql_fetch_array($res)){
				$merek = array();
				$merek['id_merek'] = $row['id_merek'];
				$merek['nama_merek'] = $row['nama_merek'];
				$merek['logo_merek'] = $row['logo_merek'];
				$merek['deskripsi_merek'] = $row['deskripsi_merek'];
		
				array_push($response['merek'], $merek);
			}
			$response['success'] = 1;
			echo json_encode($response);
		}else{
			$response['success'] = 0;
			$response['message'] = "No merek found.";
			echo json_encode($response);
		}
	} else{
		$response["success"] = 0;
		$response["message"] = "Merek sudah ada";
		echo json_encode($response);
	}
}else{
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
	echo json_encode($response);
}
?>
