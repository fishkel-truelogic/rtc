<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

<head>
<title>Administration</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/admin-web/css/style.css" />
<link rel="stylesheet" type="text/css" href="/admin-web/css/jquery-ui.css" />
<link href="/admin-web/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/admin-web/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/admin-web/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/admin-web/js/admin.jquery.dialog.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="/admin-web/js/modernizr-1.5.min.js"></script>
<script src="//datatables.net/download/build/nightly/jquery.dataTables.js"></script>
<script type="text/javascript">


$(document).ready( function () {
	var table = $('#grounds').DataTable();
} );

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

#new_ground_btn {
	margin-left: 50px;
    padding-left: 10px;
    padding-right: 11px;
    text-align: center;
    text-decoration: none;
}

</style>

</head>

<body>
	<div id="container">
		<div id="main">
			<header>
				<div id="logo">
					<h1>
						Administracion<a href="#">RTC</a>
					</h1>
				</div>
				<nav>
					<ul class="lavaLampWithImage" id="lava_menu">
						<li><a href="/admin-web/admin/users">Usuarios</a></li>
						<li><a href="/admin-web/admin/districts">Barrios</a></li>
						<li><a href="/admin-web/admin/sports">Deportes</a></li>
						<li class="current"><a href="/admin-web/admin/grounds">Suelos</a></li>
					</ul>
				</nav>
			</header>
			<div id="site_content" style="height: 570px">
				<div id="content">
					<p class="form_settings"><span>&nbsp;</span>
					  <a id="new_ground_btn" class="submit" onclick="$.openDialog('600', '270', '/admin-web/admin/grounds/new')">Crear Suelo</a>
                    </p>
					<div class="container">
						<table id="grounds" class="display" style="width:100%">
							<thead>
								<tr>
									<th>Suelo</th>
									<th>Acción</th>
								</tr>
							</thead>

							<tfoot>
								<tr>
									<th>Suelo</th>
									<th>Acción</th>
								</tr>
							</tfoot>

							<tbody>
								<c:forEach var="ground" items="${grounds}">
									<tr>
										<td>${ground.name}</td>
										<td>
											<a>Modificar</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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
	<script type="text/javascript" src="/admin-web/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript"
		src="/admin-web/js/jquery.lavalamp.min.js"></script>
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
