<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar_container">
	<div class="sidebar">
		<h3>Opciones</h3>
		<ul>
			<li><a href="#">Deshabilitar Estab.</a></li>
			<li><a href="#">Deshabilitar Usuario</a></li>
			<li><a href="/admin-web/admin/users">Volver</a></li>
		</ul>
	</div>
</div>
<div id="content">
	<div id="accordion">
		<h3>Establecimiento</h3>
		<div>
			<p>Mauris mauris ante, blandit et, ultrices a, suscipit eget,
				quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida
				in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam
				mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a
				velit eu ante scelerisque vulputate.</p>
		</div>
		<h3>Canchas</h3>
		<div>
			<p>Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum
				sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris
				turpis porttitor velit, faucibus interdum tellus libero ac justo.
				Vivamus non quam. In suscipit faucibus urna.</p>
		</div>
		<h3>Servicios</h3>
		<div>
			<p>Nam enim risus, molestie et, porta ac, aliquam ac, risus.
				Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in
				pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac
				felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam
				nisi, eu iaculis leo purus venenatis dui.</p>
			<ul>
				<li>List item one</li>
				<li>List item two</li>
				<li>List item three</li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#accordion").accordion({
			heightStyle : "content"
		});
	});
</script>
