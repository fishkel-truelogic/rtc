<!DOCTYPE HTML>
<?php
	
	include '../php/restRequest.php';

	if (isset($_REQUEST['idAlbum'])) {
		$idAlbum = $_REQUEST['idAlbum'];
	} 
	
	$album = getRequest($urlServices . 'albums/' . $idAlbum);
	
?>
<html>
	<head>
		<title>Strongly Typed by HTML5 UP</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="viewport" content="width=1040" />
	
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700" rel="stylesheet" type="text/css" />
		<link rel='stylesheet' href='../css/slider_multiple.css' />  
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="../js/jquery.min.js"></script>
		<script src="../js/jquery.dropotron.js"></script>
		<script src="../js/config.js"></script>
		<script src="../js/skel.min.js"></script>
		<script src="../js/skel-panels.min.js"></script>
		<script src="../js/main.js"></script>
		<script src='../js/wpts_slider_multiple.js'></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				jQuery('#my_carousel_ct').tsSlider({
					   thumbs:''
					  , width:'100%'
					  , showText: true 
					  , autoplay:15000
					  , imgWidth: 100
					  , imgHeight: 100
					  , imgMarginTop: 0
					  , imgMarginLeft: 0
					  , squared: true 
					  , textSquarePosition: 4 
					  , textPosition: 'top'
					  , imgAlignment: 'Center'
					  , textColor: 'F00'
					  , skin: 'transparent'
					  , arrows:'ts-arrow-1'
					  , sliderHeight: 640 
					  , effects:''
								, titleBold: 'false'
								, titleItalic: 'false'
								, textBold: 'true' 
								, textItalic: 'false' 
								, textWidth: 90
					  , background_sld:'#FFF'
					  , background_caption:'#000'
					 });		
				  });
		</script>
		
		
		
		<noscript>
			
			<link rel="stylesheet" href="../css/skel-noscript.css" />
			<link rel="stylesheet" href="../css/style.css" />
			<link rel="stylesheet" href="../css/style-desktop.css" />
		</noscript>
	</head>
	
	<body >
	
	
		<div id='my_carousel_ct' class='carousel-container'>  
			<div id="my_carousel_1" class="carousel slide">
				<div id="my_carousel_2" class="carousel-inner rs-slider">
					<div class="item active">
						<div class="ts_border">
						  <img src="http://localhost:8080<?php echo $album->{"cover"}->{"dir"} ?>" />
						  <div class="carousel-caption">
							<h4><?php echo $album->{"cover"}->{"name"} ?></h4>
							  <p class="ts_txt">
								<p><?php echo $album->{"cover"}->{"description"} ?></p>
							  </p>
						  </div>
						</div>
					</div>
					<?php foreach($album->{"images"} as $image) { ?>	
					<div class="item">
						<div class="ts_border">
						  <img src="http://localhost:8080<?php echo $image->{"dir"} ?>" />
						  <div class="carousel-caption">
							<h4><?php echo $image->{"name"} ?></h4>
							  <p class="ts_txt">
								<p><?php echo $image->{"description"} ?>
								</p>
							  </p>
						  </div>
						</div>
					  </div>   
				<?php } ?>
				</div>
				<a class="carousel-custom" href="#my_carousel_1" data-slide="prev">&lsaquo;</a>
				<a class="carousel-custom" href="#my_carousel_1" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		
	</body>
	
	
	
</html>