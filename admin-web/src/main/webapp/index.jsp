<% 
	String path = "/users";
	if (request.getAttribute("path") != null) path = (String) request.getAttribute("path"); 
 %>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/admin-web/admin<%= path %>">
		<style type="text/css">
			body { 
			  font: normal .85em arial, sans-serif;
			  background: #000 url(../images/back.jpg) no-repeat top;
			  color: #EEE;
			}
		</style>
	</head>
	<body>
	</body>
</html>