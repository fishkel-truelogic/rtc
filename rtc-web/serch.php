<!DOCTYPE HTML>
<?php
	
	include 'php/restRequest.php';
	include 'php/player.php';
	
	$state = '';
	if (isset($_REQUEST['state'])) {
		$state = $_REQUEST['state'];
	} 
	
	$sport = '';
	if (isset($_REQUEST['sport'])) {
		$sport = $_REQUEST['sport'];
	} 
	
	$district = '';
	if (isset($_REQUEST['district'])) {
		$district = $_REQUEST['district'];
	}
	
	$ground = '';
	if (isset($_REQUEST['ground'])) {	
		$ground = $_REQUEST['ground'];
	}
	
	$hour = '';
	if (isset($_REQUEST['hour'])) {
		$hour = $_REQUEST['hour'];
	}
	
	$day = '';
	if (isset($_REQUEST['day'])) {
		$day = $_REQUEST['day'];
	}
	
	
?>		
		
<html>
	<head>
		<title>Reservá tu cancha</title>
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="viewport" content="width=1040" />
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="css/gmap3-menu.css" />
		<link rel="stylesheet" type="text/css" href="css/style-login.css" />
		<link rel="stylesheet" href="css/jquery-ui.css" />
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<script src="js/jquery.tmpl.js"></script>
		<script src="js/jquery.dropotron.js"></script>
		<script src="js/config.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-panels.min.js"></script>
		<script src="js/hashing.js"></script>
		<script src="js/main.js"></script>
		<script src="js/modernizr.custom.63321.js"></script>
		<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
		<script src="js/gmap3.js"></script>
		<script type="text/javascript" src="js/gmap3-menu.js"></script>
		<script type="text/javascript">
			$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . '\'}'  ?>);
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
										<li><a id="login-fb-icon" class="icon icon-user" onclick="$.loadPlayerProfile();"><span><?php echo $player->__get('username');?></span></a></li>
									<?php } else { ?>
										<li><a id="login-fb-icon" class="icon icon-facebook-sign" onclick="$.loadLoginDialog();"><span>ingresar</span></a></li>
									<?php } ?>
								</ul>
							</nav>

					</div>

			</div>
			
		
		
	

		<!-- Main Wrapper -->
		
			<div id="main-wrapper">

			<!-- Main -->
				<div id="main" class="container">
					<div class="row">
					
						<!-- Sidebar -->
							<div id="sidebar" class="4u">
								<!-- ajax injection of request filter -->
							</div>

						<!-- results -->
							<div id="content" class="8u skel-cell-mainContent">

								<div id="sidebar" style="width:100%" class="4u">
							
								
									<section >
										<ul id="results" class="divided">
											<!-- ajax injection of request results -->
										</ul>
									</section>
							
								</div>
								
							</div>

					</div>
				</div>
			</div>
				


		<!-- Footer Wrapper -->
			<div id="footer-wrapper">

				<!-- Footer -->
					<div id="footer" class="container">
						<header>
							<h2>Questions or comments? <strong>Get in touch:</strong></h2>
						</header>
						
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

		
				


