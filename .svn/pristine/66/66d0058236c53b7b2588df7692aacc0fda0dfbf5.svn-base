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
<script type="text/javascript" src="/owner-web/js/modernizr-1.5.min.js"></script>
<script type="text/javascript" src="/owner-web/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/owner-web/js/validations.js"></script>
<script type="text/javascript" src="/owner-web/js/owner.js"></script>
<script type="text/javascript" src="/owner-web/js/jquery.easing.1.3.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	reloadCalendar = function(entity) {
		$("#content").html('<iframe scrolling="no" src="/owner-web/owner/calendar/' + $('#status').val() +'?entity=' + entity +'" style="width:100%; height:1140px; overflow:hidden" seamless="seamless"></iframe>');
	};
	
	reloadCalendarStatus = function(status) {
		$("#content").html('<iframe scrolling="no" src="/owner-web/owner/calendar/' + status  +'?entity=' + $('#field').val() +'" style="width:100%; height:1140px; overflow:hidden" seamless="seamless"></iframe>');
	};
});
</script>
<style>
#new_event_btn {
	margin-left: 50px;
    padding-left: 10px;
    padding-right: 11px;
    text-align: center;
    text-decoration: none;
}
</style>
</head>
<body>
	<div id="new-event-dialog" style="display:none"></div>
	<div id="container">
		<div id="main" style="width: 90%">
			<header>
				<div id="logo">
					<h1>
						Administracion<a href="#">Owner</a>
					</h1>
				</div>
				<nav>
					<ul class="lavaLampWithImage" id="lava_menu">
						<li><a href="/owner-web/owner/stablishment">establecimiento</a></li>
						<li><a href="/owner-web/owner/fields">canchas</a></li>
						<li><a href="/owner-web/owner/services">Servicios</a></li>
						<li class="current"><a href="/owner-web/owner/calendar">calendario</a></li>
					</ul>
				</nav>
			</header>
			<div id="site_content" style="height: 90%; width: 100%">
				<p class="form_settings"><span>&nbsp;</span>
					Cancha: 
				    <select id="field">
		              	  <option value="0" onclick="reloadCalendar('0');">Todos</option>
			              <c:forEach var="field" items="${fields}">
				              <option value="${field.id}" onclick="reloadCalendar('${field.id}');">${field.name}</option>
		              	  </c:forEach>
		            </select>
		              Estado:
					<select id="status">
		              	  <option value="1" onclick="reloadCalendarStatus('1');">Pendientes de seña, Confirmadas y Calificadas</option>
		            	  <option value="0" onclick="reloadCalendarStatus('0');">Canceladas</option>
		            </select>
		              
	           		<a id="new_event_btn" class="submit" onclick="$.createNewEvent()">Crear Reserva</a>
                </p>
				<div id="content" style="width: 100%">
					<iframe scrolling="no" src="/owner-web/owner/calendar/1?entity=0" style="width:100%; height:1164px; overflow:hidden" seamless="seamless"></iframe>
				</div>
			</div>
			<footer>
				<p>
					<a href="index.html">home</a> | <a href="about.html">about me</a> |
					<a href="portfolio.html">my portfolio</a> | <a href="blog.html">blog</a>
					| <a href="contact.php">contact</a>
				</p>
				<p>
					&copy; 2012 CSS3_colour. All Rights Reserved. | <a
						href="http://www.css3templates.co.uk">design from
						css3templates.co.uk</a>
				</p>
			</footer>
		</div>
	</div>
	<script type="text/javascript" src="/owner-web/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript"
		src="/owner-web/js/jquery.lavalamp.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#lava_menu").lavaLamp({
				fx : "backout",
				speed : 700
			});
		});
	</script>
</body>
</html>
