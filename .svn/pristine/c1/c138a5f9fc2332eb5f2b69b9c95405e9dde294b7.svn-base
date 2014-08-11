<?php
	$lat = 0;
	$lng = 0;
	$address = "no data";
	if (isset($_REQUEST['lat'])) {
		$lat = $_REQUEST['lat'];
	}
	if (isset($_REQUEST['lng'])) {
		$lng = $_REQUEST['lng'];
	}
	if (isset($_REQUEST['address'])) {
		$address = $_REQUEST['address'];
	}
?>
<iframe style="width: 100%; height:100%" src="html/stablishmentMap.php?lat=<?php echo $lat ?>&lng=<?php echo $lng ?>&address=<?php echo $address ?>&scrollwheel=true" seamless>
	<p>Your browser does not support iframes.</p>
</iframe>