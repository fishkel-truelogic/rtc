<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
html { height: 100% }
body { height: 100%; margin: 0; padding: 0 }

#map-canvas { height: 100% }
</style>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="/owner-web/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/owner-web/js/owner.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">


$(document).ready(function() {
	function initialize() {

		var deflat = -34.60392547, deflng = -58.38202827;
		var	lat = $("#lat").val();
		var	lng = $("#lng").val();
		var mapOptions = {
		center: new google.maps.LatLng(lat, lng),
		zoom: 11,
		disableDefaultUI:true,  
		zoomControl:true,
		scrollwheel:false,
		mapTypeId: google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

		if(lat != deflat && lng != deflng) {
			var marker = new google.maps.Marker({
			position: new google.maps.LatLng(lat, lng),
			map: map
			});
	
			}
		}
	
	google.maps.event.addDomListener(window, 'load', initialize);
});
</script>
</head>
	<input type="hidden" id="lat" value="${lat}">
	<input type="hidden" id="lng" value="${lng}">
	
	<div id="map-canvas">
	</div>

</html>