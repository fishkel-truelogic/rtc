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

  <div id="newFieldDialog" style="display:none"></div>
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
            <li class="current"><a href="/owner-web/owner/fields">canchas</a></li>
            <li><a href="/owner-web/owner/services">Servicios</a></li>
            <li><a href="/owner-web/owner/calendar">calendario</a></li>
          </ul>
        </nav>
      </header>
      <div id="site_content" style="height:545px">
        <div id="content" style="width:100%">
        	<div class="icon-plus" onclick="$.loadFieldUploadDialog()">Agregar cancha</div>
        	<c:if test="${not empty fields}">
	        	<div id="accordion">
	        		<c:forEach var="field" items="${fields}">
					  		<h3>${field.name}</h3>
					  		<div>
						  		<div id="sidebar_container" style="width:160px;height:200px">
									<div class="sidebar">
										<a id="${field.album.id}" style="width: 100%; cursor:pointer" class="image image-left">
							          		<c:if test="${empty field.album.cover}">
												<img onclick="uploadImage('${field.album.id}')" src="/owner-web/images/back.jpg">
											</c:if>
											<c:if test="${not empty field.album.cover}">
												<img src="${field.album.cover.dir}"  onclick="$.loadAlbumImagesDialog('${field.album.id}')">
											</c:if>
										</a>
									</div>
								</div>
					    		<p style="width:550px;">${field.ground}</p>
					    		<c:if test="${field.ceiling}">
						    		<p style="width:550px;">Techado: Si</p>
								</c:if>
								<c:if test="${not field.ceiling}">
						    		<p style="width:550px;">Techado: No</p>
								</c:if>
					    		<c:if test="${field.lights}">
						    		<p style="width:550px;">Luces: Si</p>
								</c:if>
								<c:if test="${not field.lights}">
						    		<p style="width:550px;">Luces: No</p>
								</c:if>
								<c:forEach var="sport" items="${field.sports}">
									<p style="width:550px;">${sport.name}</p>
								</c:forEach>
					    		<div style="margin-top:10px;position:relative">
					    		<a class="icon-minus" onclick="$.loadConfirmationFieldDeleteDialog('${field.id}','${field.name}')">Eliminar cancha</a>
					    		<a class="icon-pencil" onclick="$.loadEditFieldDialog('${field.id}')">Editar cancha</a>
					    		</div>
						  	</div>
						</c:forEach>
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
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="/owner-web/js/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.lavalamp.min.js"></script>
  <script type="text/javascript">
    $(function() {
      $("#lava_menu").lavaLamp({
        fx: "backout",
        speed: 700
      });
    $( "#accordion" ).accordion({
        heightStyle: "content"
    });
    });
  </script>
</body>
</html>
