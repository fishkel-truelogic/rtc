<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
validateUser = function() {
	
};
</script>
<div style="background-color: #000; margin-top: -50px; height: 100%">
	<div id="main">
		<div id="site_content"
			style="margin-left: 22px; margin-right: 22px; margin-top: 22px; position: absolute; width: 515px;">
			<div id="content">
				<form action="/admin-web/admin/users" id="user"
					onsubmit="return validateUser();" method="post"
					style="width: 75%;">
					<div class="form_settings" style="color: #FFF">
						<p>
							<span style="width: 140px;">Nombre de usuario</span><input type="text"
								id="username" name="username" value="" />
						</p>
						<p>
							<span style="width: 140px;">Roles</span> 
								<c:forEach var="role" items="${roles}" varStatus="status">
									<input type="checkbox" name="role${status.index}" value="${role.id}" >${role.name}
								</c:forEach>
							<input type="hidden" id="password" name="password" value="admin">
						</p>
						<p>
							<span style="width: 140px;">Habilitado</span>
							<input type="checkbox" name="enabled" value="true" >
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="submit" value="crear" style="height: 40px;" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
