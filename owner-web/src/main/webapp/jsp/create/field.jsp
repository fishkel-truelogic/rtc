<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div style="background-color:#000; margin-top:-50px; height:100%">
    <div id="main">
      <div id="site_content" style="height:100%"> 
        
        <div id="content">
	        <form action="/owner-web/owner/fields/" id="field" onsubmit="return $.validateField();" method="post">
	            <div class="form_settings" style="color:#FFF">
	              <p><span>Nombre de la cancha</span><input type="text" id="name" name="name" value="" /></p>
	              <p><span>Techo</span><input class="checkbox" type="checkbox" name="ceiling" value="true" /></p>
	              <p><span>Luces</span><input class="checkbox" type="checkbox" name="lights" value="true" /></p>
	              <p><span>Suelo</span><select name="ground">
		              <c:forEach var="ground" items="${grounds}">
			              <option value="${ground.name}">${ground.name}</option>
	              	  </c:forEach>
	              </select></p>
	              <h4>Deportes</h4>
			      <c:forEach var="sport" items="${sports}" varStatus="status">
			      	  <p><span>${sport.name}</span><input class="checkbox" type="checkbox" name="sports[${status.index}]" value="${sport.id}" /></p>
				  </c:forEach>
	              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="submit" value="crear" /></p>
	            </div>
	        </form>
         
      	</div>
      </div>
      </div>
    </div>
