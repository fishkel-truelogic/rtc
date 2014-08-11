<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <div style="background-color:#000; margin-top:-50px; height:100%">
    <div id="main">
      <div id="site_content" style="height:100%"> 
        
        <div id="content">
	        <form action="/owner-web/owner/services/" id="tpService" onsubmit="return $.validateTpService();" method="post">
	            <div class="form_settings" style="color:#FFF">
	              <input type="hidden" name="id" value="${tpService.id}">
	              <input type="hidden" name="userId" value="${tpService.userId}">
	              <input type="hidden" name="album.id" value="${tpService.album.id}">
	              <p><span>Nombre del servicio</span><input type="text" id="name" name="name" value="${tpService.name}" /></p>
	              <p style="padding-top: 15px"><span>&nbsp;</span><input type="submit" class="submit" value="modificar"></p>
	            </div>
	        </form>
         
      	</div>
      </div>
      </div>
    </div>

