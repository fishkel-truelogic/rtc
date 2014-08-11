<!DOCTYPE HTML>
<html>
	<head>
		<title>Reserva tu cancha</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="viewport" content="width=1040" />
		<link rel="stylesheet" href="css/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="css/style-login.css" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700" rel="stylesheet" type="text/css" />
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
        <script src="js/modernizr.custom.63321.js"></script>
		
		
		
		<script type="text/javascript">
			$(document).ready(function() {
				$.loadHeader({state:"", sport:""});
			});
		</script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
	</head>
	<body class="homepage">
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
			<div id="email-send-dialog" style="display:none"></div>
			<div id="player-dialog" style="display:none"></div>
			<div id="edit-player-dialog" style="display:none"></div>
			<div id="register-dialog" style="display:none"></div>
		<!-- Header Wrapper -->
			<div id="header-wrapper" class="backgroundSport">
					
				<!-- Header -->
					<div id="header" class="container">
						
						<!-- Logo -->
							<h1 id="logo"><a>Reservá tu cancha</a></h1>
							<p>Reservas a un click de distancia</p>
						
						<!-- Nav -->
							<nav id="nav">
								<!-- Ajax include header.php -->
							</nav>

					</div>

			</div>
		
		
		<!-- Banner Wrapper -->
			<div id="banner-wrapper">
				<div class="inner backgroundGround">
					
					<!-- Banner -->
						<section id="banner" class="container">
							<nav id="mainFilter">
								<!-- Ajax include mainFilter.php -->
							</nav>
						</section>

				</div>
			</div>
			
		<div id="serch">		
		<!-- Featured fields Wrapper -->
		<!-- Serch Wrapper -->
		</div>

		<!-- Footer Wrapper -->
			<div id="footer-wrapper">

				<!-- Footer -->
					<div id="footer" class="container">
						<header>
							<h2>Tenés canchas para alquilar? <strong>contactanos:</strong></h2>
						</header>
						<div class="row">
							<div class="6u">
								<section>
									<form method="post" action="#">
										<div class="row half">
											<div class="6u">
												<input name="name" placeholder="Nombre" type="text" class="text" />
											</div>
											<div class="6u">
												<input name="email" placeholder="Email" type="text" class="text" />
											</div>
										</div>
										<div class="row half">
											<div class="12u">
												<textarea name="message" placeholder="Mensaje"></textarea>
											</div>
										</div>
										<div class="row half">
											<div class="12u">
												<a href="#" class="button button-icon icon icon-envelope">Enviar mensaje</a>
											</div>
										</div>
									</form>
								</section>
							</div>
							<div class="6u">
								<section>
									<p>Erat lorem ipsum veroeros consequat magna tempus lorem ipsum consequat Phaselamet 
									mollis tortor congue. Sed quis mauris sit amet magna accumsan tristique. Curabitur 
									leo nibh, rutrum eu malesuada.</p>
									<div class="row">
										<ul class="icons 6u">
											<li class="icon icon-home">
												1234 Somewhere Road<br />
												Nashville, TN 00000<br />
												USA
											</li>
											<li class="icon icon-phone">
												(000) 000-0000
											</li>
											<li class="icon icon-envelope">
												<a href="#">info@untitled.tld</a>
											</li>
										</ul>
										<ul class="icons 6u">
											<li class="icon icon-twitter">
												<a href="http://twitter.com/n33co">@untitled-tld</a>
											</li>
											<li class="icon icon-dribbble">
												<a href="http://dribbble.com/n33">dribbble.com/untitled-tld</a>
											</li>
											<li class="icon icon-facebook">
												<a href="#">facebook.com/untitled-tld</a>
											</li>
											<li class="icon icon-google-plus">
												<a href="#">google.com/+untitled-tld</a>
											</li>
										</ul>
									</div>
								</section>
							</div>
						</div>
					</div>

				<!-- Copyright -->
					<div id="copyright" class="container">
						<ul class="links">
							<li>&copy; Reservá tu cancha. All rights reserved</li>
							<li>Demo images: <a href="http://regularjane.deviantart.com/">regularjane</a></li>
							<li>Design: <a href="http://html5up.net/">HTML5 UP</a></li>
						</ul>
					</div>

			</div>

	</body>
</html>