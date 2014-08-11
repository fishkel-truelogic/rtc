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
  <script type="text/javascript" src="/owner-web/js/validations.js"></script>
  <script type="text/javascript" src="/owner-web/js/owner.js"></script>
  <script type="text/javascript" src="/owner-web/js/owner.jquery.dialog.js"></script>
  
  <script type="text/javascript" src="/owner-web/js/modernizr-1.5.min.js"></script>
  <script type="text/javascript">
  $(document).ready(function() {
	
	 uploadImage = function(albumId) {
			$("#albumId").val(albumId);
			$("#upload").click();
	 };
	 
		$('#upload').change(function() { 
			
			var val = $(this).val();
	
		    switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()) {
		        case 'gif': case 'jpg': case 'png': case 'jpeg':
		        	$.uploadImageAjax($("#albumId").val());
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
  <div id="newTpServiceDialog" style="display:none"></div>
  <div id="confirmationDialog" style="display:none"></div>
  <div id="editDialog" style="display:none"></div>
  <div id="imagesDialog" style="display:none"></div>
  <input type="file" style="visibility:hidden" id="upload">
  <input type="hidden" id="albumId">
  <div id="container">
    <div id="main">
      <header>
        <div id="logo"><h1>Administracion<a href="#">Owner</a></h1></div>
        <nav>
          <ul class="lavaLampWithImage" id="lava_menu">
            <li><a href="/owner-web/owner/stablishment">establecimiento</a></li>
            <li><a href="/owner-web/owner/fields">canchas</a></li>
            <li class="current"><a href="/owner-web/owner/services">Servicios</a></li>
            <li><a href="/owner-web/owner/calendar">calendario</a></li>
          </ul>
        </nav>
      </header>
      <div id="site_content" style="height:420px">
        <div id="content" style="width:100%">
        	<div class="icon-plus" onclick="$.openDialog('1100', '265', '/owner-web/owner/services/new')">Agregar servicio</div>
        	<c:if test="${not empty tpServices}">
        		<div style="height:400px; width:100%">
		        	<div id="accordion">
		        		<c:forEach var="tpService" items="${tpServices}">
					  		<h3>${tpService.name}</h3>
					  		<div>
						  		<div id="sidebar_container" style="width:160px;height:200px">
									<div class="sidebar">
										<a id="${tpService.album.id}" style="width: 100%; cursor:pointer" class="image image-left">
							          		<c:if test="${empty tpService.album.cover}">
												<img onclick="uploadImage('${tpService.album.id}')" src="/owner-web/images/back.jpg">
											</c:if>
											<c:if test="${not empty tpService.album.cover}">
												<img src="${tpService.album.cover.dir}"  onclick="$.openDialog('1100', '500', '/owner-web/owner/image/dialog/${tpService.album.id}')">
											</c:if>
										</a>
									</div>
								</div>
					    		<div style="margin-top:10px;position:relative">
					    		<a class="icon-minus" onclick="$.openDialog('500', '265', '/owner-web/owner/services/delete?id=${tpService.id}&name=${tpService.name}')">Eliminar servicio</a>
					    		<a class="icon-pencil" onclick="$.openDialog('1100', '265', '/owner-web/owner/services/edit?id=${tpService.id}')">Editar servicio</a>
					    		</div>
						  	</div>
						</c:forEach>
					</div>
        		</div>
			</c:if>
      </div>
    </div>
  </div>
      <footer>
        <p><a href="index.html">home</a> | <a href="about.html">about me</a> | <a href="portfolio.html">my portfolio</a> | <a href="blog.html">blog</a> | <a href="contact.php">contact</a></p>
        <p>&copy; 2012 CSS3_colour. All Rights Reserved. | <a href="http://www.css3templates.co.uk">design from css3templates.co.uk</a></p>
      </footer>
  </div>
  <script type="text/javascript" src="/owner-web/js/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.lavalamp.min.js"></script>
  <script type="text/javascript">
    $(function() {
      $("#lava_menu").lavaLamp({
        fx: "backout",
        speed: 700
      });
    $( "#accordion" ).accordion({
    	heightStyle: "fill"
    });
    });
  </script>
</body>
</html>