<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="/owner-web/css/gmap3-menu.css" />
<script type="text/javascript" src="/owner-web/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script src="/owner-web/js/gmap3.js"></script>
<script type="text/javascript" src="/owner-web/js/gmap3-menu.js"></script> 
<script type="text/javascript" src="/owner-web/js/owner.js"></script> 
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
      
        var $map = $("#googleMap"), 
          menu = new Gmap3Menu($map),
          current,  // current click event (used to save as start / end position)
          m1,
          lat = $("#lat"),
          lng = $("#lng");  
        
        if (lat && lng) {
        	var clear = {name:"marker"};
            clear.tag = "stablishment";
            $map.gmap3({clear:clear});
          
          $map.gmap3({
            marker:{
              latLng:new google.maps.LatLng(lat.val(), lng.val()),
              options:{
                draggable:true,
                icon:new google.maps.MarkerImage("http://maps.gstatic.com/mapfiles/icon_green.png")
              },
              tag: ("stablishment"),
              events: {
                dragend: function(marker){
                  $.saveStablishmentMap(marker, $("#mmId").val(), $("#entity").val());	
                  updateMarker(marker);
                }
              },
              callback: function(marker){
                updateMarker(marker);
              }
            }
          });
        }
          
        
        // update marker
        function updateMarker(marker){
            m1 = marker;
        }
        
       
        function addMarker(){
          var clear = {name:"marker"};
            clear.tag = "stablishment";
            $map.gmap3({clear:clear});
          
          $map.gmap3({
            marker:{
              latLng:current.latLng,
              options:{
                draggable:true,
                icon:new google.maps.MarkerImage("http://maps.gstatic.com/mapfiles/icon_green.png")
              },
              tag: ("stablishment"),
              events: {
                dragend: function(marker){
                  $.saveStablishmentMap(marker, $("#mmId").val(), $("#entity").val());	
                  updateMarker(marker);
                }
              },
              callback: function(marker){
            	$.saveStablishmentMap(marker, $("#mmId").val(), $("#entity").val());
                updateMarker(marker);
              }
            }
          });
        }
        
        
        // MENU : ITEM 1
        menu.add("Agregar marcador aquí", "itemB", 
          function(){
            menu.close();
            addMarker();
          });
        
        // MENU : ITEM 2
        menu.add("Centrar aquí", "centerHere", 
          function(){
              $map.gmap3("get").setCenter(current.latLng);
              menu.close();
          });
        
        // INITIALIZE GOOGLE MAP
        $map.gmap3({
          map:{
            options:{
              center:[-34.60392547, -58.38202827],
              zoom: 5
            },
            events:{
              rightclick:function(map, event){
                current = event;
                menu.open(current);
              },
              click: function(){
                menu.close();
              },
              dragstart: function(){
                menu.close();
              },
              zoom_changed: function(){
                menu.close();
              }
            }
          },
        });
      });
    </script>  
</head>
<body>
	<input type="hidden" value="${entity}" id="entity">
	<input type="hidden" value="${mmId}" id="mmId">
	<c:if test="${not empty lat and not empty lng}">
		<input type="hidden" value="${lat}" id="lat">
		<input type="hidden" value="${lng}" id="lng">
	</c:if>
	<div id="container">
      <div id="googleMap"></div>
    </div>
</body>
</html>