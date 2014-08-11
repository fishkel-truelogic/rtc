<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
  <title>Administracion owner</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="/owner-web/css/style.css" />
  <link rel="stylesheet" type="text/css" href="/owner-web/css/jquery-ui.css" />
  <link href="/owner-web/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="/owner-web/js/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script type="text/javascript" src="/owner-web/js/jquery.tmpl.js"></script>
  <script type="text/javascript" src="/owner-web/js/owner.js"></script>
  <script type="text/javascript" src="/owner-web/js/validations.js"></script>
  <script type="text/javascript" src="/owner-web/js/modernizr-1.5.min.js"></script>
  <script src="//datatables.net/download/build/nightly/jquery.dataTables.js"></script>
  <script type="text/javascript">
    $( document ).ready(function() {
    	
    	var table = $('#lugares').DataTable();
    	
    	selectState = function(state) {
	    	 
    		$.ajax({
	    		url: '/owner-web/owner/district/' + state,
	    		type : "GET",
	    		success: function(data) {
	    			var districts = JSON.parse(data);
	    			var length = districts.length;
	    			var html = "";
	    			for (var i = 0; i < length; i++) {
	    				html += '<option value="'
	    					 + districts[i].id 
	    					 + '">'
	    					 + districts[i].name
	    					 + '</option>'; 
	    			}
	    			$("#districts").html(html);
	    		}
	    	 });
	    };
    });
  </script>
  <style type="text/css">
body {
	font: 90%/1.45em "Helvetica Neue", HelveticaNeue, Verdana, Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0;
	color: #777777;
	background-color: #fff;
}

div.container {
	min-width: 900px;
	margin: 0 auto;
}


</style>
</head>

<body>
  <div id="imagesDialog" style="display:none"></div>
  <div id="container">
    <div id="main">
      <header>
        <div id="logo"><h1>Administracion<a href="#">Owner</a></h1></div>
        <nav>
          <ul class="lavaLampWithImage" id="lava_menu">
            <li class="current"><a href="/owner-web/owner/stablishment">Establecimiento</a></li>
            <li><a href="/owner-web/owner/help">Ayuda</a></li>
          </ul>
        </nav>
      </header>
      <div id="site_content">
        
        <div id="content">
        
<!--         <table id="lugares" class="display" style="width:100%"> -->
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<th>Lugar</th> -->
<!-- 									<th>Domicilio</th> -->
<!-- 									<th>Telefono</th> -->
<!-- 									<th>comentario</th> -->
<!-- 									<th>Accion</th> -->
<!-- 								</tr> -->
<!-- 							</thead> -->

<!-- 							<tfoot> -->
<!-- 								<tr> -->
<!-- 									<th>Lugar</th> -->
<!-- 									<th>Domicilio</th> -->
<!-- 									<th>Telefono</th> -->
<!-- 									<th>comentario</th> -->
<!-- 									<th>Accion</th> -->
<!-- 								</tr> -->
<!-- 							</tfoot> -->

<!-- 							<tbody> -->
<%-- 								<c:forEach var="lugar" items="${lugares}"> --%>
<!-- 									<tr> -->
<%-- 										<td>${lugar.descripcion}</td> --%>
<%-- 										<td>${lugar.domicilio}</td> --%>
<%-- 										<td>${lugar.telefono}</td> --%>
<%-- 										<td>${lugar.comentario}</td> --%>
<!-- 										<td> -->
<!-- 											<a href="#">Seleccionar</a> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
<!-- 							</tbody> -->
<!-- 						</table> -->
	        <form action="/owner-web/owner/stablishment/" id="stablishment" onsubmit="return $.validateStablishment();" method="post">
	            <div class="form_settings">
	              <p><span>Nombre del establecimiento</span><input type="text" id="name" name="name" value="" /></p>
	              <p><span>Dia de apertura</span>
	              	<select id="openDay" name="openDay">
		              	<option value="Lunes">Lunes</option>
		              	<option value="Martes">Martes</option>
		              	<option value="Miercoles">Miercoles</option>
		              	<option value="Jueves">Jueves</option>
		              	<option value="Viernes">Viernes</option>
		              	<option value="Sabado">Sabado</option>
		              	<option value="Domingo">Domingo</option>
	              	</select>
	              </p>
	              <p><span>Horario de apertura</span>
	              	<select id="openHour" name="openHour">
	              		<c:forEach var="i" begin="0" end="23">
		              		<option value="${i}:00">${i}:00</option>
		              		<option value="${i}:30">${i}:30</option>
						</c:forEach>
	              	</select>
	              </p>
	              <p><span>Dia de cierre</span>
	              	<select id="closeDay" name="closeDay">
		              	<option value="Lunes">Lunes</option>
		              	<option value="Martes">Martes</option>
		              	<option value="Miercoles">Miercoles</option>
		              	<option value="Jueves">Jueves</option>
		              	<option value="Viernes">Viernes</option>
		              	<option value="Sabado">Sabado</option>
		              	<option value="Domingo">Domingo</option>
	              	</select>
	              </p>
	              <p><span>Horario de cierre</span>
	              	<select id="closeHour" name="closeHour">
		              	<c:forEach var="i" begin="0" end="23">
		              		<option value="${i}:00">${i}:00</option>
		              		<option value="${i}:30">${i}:30</option>
						</c:forEach>
	              	</select>
	              </p>
	              <p><span>Sitio web</span><input type="text" id="web" name="web" value="" /></p>
	              <p><span>e-mail</span><input type="text" name="email" id="email" value="" /></p>
	              <p><span>Direccion</span><input type="text" name="address" id="address" value="" /></p>
	              <p><span>Localidad</span>
	              	<select id="states" name="district.state.id">
	              		<c:forEach var="state" items="${states}">
			              	<option onclick="selectState('${state.name}')" value="${state.id}">${state.name}</option>
	              		</c:forEach>
	              	</select>
	              </p>
	              <p><span>Barrio</span>
	              	<select id="districts" name="district.id">
		              	<c:forEach var="district" items="${districts}">
			              	<option value="${district.id}">${district.name}</option>
	              		</c:forEach>
	              	</select>
	              	<c:forEach var="district" items="${districts}">
			              <input type="hidden" id="${district.id}" value="${district.name}">
	              	</c:forEach>
	              </p>
	              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="submit" value="continuar" /></p>
	            </div>
	        </form>
         
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
    
    $( document ).ready(function() {
    	$("#states").select(function() {
	    	 $.ajax({
	    		url: 'district/' + $("#states").val(),
	    		type : "GET",
	    		success: function(data) {
	    			var districts = JSON.parse(data);
	    			var length = districts.length;
	    			var html = "";
	    			for (var i = 0; i < length; i++) {
	    				html += '<option value="'
	    					 + districts[i].id 
	    					 + '">'
	    					 + districts[i].name
	    					 + '</option>'; 
	    			}
	    			$("#districts").html(html);
	    		}
	    	 });
	    	});
   		});
    
	    
  </script>
</body>
</html>