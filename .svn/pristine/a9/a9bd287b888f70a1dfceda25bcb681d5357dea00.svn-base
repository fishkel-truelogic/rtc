<?php
	include '../php/restRequest.php';
	include '../php/jqueryAjax.php';

	if (isset($_REQUEST['state'])) {
		$state = $_REQUEST['state'];
	} 
	
	$districts = getRequest($urlServices . 'districts/?state=' . replace($state));
	
	if (isset($_REQUEST['sport'])) {
		$sport = $_REQUEST['sport'];
	} 
	
	$grounds = getRequest($urlServices . 'grounds/' . replace($sport));
	
	if (isset($_REQUEST['district'])) {
		$district = empty($_REQUEST['district']) ? "BARRIO" : $_REQUEST['district'];
	} else {
		$district = "BARRIO";
	}
	
	if (isset($_REQUEST['ground'])) {	
		$ground = empty($_REQUEST['ground']) ? "SUELO" : $_REQUEST['ground'];
	} else {
		$ground = "SUELO";
	}
	
	if (isset($_REQUEST['hour'])) {
		$hour = empty($_REQUEST['ground'])  ? "HORA" : $_REQUEST['hour'];
	} else {
		$hour = "HORA";
	}
	
	if (isset($_REQUEST['day'])) {
		$day = empty($_REQUEST['day'])  ? "DÍA" : $_REQUEST['day'];
	} else {
		$day = "DÍA";
	}
	
?>
<ul>
	<li>
		<a href="" class="icon icon-map-marker" style="color: #FFF"><span><?php echo $district ?></span></a>
		<ul>
		<?php foreach ($districts as $districtIT) { ?>
			<li>
				<a href="" onclick="<?php echo loadMainFilterSentence($state, $districtIT->{"name"}, $sport, $ground, $hour, $day); ?> ">
					<?php echo $districtIT->{"name"} ?>
				</a>
			</li>
		<?php } ?>
		</ul>
	</li>
	<?php if (!empty($grounds)) { ?>
	<li>
		<a href="" class="icon icon-list" style="color: #FFF"><span><?php echo $ground ?></span></a>
		<ul>
			<?php foreach ($grounds as $groundIT) {?>
			<li>
				<a href="" onclick=" <?php echo loadMainFilterSentence($state, $district, $sport, $groundIT->{"text"}, $hour, $day); ?> ">
					<?php echo $groundIT->{"text"} ?> 
				</a>
			</li>
			<?php }	?>
		</ul>
	</li>
	<?php } ?>
	<li>
		<a href="" class="icon icon-time" style="color: #FFF"><span><?php echo $hour ?></span></a>
		<ul>
			<?php for ($hourIT = 0; $hourIT <= 24; $hourIT++) { ?>
			<li>
				<a href="" onclick=" <?php echo loadMainFilterSentence($state, $district, $sport, $ground, $hourIT.':00', $day); ?> ">
				<?php echo $hourIT.':00' ?> 
			    </a>
			</li>
			<?php if ($hourIT == 24) break; ?>
			<li>
				<a href="" onclick=" <?php echo loadMainFilterSentence($state, $district, $sport, $ground, $hourIT.':30', $day); ?> ">
					<?php echo $hourIT.':30' ?>
				</a>
			</li>
			<?php } ?>
		</ul>
	</li>
	<li>
		<a href="" class="icon icon-calendar" style="color: #FFF"><span><?php echo $day ?></span></a>
		<ul>
		<?php for ($i = 0; $i <= 30; $i++) { 
				$mkTime = mktime(0, 0, 0, date("m"), date("d") + $i, date("Y"));
				$dayIT = date("d/m/Y", $mkTime);
		?>
			<li>
				<a href="" onclick=" <?php echo loadMainFilterSentence($state, $district, $sport, $ground, $hour, $dayIT); ?> ">
					<?php echo $dayIT ?> 
				</a>
			</li>		
		<?php } ?>
		</ul>
	</li>
	<li>
		
		<a href=""  class="icon icon-search" onclick="$('#mainFilterForm').submit();" style="color: #FFF" ><span>Buscar</span></a>
		
	</li>
</ul>
<form id="mainFilterForm" action="serch.php" method="get">
	<input type="hidden" name="state" value="<?php echo $state ?>"/>
	<input type="hidden" name="district" value="<?php echo $district == 'BARRIO' ? '' : $district ?>"/>
	<input type="hidden" name="hour" value="<?php echo $hour == 'HORA' ? '' : $hour?>"/>
	<input type="hidden" name="ground" value="<?php echo $ground == 'SUELO' ? '' : $ground?>"/>
	<input type="hidden" name="day" value="<?php echo $day == 'DÍA' ? '' : $day ?>"/>
	<input type="hidden" name="sport" value="<?php echo $sport == 'DEPORTE' ? '' : $sport ?>"/>
</form>
