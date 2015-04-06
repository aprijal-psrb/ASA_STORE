<?php
/*
$server = 'localhost';
$user = 'root';
$pw = '';
$db = 'pasaribu_store';

$link = @mysqli_connect($server,$user,$pw,$db)
*/
$HOSTNAME = "localhost";
	$USERNAME = "root";
	$PASSWORD = ""; //Kosongkan jika tidak ada password mysql di laptop mu. Kosong -> ""
	$DB_NAME = "pasaribu_store";
	
    $koneksi = mysql_pconnect($HOSTNAME,$USERNAME,$PASSWORD) or trigger_error(mysql_error(), E_USER_ERROR);
	$database = mysql_select_db($DB_NAME, $koneksi) or die(mysql_error() . "<br>Gagal memilih database !!");


?>