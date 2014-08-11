<!DOCTYPE HTML>

<?php include 'php/player.php'; ?>
<html>
	<head>
		<title>Reserva tu cancha</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="viewport" content="width=1040" />
		<link rel="stylesheet" href="css/jquery-ui.css" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700" rel="stylesheet" type="text/css" />
		<link rel='stylesheet' href='css/slider_multiple.css' /> 
		<link rel="stylesheet" type="text/css" href="css/style-login.css" />
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="js/jquery.tmpl.js"></script>
		<script src="js/jquery.dropotron.js"></script>
		<script src="js/config.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/hashing.js"></script>
		<script src="js/main.js"></script>
		<script type='text/javascript' src='js/wpts_slider_multiple.js'></script>
		<script src="js/modernizr.custom.63321.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('#nav > ul').dropotron({ 
				offsetX: -2,
				offsetY: -8,
				mode: 'fade',
				noOpenerFade: true,
				hoverDelay: 0,
				hideDelay: 350,
				detach: false
			});
			});
			$(document).ready(function() {
				$('.is-post > ul').dropotron({ 
				offsetX: -2,
				offsetY: -8,
				mode: 'fade',
				noOpenerFade: true,
				hoverDelay: 0,
				hideDelay: 350,
				detach: false
			});
			
			
				
			
			
			});
		</script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
	</head>
	<body class="right-sidebar">
		<div id="sessionUser" style="display:none"></div>
	<div id="fb-root"></div>
		<script>
		  window.fbAsyncInit = function() {
				FB.init({
				  appId      : '631821006841758', 
				  channelUrl : '//localhost:8081/channel.html',
				  status     : true, 
				  cookie     : true, 
				  xfbml      : true  
				});

			  FB.Event.subscribe('auth.authResponseChange', function(response) {
				
				if (response.status === 'connected') {
				  statusConnected();
				} 
			  });
		};
	
		
		  (function(d){
			 var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
			 if (d.getElementById(id)) {return;}
			 js = d.createElement('script'); js.id = id; js.async = true;
			 js.src = "//connect.facebook.net/es_AR/all.js";
			 ref.parentNode.insertBefore(js, ref);
		   }(document));
		   
		  function statusConnected() {
			FB.api('/me?fields=id,name,email', function(response) {
				$.loadUserInSession(JSON.stringify(response));
				try { $("#login-dialog").dialog("close"); } catch (e) {}
			});
		  }
		  
		 
		</script>
			<div id="login-dialog" style="display:none"></div>
			<div id="player-dialog" style="display:none"></div>
			<div id="register-dialog" style="display:none"></div>
			<div id="edit-player-dialog" style="display:none"></div>
		<!-- Header Wrapper -->
			<div id="header-wrapper" style="height: 150px;">
						
				<!-- Header -->
					<div id="header" class="container">
						
											
						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li><a href="/rtc-web" class="icon icon-home"><span>HOME</span></a></li>
									<?php 
									session_start();
									
									if(isset($_SESSION['player'])) { 
										$player = unserialize($_SESSION['player']);
									?>
										<li><a href="" id="login-fb-icon" class="icon icon-user" onclick="$.loadPlayerProfile();"><span><?php echo $player->__get('username');?></span></a></li>
									<?php } else { ?>
										<li><a href="" id="login-fb-icon" class="icon icon-facebook-sign" onclick="$.loadLoginDialog();"><span>ingresar</span></a></li>
									<?php } ?>
								</ul>
							</nav>

					</div>

			</div>
	
			<?php
				

				
				include 'php/restRequest.php';
			
				if (isset($_GET['day'])) {
					$day = empty($_GET['day']) ? "DÍA" : $_GET['day'];
				} else {
					$day = "DÍA";
				}
				
				if (is_null($day)) {
					$day = "DÍA";
				}
				
				if (isset($_REQUEST['id'])) {
					$id = $_REQUEST['id'];
				} else {
					$id = "";
				}
				
				$param = '';
				if ($day != "DÍA") {
					$param = $param.'?day='.$day;
				}
				
				$stablishment = getRequest($urlServices.'stablishments/'.$id.$param );
				
				
				
			?>
			
			<div id="images-dialog" style="display:none"></div>
			<div id="map-dialog" style="display:none"></div>
			<div id="book-confirmation-dialog" style="display:none"></div>
		<!-- Header Wrapper -->
			
			
			<div id="banner-wrapper">
				<div class="inner" >

					<!-- Banner -->
						<section id="banner" class="container" >
							<img src="http://localhost:8080<?php echo $stablishment->{"album"}->{"cover"}->{"dir"}?>">
						</section>

				</div>
			</div>
			
		<!-- Main Wrapper -->
			<div id="main-wrapper">

				<!-- Main -->
					<div id="main" class="container">
						<div class="row">
						
						
							<div id="fields-container" class="8u skel-cell-mainContent">
							<?php foreach ($stablishment->{"fields"} as $field) { ?>
							<!-- fields -->
								<div style="margin-bottom: 40px;" >

									<!-- Post -->
										<article  class="is-post">
											<header>
												<h2><?php echo $field->{"name"} ?></h2>
											</header>
											<span class="image image-full"><img src="http://localhost:8080<?php echo $field->{"album"}->{"cover"}->{"dir"}?>" style="cursor:pointer" onclick="$.loadImagesDialog('<?php echo $field->{"album"}->{"id"} ?>');" /></span>
											<h3><?php echo $field->{"description"}?></h3>
											
											<ul>
												<li>
													<a href="" class="icon icon-calendar"><span id="<?php echo 'day'.$field->{"id"} ?>"><?php echo $day != 'DÍA' ? $day : 'Seleccionar Día' ?></span></a>
													<ul>
														<?php for ($i = 0; $i <= 30; $i++) { 
																$mkTime = mktime(0, 0, 0, date("m"), date("d") + $i, date("Y"));
																$dayIT = date("d/m/Y", $mkTime);
														?>
														<li>
															<a href="" onclick="$('#<?php echo 'day'.$field->{"id"} ?>').html('<?php echo $dayIT ?>')">
																<?php echo $dayIT ?> 
															</a>
														</li>
														<?php } ?>
													</ul>
												</li>
												
												<li>
													<a href="" class="icon icon-time"><span>hora de reserva</span></a>
													<ul>
														<?php for ($hourIT = 0; $hourIT <= 24; $hourIT++) { ?>
														<li>
															<a href="" onclick="$.loadBookConfirmationDialog();" >
															<?php echo $hourIT.':00' ?> 
															</a>
														</li>
														<?php if ($hourIT == 24) break; ?>
														<li>
															<a href="" onclick="$.loadBookConfirmationDialog();" >
																<?php echo $hourIT.':30' ?>
															</a>
														</li>
														<?php } ?>
													</ul>
												</li>
											</ul>
											
										</article>
										
								</div>
							
							<?php } ?>	
							
							
						</div>
						<!-- Sidebar -->
						<div id="sidebar" class="4u" >

								
									<!-- Highlights -->
										<section>
											<ul class="divided">
												<li>

													<!-- Highlight -->
														<article class="is-highlight">
															<header>
																<h3><a><span style="width:5px"><i  class="icon icon-map-marker" style="margin-right:5px"></i><?php echo $stablishment->{"address"} ?></span></a></h3>
															</header>
															
															<a style="width: 100%;" class="image image-left">
																<iframe style="margin-bottom: -8px; width: 100%" src="html/stablishmentMap.php?lat=<?php echo $stablishment->{"mapMarker"}->{"lat"} ?>&lng=<?php echo $stablishment->{"mapMarker"}->{"lng"} ?>&address=<?php echo $stablishment->{"address"} ?>" seamless>
																	<p>Your browser does not support iframes.</p>
																</iframe>
															</a>
															<ul class="actions">
																<li><a class="button button-icon icon icon-map-marker" onclick="$.loadMapDialog(<?php echo $stablishment->{"mapMarker"}->{"lat"}.', '.$stablishment->{"mapMarker"}->{"lng"}.', \''.$stablishment->{"address"}.'\'' ?>)">Ampliar Mapa</a></li>
															</ul>
														</article>

												</li>
												
											<!--	<li>

													
														<article class="is-highlight">
															<header>
																<h3><a href="#">Something of note</a></h3>
															</header>
															<a href="http://regularjane.deviantart.com/art/In-Your-Hands-356733525" class="image image-left"><img src="images/pic06.jpg" alt="" /></a>
															<p>Phasellus  sed laoreet massa id justo mattis pharetra. Fusce suscipit ligula vel quam 
															viverra sit amet mollis tortor congue magna lorem ipsum dolor et quisque ut odio facilisis 
															convallis. Etiam non nunc vel est suscipit convallis non id orci. Ut interdum tempus 
															facilisis convallis. Etiam non nunc vel est suscipit convallis non id orci.</p>
															<ul class="actions">
																<li><a href="#" class="button button-icon icon icon-file">Learn More</a></li>
															</ul>
														</article>

												</li> 
												-->
											</ul>
										</section>
										
									
										<section>
											<ul class="divided">
											<h2>Servicios</h2>
											<?php foreach($stablishment->{"tpServices"} as $tpService) { ?>
												<li>

														<article class="is-excerpt">
															<header>
																<h3><a><?php echo $tpService->{"name"} ?></a></h3>
															</header>
															<a class="image image-left"><img src="http://localhost:8080<?php echo $tpService->{"album"}->{"cover"}->{"dir"} ?>" style="cursor:pointer" onclick="$.loadImagesDialog('<?php echo $tpService->{"album"}->{"id"} ?>');"/></a>
															<p><?php echo $tpService->{"description"} ?></p>
														</article>

												</li>
											<?php } ?>
											</ul>
										</section>
									
								
								</div>
						</div>
						
							
					</div>
					
					

			</div>

		<!-- Footer Wrapper -->
			<div id="footer-wrapper">

				<!-- Footer -->
					<div id="footer" class="container">
						<header>
							<h2>Comentarios</h2>
						</header>
						<div class="row">
						
						</div>
					</div>

				<!-- Copyright -->
					<div id="copyright" class="container">
						<ul class="links">
							<li>&copy; Untitled. All rights reserved</li>
							<li>Demo images: <a href="http://regularjane.deviantart.com/">regularjane</a></li>
							<li>Design: <a href="http://html5up.net/">HTML5 UP</a></li>
						</ul>
					</div>

			</div>
	</body>
	
</html>