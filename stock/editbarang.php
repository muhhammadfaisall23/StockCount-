<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	
	class emp{}
	
	if (empty($id)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM barang WHERE id='".$id."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->id = $row["id"];
			$response->kode = $row["kode"];
			$response->nama = $row["nama"];
			$response->stock = $row["stock"];
			$response->harga = $row["harga"];
			$response->ukuran = $row["ukuran"];
			$response->kategori = $row["kategori"];
			$response->gambar = $row["gambar"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>