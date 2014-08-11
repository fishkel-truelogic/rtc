<?php

	function loadMainFilterSentence($state, $district, $sport, $ground, $hour, $day) {
		return '$.loadMainFilter({state:\'' . $state . '\', district:\'' . $district . '\', sport: \'' . $sport  . '\', ground: \'' . $ground  . '\', hour: \'' . $hour . '\', day: \'' . $day . '\'});';
	}
	function loadSerchSentence($state, $district, $sport, $ground, $hour, $day) {
		return '$.loadSerch({state:\'' . $state . '\', district:\'' . $district . '\', sport: \'' . $sport  . '\', ground: \'' . $ground  . '\', hour: \'' . $hour . '\', day: \'' . $day . '\'});';
	}
	function loadHeaderSentence($state, $sport) {
		return '$.loadHeader({state:\'' . $state . '\', sport:\'' . $sport . '\'});';
	}
	function loadFeaturedFieldsSentence($state, $sport) {
		return '$.loadFeaturedFields({state:\'' . $state . '\', sport:\'' . $sport . '\'});';
	}
	function loadSerchResultsSentence($serchParameters, $page, $pageCant) {
		return '$.loadSerchResults('.$serchParameters.', \''.$page.'\', \''.$pageCant.'\');';
	}
	
	function buildSerchUrl($state, $district, $sport, $ground, $hour, $day) {
		$url = 'serch.php';
		$param = '?';
		if($state != '') {
			$param += 'state='.$state;
		}
		if ($param != '?') {
			$param += '&';
		}
		if($district != '') {
			$param += 'district='.$district;
		}
		if ($param != '?') {
			$param += '&';
		}
		if($sport != '') {
			$param += 'sport='.$sport;
		}
		if ($param != '?') {
			$param += '&';
		}
		if($ground != '') {
			$param += 'ground='.$ground;
		}
		if ($param != '?') {
			$param += '&';
		}
		if($hour != '') {
			$param += 'hour='.$hour;
		}
		if ($param != '?') {
			$param += '&';
		}
		if($day != '') {
			$param += 'day='.$day;
		}
		if ($param != '?') {
			$url += $param;
		}
		return $url;
		
		
	}
?>