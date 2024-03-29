<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div style="background-color:#000; margin-top:-50px; height:100%">
    <div id="main">
      <div id="site_content" style="height:100%"> 
        
        <div id="content">
	        <form action="/owner-web/owner/fields/" id="field" onsubmit="return $.validateField();" method="post">
	            <div class="form_settings" style="color:#FFF">
	              <input type="hidden" name="id" value="${field.id}">
	              <input type="hidden" name="userId" value="${field.userId}">
	              <input type="hidden" name="albumId" value="${field.albumId}">
	              <input type="hidden" name="rateId" value="${field.rateId}">
	              <p><span>Nombre de la cancha</span><input type="text" id="name" name="name" value="${field.name}" /></p>
			      <c:if test="${field.ceiling}">
		              <p><span>Techo</span><input class="checkbox" type="checkbox" name="ceiling" value="true" checked/></p>
				  </c:if>			      
			      <c:if test="${not field.ceiling}">
		              <p><span>Techo</span><input class="checkbox" type="checkbox" name="ceiling" value="true" /></p>
				  </c:if>			      
			      <c:if test="${field.lights}">
		              <p><span>Luces</span><input class="checkbox" type="checkbox" name="lights" value="true" checked /></p>
				  </c:if>			      
			      <c:if test="${not field.lights}">
		              <p><span>Luces</span><input class="checkbox" type="checkbox" name="lights" value="true" /></p>
				  </c:if>			      
	              
	              <p><span>Suelo</span><select name="ground">
	              	  <option value="${field.ground}">${field.ground}</option>
		              <c:forEach var="ground" items="${grounds}">
			              <option value="${ground.name}">${ground.name}</option>
	              	  </c:forEach>
	              </select></p>
		      	 <c:set var="trueVal" value="true" />
	              <h4>Deportes</h4>
			      <c:forEach var="sport" items="${sports}" varStatus="status">
			      	 <c:set var="contains" value="false" />
			      	  <c:forEach var="sportIT" items="${field.sports}">
  					  	<c:if test="${sportIT eq sport.id}">
    					<c:set var="contains" value="true" />
  						</c:if>
					  </c:forEach>
					  <c:if test="${contains eq trueVal}">
						 <p><span>${sport.name}</span><input class="checkbox" type="checkbox" name="sports[${status.index}]" value="${sport.id}" checked="checked"/></p>					  	
					  </c:if>
			      	  <c:if test="${not contains eq trueVal}">
						 <p><span>${sport.name}</span><input class="checkbox" type="checkbox" name="sports[${status.index}]" value="${sport.id}" /></p>					  	
					  </c:if>
				  </c:forEach>
	              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="submit" value="Modificar" /></p>
	            </div>
	        </form>
         
      	</div>
      </div>
      </div>
    </div>
