<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>

<html>
<head>
<title>Administracion Owner</title>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-15" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="viewport" content="width=1040" />

<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700"
	rel="stylesheet" type="text/css" />
<link rel='stylesheet' href='/owner-web/css/style.css' />
<link rel="stylesheet" type="text/css" href="/owner-web/css/jquery-ui.css" />
<link rel='stylesheet' href='/owner-web/css/slider_multiple.css' />
<script type="text/javascript" src="/owner-web/js/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="/owner-web/js/jquery.form.js"></script>
<script type="text/javascript" src="/owner-web/js/owner.js"></script>
<script type="text/javascript"
	src="/owner-web/js/wpts_slider_multiple.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		jQuery('#my_carousel_ct').tsSlider({
			thumbs : '',
			width : '100%',
			showText : true,
			autoplay : 15000,
			imgAlignment : 'center',
			imgMarginTop : 0,
			imgMarginLeft : 0,
			squared : true,
			textSquarePosition : 4,
			textPosition : 'top',
			imgAlignment : 'Center',
			textColor : 'F00',
			skin : 'transparent',
			arrows : 'ts-arrow-1',
			sliderHeight : 475,
			effects : '',
			titleBold : 'false',
			titleItalic : 'false',
			textBold : 'true',
			textItalic : 'false',
			textWidth : 90,
			background_sld : '#000',
			background_caption : '#000'
		});

		$(".item").hover(function() {
			$(".opt").css({
				"visibility" : "visible"
			});
		}, function() {
			$(".opt").css({
				"visibility" : "hidden"
			});
		});

		$(".upload_image").click(function() {
			$("#upload").click();
		});
		$('#upload').change(function() {

			var val = $(this).val();

			switch (val.substring(val.lastIndexOf('.') + 1).toLowerCase()) {
			case 'gif':
			case 'jpg':
			case 'png':
			case 'jpeg':
				$.uploadImageForm($("#albumId").val());
				break;
			default:
				$(this).val('');
				break;
			}

		});

	});

	saveDescription = function(id) {

		_success = function(data) {
			if (data == "ok") {
				$("#" + id).parent().html(
						'<span id="' + id + '" class="icon-pencil-edit" onclick="editDescription(\'' + id + '\')">' + $("#" + id).val()
								+ '</span>');
			}
		};

		$.ajax({
			url : "/owner-web/owner/image/description/" + id + "?description="
					+ $("#" + id).val(),
			type : 'POST',
			success : _success
		});
	};
	
	editDescription = function(id) {
		$("#" + id).parent().html(
				'<textarea style="height: 50px; width: 100%;" id="' + id + '" placeholder="Descripcion" draggable="false">' + $("#" + id).text() + '</textarea>' +
				'<a style="cursor: pointer;" onclick="saveDescription(\'' + id + '\')">Guardar Descripcion</a>');
		
	};
</script>
<style type="text/css">
	#cover {
		width: ${album.cover.width}px  !important ;
		margin-left: ${ (1100 - album .cover.width)/2}px !important;
	}
</style>
<c:forEach var="image" items="${album.images}">
	<style type="text/css">
		#i${image.id}{
			width:${image.width}px  !important ;
			margin-left: ${ (1100 - image .width)/2}px !important;
		}
	</style>
</c:forEach>

</head>

<body>
    <div id="overlay" style="display:none">Cargando...</div>
	<form id="upload_form" method="post" action="upload/${album.id}" style="height: 0px" enctype="multipart/form-data">
		<input type="file" name="upload" style="visibility: hidden" id="upload">
	</form>
	<input type="hidden" id="albumId" value="${album.id}">
	<div id='my_carousel_ct' class='carousel-container'>
		<div id="my_carousel_1" class="carousel slide">
			<div id="my_carousel_2" class="carousel-inner rs-slider">
				<div class="item active">
					<div class="ts_border">
						<img id="cover" src="${album.cover.dir}" />
						<div class="opt" style="visibility: hidden">
						<c:if test="${fn:length(album.images) < 3}">
							<a class="upload_image" style="color: #000000; opacity: 1; cursor: pointer; background-color: #FFFFFF">
								Agregar foto 
							</a>
						</c:if>
						</div>
						<div class="carousel-caption">
							<p class="ts_txt">
								<c:if test="${empty album.cover.description}">
									<textarea style="height: 50px; width: 100%;" id="${album.cover.id}" placeholder="Descripcion" draggable="false"></textarea>
									<a style="cursor: pointer;" onclick="saveDescription('${album.cover.id}')">Guardar Descripcion</a>
								</c:if>
								<c:if test="${not empty album.cover.description}">
									<span id="${album.cover.id}" onclick="editDescription('${album.cover.id}')"class="icon-pencil-edit">${album.cover.description}</span>
								</c:if>
							</p>
						</div>
					</div>
				</div>
				<c:forEach var="image" items="${album.images}">
					<div class="item">
						<div class="ts_border">
							<img id="i${image.id}" src="${image.dir}" />
							<div class="opt" style="visibility: hidden">
							<c:if test="${fn:length(album.images) < 3}">
								<a class="upload_image"
									style="color: #000000; opacity: 1; cursor: pointer; background-color: #FFFFFF">
									Agregar foto 
								</a>
							</c:if>
							</div>
							<div class="carousel-caption">
								<p class="ts_txt">
									<c:if test="${empty image.description}">
										<textarea id="${image.id}" style="height: 50px; width: 100%;" placeholder="Descripcion" draggable="false"></textarea>
										<a style="cursor: pointer;" onclick="saveDescription('${image.id}')">Guardar Descripcion</a>
									</c:if>
									<c:if test="${not empty image.description}">
										<span id="${image.id}" onclick="editDescription('${image.id}')" class="icon-pencil-edit">${image.description}</span>
									</c:if>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<a class="carousel-custom" href="#my_carousel_1" data-slide="prev">p</a>
			<a class="carousel-custom" href="#my_carousel_1" data-slide="next">n</a>
		</div>
	</div>

</body>



</html>