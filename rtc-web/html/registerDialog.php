<div style="background: #e1c192 url(images/wood_pattern.jpg); width:100%; height:100%">
	<div class="containerLogin">	
		<section class="main">
			<form class="form-2" style="top:20px">
				<h1><span class="log-in">Registrarse</span>
					<span id="registerError" class="loginError" style="visibility:hidden"></span>
				</h1>
				<p style="margin-bottom: 0px;">
					<label for="login"><i class="icon-user"></i>Nombre de usuario</label>
					<input type="text" id="username" name="username" placeholder="Nombre de usuario">
				</p>
				<p style="margin-bottom: 0px;"> 
					<label for="login"><i class="icon-inbox"></i>email</label>
					<input type="text" id="email" name="email" placeholder="Dirección de correo electrónico">
				</p>
				<p style="margin-bottom: 0px;">
					<label for="r_password"><i class="icon-lock"></i>Contraseña</label>
					<input type="password" id="r_password" name="r_password" placeholder="Contraseña" class="showpassword">
				</p>
				<p style="margin-bottom: 0px;">
					<label for="confirm_password"><i class="icon-lock"></i>Repetir Contraseña</label>
					<input type="password" id="confirm_password" name="confirm_password" placeholder="Repetir Contraseña" class="showpassword">
				</p>
				<p class="loginError" style="visibility:hidden">
					<label><i class="icon-thumbs-down"></i></label>
				<p>
				<p  style="margin-bottom: 0px;" class="clearfix">  
					<a class="icon-ban-circle log-twitter" onclick="$('#register-dialog').dialog('close')" style="background-color:black; color:red">Cancelar</a>  
					<input class="submit" onclick="$.registerPlayer()"  value="Registrarse" >
				</p>
			</form>​​
		</section>
	</div>
</div>