<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty lat and not empty lng}">
	<iframe src="/owner-web/owner/map/edit/${mapMarker}?lat=${lat}&lng=${lng}&entity=${entity}" style="width:100%; height:483px; overflow:hidden"></iframe>
</c:if>
<c:if test="${empty lat and empty lng}">
	<iframe src="/owner-web/owner/map/edit/${mapMarker}?entity=${entity}" style="width:100%; height:483px; overflow:hidden"></iframe>
</c:if>
