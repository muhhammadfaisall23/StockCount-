<?php

	include "koneksi.php";
	
	$kode = isset($_POST['kode']) ? $_POST['kode'] : '';
	$nama = isset($_POST['nama']) ? $_POST['nama'] : '';
	$stock = isset($_POST['stock']) ? $_POST['stock'] : '';
	$harga = isset($_POST['harga']) ? $_POST['harga'] : '';
	$ukuran = isset($_POST['ukuran']) ? $_POST['ukuran'] : '';
	$kategori = isset($_POST['kategori']) ? $_POST['kategori'] : '';
	$gambar = isset($_POST['gambar']) ? $_POST['gambar'] : '';


	
	class emp{}
	
	if (empty($kode) || empty($nama) || empty($stock)|| empty($harga)|| empty($ukuran)|| empty($kategori)|| 
		empty($gambar)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO barang (id,nip,pass,nama_karyawan,divisi,unit,lokasi,jabatan,hp,email) VALUES(0,'".$kode."','".$nama."','".$stock."','".$harga."','".$ukuran."','".$kategori."','".$gambar."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>