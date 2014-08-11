<?php
include 'player.php';
include 'resize-class.php';  
session_start();
foreach ($_FILES["images"]["error"] as $key => $error) {
    if ($error == UPLOAD_ERR_OK) {
		$player = unserialize($_SESSION['player']);
		$name = $player->__get('id') . '.' . $_REQUEST['type'];
		$nameGif = $player->__get('id') . '.gif';
        move_uploaded_file( $_FILES["images"]["tmp_name"][$key], "../images/" . $name);
		$resizeObj = new resize('../images/'.$name);  
		$resizeObj -> resizeImage(160, 160, 'auto');  
		$resizeObj -> saveImage('../images/'.$nameGif, 100);
    }
}

