<!DOCTYPE html>

<?php
	
	include '../php/jqueryAjax.php';
	include '../php/restRequest.php';
	
	$serchParameter = '?';

	$state = '';
	if (isset($_REQUEST['state'])) {
		$state = $_REQUEST['state'];		
		$serchParameter = $serchParameter.'state='.$state;
	} 
	
	$sport = '';
	if (isset($_REQUEST['sport'])) {
		$sport = $_REQUEST['sport'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&sport='.$sport;
		} else {
			$serchParameter = $serchParameter.'sport='.$sport;
		}
	} 
	
	$district = '';
	if (isset($_REQUEST['district'])) {
		$district = $_REQUEST['district'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&district='.$district;
		} else {
			$serchParameter = $serchParameter.'district='.$district;
		}
	}
	
	$ground = '';
	if (isset($_REQUEST['ground'])) {	
		$ground = $_REQUEST['ground'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&ground='.$ground;
		} else {
			$serchParameter = $serchParameter.'ground='.$ground;
		}
	}
	
	$hour = '';
	if (isset($_REQUEST['hour'])) {
		$hour = $_REQUEST['hour'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&hour='.$hour;
		} else {
			$serchParameter = $serchParameter.'hour='.$hour;
		}
	}
	
	$day = '';
	if (isset($_REQUEST['day'])) {
		$day = $_REQUEST['day'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&day='.$day;
		} else {
			$serchParameter = $serchParameter.'day='.$day;
		}
	}
	
	
	$mapMarkers = getRequest($urlServices.'mapmarkers'.replace($serchParameter));
?>




<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="../css/gmap3-menu.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="../js/jquery.tmpl.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script src="../js/gmap3.js"></script>
<script type="text/javascript" src="../js/gmap3-menu.js"></script>
<script type="text/javascript" src="../js/main.js"></script>

 <style>
      #container{
        position:relative;
        height:475px;
      }
      #googleMap{
        width: 100%;
        height: 100%;
      }
    </style>
    
        <script type="text/javascript">
      $(function(){
      
        function survey(selector, callback) {
			var input = $(selector);
			var oldvalue = input.val();
			setInterval(function(){
			if (input.val()!=oldvalue){
				oldvalue = input.val();
				callback();
			}
		}, 100);
	}
		
		var $map = $("#googleMap"), 
          menu = new Gmap3Menu($map),
          current; // current click event (used to save as start / end position)
 
            
       
          <?php if (!empty($mapMarkers)) { ?>		  
		  <?php foreach ($mapMarkers as $mapMarker) {  ?>
		  
		  $map.gmap3({
            marker:{
              latLng: new google.maps.LatLng(<?php echo $mapMarker->{"lat"} . ', ' . $mapMarker->{"lng"} ?>),
              options:{
                draggable:false,
                icon:new google.maps.MarkerImage("http://maps.gstatic.com/mapfiles/icon_green.png")
              },
              tag: ("<?php echo $mapMarker->{"entity"} ?>"),
			  events:{
				click:function(marker, event, context){
                        var map = $(this).gmap3("get"), infowindow = $(this).gmap3({get:{name:"infowindow"}});
						setInfoWindow('<?php echo $mapMarker->{"entity"} ?>', map, infowindow, marker);											
					}
      			}
			}
          }, "autofit");
		  

        <?php } ?>		
		<?php } else { ?>
	
		$map.gmap3({
          map:{
            options:{
              center:[-34.60392547, -58.38202827],
              zoom: 5
            }
          }
        });
	<?php } ?>
       
      });
	  
	  function setInfoWindow (id, map, infowindow, marker) {
		$.ajax({
				url: 'stablishmentInfoWindow.php?id=' + id,
				type: 'html',
				success: function(data) {
					if (infowindow){
          				infowindow.open(map, marker);
          				infowindow.setContent(data);
        			} else {
          				$("#googleMap").gmap3({
            			infowindow:{
       	       				anchor:marker, 
              				options:{content: data}
            				}
          				});
        			}
				}
			});
	  }
    </script>  
</head>
	<div id="container">
      <div id="googleMap"></div>
    </div>
</html>