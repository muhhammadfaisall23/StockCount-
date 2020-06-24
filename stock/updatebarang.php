<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	$kode = isset($_POST['kode']) ? $_POST['kode'] : '';
	$nama = isset($_POST['nama']) ? $_POST['nama'] : '';
	$stock = isset($_POST['stock']) ? $_POST['stock'] : '';
	$harga = isset($_POST['harga']) ? $_POST['harga'] : '';
	$ukuran = isset($_POST['ukuran']) ? $_POST['ukuran'] : '';
	$kategori = isset($_POST['kategori']) ? $_POST['kategori'] : '';
	$gambar = isset($_POST['gambar']) ? $_POST['gambar'] : '';

	class emp{}
	
	if (empty($id) || empty($kode) || empty($nama) || empty($stock)|| empty($harga)|| empty($ukuran)|| empty($kategori)|| 
		empty($gambar)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("UPDATE barang SET kode='".$kode."', nama='".$nama."', stock='".$stock."', harga='".$harga."', ukuran='".$ukuran."', kategori='".$kategori."', gambar='".$gambar."' WHERE id='".$id."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di update";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error update Data";
			die(json_encode($response)); 
		}	
	}
?>