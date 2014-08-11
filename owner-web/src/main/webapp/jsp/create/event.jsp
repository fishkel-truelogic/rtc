<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color:#000; margin-top:-50px; height:100%">
    <div id="main">
      <div id="site_content" style="height:100%"> 
        
        <div id="content">
	        <form action="/owner-web/owner/calendar/save" id="event" onsubmit="return $.validateEvent();" method="post">
	            <div class="form_settings" style="color:#FFF">
	              <p><span>Nombre del jugador</span><input type="text" id="username" name="username" value="" /></p>
	              <p><span>email del jugador</span><input type="text" id="email" name="email" value="" /></p>
	              <p><span>Hora</span>
	              <select id="hour" name="hour">
	              		<c:forEach var="i" begin="0" end="23">
		              		<option value="${i}:00">${i}:00</option>
		              		<option value="${i}:30">${i}:30</option>
						</c:forEach>
	              </select>
	              <p><span>Fecha</span> 
	              <select id="day" name="day">
	              		<c:forEach var="date"  items="${dates}">
		              		<option value="${date}">${date}</option>
						</c:forEach>
	              </select>
	              </p>
	              <p><span>Cancha</span> 
				    <select id="field" name="field">
			              <c:forEach var="field" items="${fields}">
				              <option value="${field.id}">${field.name}</option>
		              	  </c:forEach>
		            </select>
	              </p>
	              <p><span>Paga Seña?</span>
	              	<input type="radio" style="width:10px"  name="prepayment" value="true">Sí
					<input type="radio" style="width:10px" name="prepayment" value="false">No
	              </p> 
	              
	              
	              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="submit" value="crear" /></p>
	              
	            </div>
	        </form>
         
      	</div>
      </div>
      </div>
    </div>
