<?php

	$urlServices = 'http://127.0.0.1:8080/rtc-service/public/';
	$urlImages = "http://localhost:8081/rtc-web/images/";
	
	function getRequest($url) { 	
		$curl = curl_init($url);
		curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($curl, CURLOPT_TIMEOUT, 0);
		$curl_response = curl_exec($curl);
		if ($curl_response === false) {
			$info = curl_getinfo($curl);
			curl_close($curl);
			die('error occured during curl exec. Additioanl info: ' . var_export($info));
		}
		curl_close($curl);
		$decoded = json_decode($curl_response);
		if (isset($decoded->response->status) && $decoded->response->status == 'ERROR') {
			die('error occured: ' . $decoded->response->errormessage);
		}
		return $decoded;
	}
	
	function postRequest($url, $obj) {
		$json = json_encode($obj);
		$curl = curl_init($url);                                                                      
		curl_setopt($curl, CURLOPT_POST, 1); 
		curl_setopt($curl, CURLOPT_TIMEOUT, 0);		
		curl_setopt($curl, CURLOPT_POSTFIELDS, $json);                                                                  
		curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);                                                                      
		curl_setopt($curl, CURLOPT_HTTPHEADER, array('Content-Type: application/json', 'Content-Length: ' . strlen($json)));
		$curl_response = curl_exec($curl);
		if ($curl_response === false) {
			$info = curl_getinfo($curl);
			curl_close($curl);
			die('error occured during curl exec. Additioanl info: ' . var_export($info));
		}
		curl_close($curl);
		$decoded = json_decode($curl_response);
		if (isset($decoded->response->status) && $decoded->response->status == 'ERROR') {
			die(var_export($decoded));
		}
		
		return $decoded;
	}
	
	function replace($word) {
		$search  = array('á', 'é', 'í', 'ó', 'ú', ' ');
		$replace = array('%C3%A1', '%C3%A9', '%C3%AD', '%C3%B3', '%C3%BA', '%20');
		return str_replace($search, $replace, $word);	
	}
	
	function checkImage($url)
{
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    // don't download content
    curl_setopt($ch, CURLOPT_NOBODY, 1);
    curl_setopt($ch, CURLOPT_FAILONERROR, 1);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    if(curl_exec($ch)!==FALSE)
    {
        return true;
    }
    else
    {
        return false;
    }
}
?>