<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
  <title>Administracion Owner</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="/owner-web/css/style.css" />
  <link rel="stylesheet" type="text/css" href="/owner-web/css/jquery-ui.css" />
  <script type="text/javascript" src="/owner-web/js/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.form.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.tmpl.js"></script>
  <script type="text/javascript" src="/owner-web/js/owner.js"></script>
  <script type="text/javascript" src="/owner-web/js/owner.jquery.dialog.js"></script>
  <script type="text/javascript" src="/owner-web/js/modernizr-1.5.min.js"></script>
  <script type="text/javascript">
	$(document).ready(function() {
		
		$("#upload_image").click(function() {
			$("#upload_cover").click();
		});
		$('#upload_cover').change(function() { 
			
			var val = $(this).val();
	
		    switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()) {
		        case 'gif': case 'jpg': case 'png': case 'jpeg':
		        	$("#name").val(val);
		        	$.uploadCover();
		            break;
		        default:
		            $(this).val('');
		            break;
		    }	
			 
		});
		
	 	});
  </script>
</head>

<body>
  <div id="imagesDialog" style="display:none"></div>
  <div id="mapEditDialog" style="display:none"></div>
  <div id="container">
    <div id="main">
      <header>
        <div id="logo"><h1>Administracion<a href="#">Owner</a></h1></div>
        <nav>
          <ul class="lavaLampWithImage" id="lava_menu">
            <li class="current"><a href="/owner-web/owner/stablishment">Establecimiento</a></li>
            <li><a href="/owner-web/owner/fields">Canchas</a></li>
            <li><a href="/owner-web/owner/services">Servicios</a></li>
            <li><a href="/owner-web/owner/calendar">Calendario</a></li>
          </ul>
        </nav>
      </header>
      <div id="site_content" style="height:545px">
        <div id="sidebar_container">
          <div class="sidebar">
          	<h3>Fotos</h3>
          	<a id="cover" style="width: 100%; cursor:pointer" class="image image-left">
          		<c:if test="${empty stablishment.album.cover}">
	          		<form id="upload_cover_form" method="post" action="/owner-web/owner/image/upload/${stablishment.album.id}" style="height:0px" enctype="multipart/form-data">
		  				<input type="file" name="upload" style="visibility:hidden" id="upload_cover">
		  				<input type="hidden" value="${stablishment.album.id}" name="entity">
		  				<input type="hidden" name="name" id="name">
					</form>
					<img id="upload_image" src="/owner-web/images/back.jpg">
				</c:if>
				<c:if test="${not empty stablishment.album.cover}">
					<img src="${stablishment.album.cover.dir}"  onclick="$.openDialog('1100', '500', '/owner-web/owner/image/dialog/${stablishment.album.id}')">
				</c:if>
			</a>
          </div>
          
          <div class="sidebar">
         	<h5>${stablishment.district.name} - ${stablishment.district.state.name}</h5>
            <h5>${stablishment.address}</h5>
            <c:if test="${empty stablishment.mapMarker.lat and empty stablishment.mapMarker.lng}">
				<h3 class="edit-field" onclick="$.loadMapEditDialog('${stablishment.mapMarker.id}', '${stablishment.id}')">Ubicacion</h3>
				<a style="width: 100%;" class="image image-left" onclick="$.loadMapEditDialog('${stablishment.mapMarker.id}', '${stablishment.id}')">
					<img style="cursor:pointer" src="/owner-web/images/mapImage.jpg">
				</a>
			</c:if>
			<c:if test="${not empty stablishment.mapMarker.lat and not empty stablishment.mapMarker.lng}">
           		<h3 onclick="$.loadMapEditDialog('${stablishment.mapMarker.id}', '${stablishment.id}')" class="edit-field">Ubicacion</h3>
            	<a  style="width: 100%;" class="image image-left">
					<iframe style="margin-bottom: -8px; width: 100%" src="/owner-web/owner/map/embedSmall?lat=${stablishment.mapMarker.lat}&lng=${stablishment.mapMarker.lng}" seamless>
						<p>Your browser does not support iframes.</p>
					</iframe>
				</a>
			</c:if>
          </div>
        </div>
        <div id="content">
          <h1>${stablishment.name}</h1>
          <h2>Horario disponible</h2>
          <p>de <strong>${stablishment.openDay}</strong> a <strong>${stablishment.closeDay}</strong>
           de <strong>${stablishment.openHour}</strong> hasta <strong>${stablishment.closeHour}</strong></p>
          <h3>Web</h3>  
          <h4>${stablishment.web}</h4>
          <h3>e-mail</h3>  
          <h4>${stablishment.email}</h4>
           <p class="form_settings" style="padding-top: 100px"><span>&nbsp;</span>
           <a class="submit" href="/owner-web/owner/stablishment/edit" style="float:left; text-decoration:none; text-align:center">editar</a></p>
         
      </div>
      </div>
      </div>
      <footer>
        <p><a href="index.html">home</a> | <a href="about.html">about me</a> | <a href="portfolio.html">my portfolio</a> | <a href="blog.html">blog</a> | <a href="contact.php">contact</a></p>
        <p>&copy; 2012 CSS3_colour. All Rights Reserved. | <a href="http://www.css3templates.co.uk">design from css3templates.co.uk</a></p>
      </footer>
    </div>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="/owner-web/js/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.lavalamp.min.js"></script>
  <script type="text/javascript">
    $(function() {
      $("#lava_menu").lavaLamp({
        fx: "backout",
        speed: 700
      });
    });
  </script>
</body>
</html>
