<?php 
	include '../php/restRequest.php';
	

	
	$id = '';
	if (isset($_REQUEST['id'])) {
		$id = $_REQUEST['id'];
	} 
	
	$stablishment = getRequest($urlServices.'stablishments/'.$id);
	
?>
<div style="width:600px; height:180px; overflow:hidden">
		<article class="is-highlight" >
		<a style="float:left" href="stablishment.php?id=<?php echo $stablishment->{"id"} ?>" class="image image-left"><img src="http://localhost:8080<?php echo $stablishment->{"album"}->{"cover"}->{"dir"}?>" style="width:175px; height:155px" /></a>
			<div>
				<h3>
					<a style="padding-right:5px float:left" href="stablishment.php?id=<?php echo $stablishment->{"id"} ?>"><?php echo $stablishment->{"name"}?><a>
					<div id="r1" class="rate_widget">
						<input type="hidden" value="<?php echo '3.4' ?>">
						<?php for ($i = 1; $i <= 5; $i++) { ?>
						<div class="star_<?php echo $i ?> ratings_stars <?php echo ($i <= round(4.5)) ? 'ratings_vote' : ''; ?>"></div>
						<?php } ?>
						
					</div>
				</h3>
				
				<p style="height:60px; overflow-y:hidden"><?php echo $stablishment->{"description"}?></p>
				<p><?php echo $stablishment->{"address"}?> | <?php echo $stablishment->{"openDay"}?> a <?php echo $stablishment->{"closeDay"}?> de <?php echo $stablishment->{"openHour"}?> a <?php echo $stablishment->{"closeHour"}?> | $$$</p>
			</div>
			
		</article>
</div>
