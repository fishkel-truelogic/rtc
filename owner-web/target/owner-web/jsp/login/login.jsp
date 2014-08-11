<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ES">

<html>
<head>
<title>Administración Owner</title>
<link rel="StyleSheet" href="/owner-web/css/style-login.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>

</head>

<body>
	<img src="/owner-web/images/back.jpg" style="position:absolute; top:0px; width:100%; height:100%">
		<div class="containerLogin" style="width: 600px; height: 500px; align: center;">
			<form id="login_form" name="login_form" method="post" class="form-2"
				action="j_security_check" enctype="application/x-www-form-urlencoded">
	
				<h1>
					<span class="log-in">ingresar</span>
				</h1>
				<p>
					<label for="username"><i class="icon-user"></i>Nombre de usuario</label>
					<input id="username" type="text" name="j_username" size="20" />
				</p>
				<p>
					<label for="password"><i class="icon-lock"></i>Contraseña</label>
					<input id="password" type="password" name="j_password" value="" size="20" />
				</p>
				<p class="clearfix"> 
					<input id="submit" type="submit" name="submit" value="Login" class="submit" />
				</p>
			</form>
		</div>
</body>
</html>