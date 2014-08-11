<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
html { height: 100% }
body { height: 100%; margin: 0; padding: 0 }

#map-canvas { height: 100% }
</style>
<?php
	$lat = 0;
	$lng = 0;
	$address = "no data";
	$scrollwheel = "false";
	if (isset($_REQUEST['lat'])) {
		$lat = $_REQUEST['lat'];
	}
	if (isset($_REQUEST['lng'])) {
		$lng = $_REQUEST['lng'];
	}
	if (isset($_REQUEST['address'])) {
		$address = $_REQUEST['address'];
	}
	if (isset($_REQUEST['scrollwheel'])) {
		$scrollwheel = $_REQUEST['scrollwheel'];
	}
?>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
function initialize() {

var mapOptions = {
center: new google.maps.LatLng(<?php echo $lat.', '.$lng ?>),
zoom: 14,
disableDefaultUI:true,  
zoomControl:true,
scrollwheel:<?php echo $scrollwheel ?>,
mapTypeId: google.maps.MapTypeId.ROADMAP
};

var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

var marker = new google.maps.Marker({
position: new google.maps.LatLng(<?php echo $lat.', '.$lng ?>),
map: map
});

var infowindow = new google.maps.InfoWindow({
  content:"<?php echo $address ?>"
  });

google.maps.event.addListener(marker, 'click', function() {
  infowindow.open(map,marker);
  });
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<?php if ($lat != 0 && $lng != 0) { ?>
<div id="map-canvas">
</div>
<?php } ?>
</html>