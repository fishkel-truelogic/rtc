<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
deleteService = function(id) { 
	$.ajax({
		url : '/owner-web/owner/services/delete/' + id,
		type : "DELETE",
		success :setTimeout(function () { window.location.replace("/owner-web/owner/services");}, 2000)
	});
}
</script>
<div style="background-color: #000; margin-top: -50px; height: 100%">
	<div id="main">
		<div id="site_content" style="height: 100%">
			<div id="content">
				<div class="form_settings" style="color: #FFF">
					<p>
						<span> Desea eliminar el servicio "${name}" ?</span>
					</p>
					<p style="padding-top: 15px">
						<a style="margin-left:130px" onclick="deleteService('${id}')" class="submit">Eliminar</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>