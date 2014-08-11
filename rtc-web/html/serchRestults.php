<?php 
	include '../php/restRequest.php';
	
	$serchParameter = '?';
	
	$state = '';
	if (isset($_REQUEST['state'])) {
		$state = $_REQUEST['state'];
		$serchParameter = $serchParameter.'state='.$state;
	} 
	
	$sport = '';
	if (isset($_REQUEST['sport'])) {
		$sport = $_REQUEST['sport'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&sport='.$sport;
		} else {
			$serchParameter = $serchParameter.'sport='.$sport;
		}
	} 
	
	$district = '';
	if (isset($_REQUEST['district'])) {
		$district = $_REQUEST['district'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&district='.$district;
		} else {
			$serchParameter = $serchParameter.'district='.$district;
		}
	}
	
	$ground = '';
	if (isset($_REQUEST['ground'])) {	
		$ground = $_REQUEST['ground'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&ground='.$ground;
		} else {
			$serchParameter = $serchParameter.'ground='.$ground;
		}
	}
	
	$hour = '';
	if (isset($_REQUEST['hour'])) {
		$hour = $_REQUEST['hour'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&hour='.$hour;
		} else {
			$serchParameter = $serchParameter.'hour='.$hour;
		}
	}
	
	$day = '';
	if (isset($_REQUEST['day'])) {
		$day = $_REQUEST['day'];
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&day='.$day;
		} else {
			$serchParameter = $serchParameter.'day='.$day;
		}
	}
	
	if (isset($_REQUEST['page'])) {
		if ($serchParameter != '?') {
			$serchParameter = $serchParameter.'&page='. $_REQUEST['page'];
		} else {
			$serchParameter = $serchParameter.'page='. $_REQUEST['page'];
		}
	} 
	
		
	if ($serchParameter == '?') {
		$serchParameter = '';
	}

	$stablishmentsPage = getRequest($urlServices.'stablishments'.replace($serchParameter));
	
	
	if ($stablishmentsPage->{"pageCant"} >= $stablishmentsPage->{"pageNumber"}) {
	
		foreach ($stablishmentsPage->{"pageElements"} as $stablishment) { 
?>


<li id="<?php echo 'page'.$stablishmentsPage->{"pageNumber"} ?>" style="width:800px">

	<!-- Highlight -->
		<article class="is-highlight">
			<a style="float:left" href="stablishment.php?id=<?php echo $stablishment->{"id"} ?>&day=<?php echo $day ?>" class="image image-left"><img src="http://localhost:8080<?php echo $stablishment->{"album"}->{"cover"}->{"dir"}?>" style="width:175px; height:155px" /></a>
			<div>
				<h3>
					<a style="padding-right:5px float:left" href="stablishment.php?id=<?php echo $stablishment->{"id"} ?>&day=<?php echo $day ?>"><?php echo $stablishment->{"name"}?><a>
					<div id="r1" class="rate_widget">
						<input type="hidden" value="<?php echo '3.4' ?>">
						<?php for ($i = 1; $i <= 5; $i++) { ?>
							<div class="star_<?php echo $i ?> ratings_stars <?php echo ($i <= round(4.4)) ? 'ratings_vote' : ''; ?>"></div>
						<?php } ?>
					</div>
				</h3>
				
				<p style="height:60px; overflow-y:hidden"><?php echo $stablishment->{"description"}?></p>
				<p><?php echo $stablishment->{"address"}?> | <?php echo $stablishment->{"openDay"}?> a <?php echo $stablishment->{"closeDay"}?> de <?php echo $stablishment->{"openHour"}?> a <?php echo $stablishment->{"closeHour"}?> | $$$</p>
			</div>
		</article>

</li>
<?php }
	} ?>
	
