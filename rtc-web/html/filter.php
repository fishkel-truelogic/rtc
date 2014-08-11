<script type="text/javascript">
	$('#nav2 > ul').dropotron({ 
				offsetX: -2,
				offsetY: -8,
				mode: 'fade',
				expandMode: 'click',
				noOpenerFade: true,
				hoverDelay: 0,
				hideDelay: 350,
				detach: false
			});
	$('#nav3 > ul').dropotron({ 
		offsetX: -2,
		offsetY: -8,
		mode: 'fade',
		expandMode: 'click',
		noOpenerFade: true,
		hoverDelay: 0,
		hideDelay: 350,
		detach: false
	});
</script>

<?php
	include '../php/jqueryAjax.php';
	include '../php/restRequest.php';
	
	$states = getRequest($urlServices . 'districts/states');
	$sports = getRequest($urlServices . 'sports/');
	
	$serchParameter = '?';
	
	
	$state = '';
	if (isset($_REQUEST['state'])) {
		$state = $_REQUEST['state'];
		
		$districts = getRequest($urlServices . 'districts/?state=' . replace($state));
		
		$serchParameter = $serchParameter.'state='.$state;
	} 
	
	$view = '';
	if (isset($_REQUEST['view'])) {
		$view = $_REQUEST['view'];
		if ($view == 'MAPA') {
			if ($serchParameter != '?') {
				$serchParameter = $serchParameter.'&pageSize=-1';
			} else {
				$serchParameter = $serchParameter.'pageSize=-1';
			}
		}
	}
	
	$sport = '';
	if (isset($_REQUEST['sport'])) {
		$sport = $_REQUEST['sport'];
		if (!empty($sport)) {
			$grounds = getRequest($urlServices . 'grounds/' . replace($sport));
		}
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
	
	

	$pageCant = getRequest($urlServices.'stablishments/count'.replace($serchParameter));
	
?>				
								
<section>
	<ul>
		<li>
			<article id="nav3" class="is-excerpt">
				<ul>	
					<li style="margin-bottom:7px">
						<a href="" class="<?php echo (!empty($view) && $view == 'MAPA') ? 'icon icon-map-marker' : 'icon icon-list' ?>"><span><?php echo empty($view) ? 'VISTAS' : $view ?></span></a>
						<ul>
							<li>
								<a href="" class="icon icon-map-marker" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'MAPA\'}'  ?>);">
									Mapa 
								</a>
							</li>
							<li>
								<a href="" class="icon icon-list" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'LISTA\'}'  ?>);">
									Lista 
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</article>
			<!-- Excerpt -->
				<article class="is-excerpt">
					<header>
						<?php if (!empty($state)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $state ?></span>
						<?php } if (!empty($district)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . $sport . '\',district:\'' . '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $district ?></span>
						<?php } if (!empty($sport)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $sport ?></span>
						<?php } if (!empty($ground)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $ground ?></span>
						<?php } if (!empty($hour)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' . '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $hour ?></span>
						<?php } if (!empty($day)) { ?>
						<span class="date icon-remove-sign" onclick="$.loadFilter(<?php echo  '{state:\'' .$state. '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . '\',view:\'' . $view . '\'}'  ?>);"><?php echo $day ?></span>
						<?php } ?>
					</header>
				</article>

		</li>
		<li>

			<!-- Excerpt -->
				<article id="nav2" class="is-excerpt">
					
					<ul>	
						<li style="margin-bottom:7px">
							<a href="" class="icon icon-map-marker"><span><?php echo empty($state) ? 'LOCALIDAD' : $state ?></span></a>
							<ul>
							<?php foreach ($states as $stateIT) { ?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $stateIT . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo $stateIT ?> 
									</a>
								</li>
								<?php } ?>
							</ul>
						</li>
						<?php if (isset($districts)) { ?>
						<li style="margin-bottom:7px">
							<a href="" class="icon icon-map-marker"><span><?php echo empty($district) ? 'BARRIO' : $district ?></span></a>
							<ul>
							<?php foreach ($districts as $districtIT) { ?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$districtIT->{"name"}. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo $districtIT->{"name"} ?>
									</a>
								</li>
							<?php } ?>
							</ul>
						</li>
						<?php } ?>
						<li style="margin-bottom:7px">
							<a href="" class="icon icon-caret-down"><span><?php echo empty($sport) ? 'DEPORTE' : $sport ?></span></a>
							<ul>
							<?php foreach ($sports as $sportIT) { ?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sportIT->{"name"} . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo  $sportIT->{"name"} ?> 
									</a>
								</li>
								<?php } ?>
							</ul>
						</li>
						
						<?php if (!empty($grounds)) { ?>
						<li style="margin-bottom:7px">
							<a href="" class="icon icon-list"><span><?php echo empty($ground) ? 'SUELO' : $ground ?></span></a>
							<ul>
								<?php foreach ($grounds as $groundIT) {?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $groundIT->{"text"} . '\',hour:\'' .$hour. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo $groundIT->{"text"} ?> 
									</a>
								</li>
								<?php }	?>
							</ul>
						</li>
						<?php } ?>
						<li style="margin-bottom:7px">
							<a href="" class="icon icon-time"><span><?php echo empty($hour) ? 'HORA' : $hour ?></span></a>
							<ul>
								<?php for ($hourIT = 0; $hourIT <= 24; $hourIT++) { ?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hourIT.':00'. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
									<?php echo $hourIT.':00' ?> 
									</a>
								</li>
								<?php if ($hourIT == 24) break; ?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hourIT.'30'. '\',day:\'' . $day . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo $hourIT.':30' ?>
									</a>
								</li>
								<?php } ?>
							</ul>
						</li>
						<li style="margin-bottom:130px">
							<a href="" class="icon icon-calendar"><span><?php echo empty($day) ? 'DÍA' : $day ?></span></a>
							<ul>
							<?php for ($i = 0; $i <= 30; $i++) { 
									$mkTime = mktime(0, 0, 0, date("m"), date("d") + $i, date("Y"));
									$dayIT = date("d/m/Y", $mkTime);
							?>
								<li>
									<a href="" onclick="$.loadFilter(<?php echo  '{state:\'' . $state . '\',sport:\'' . $sport . '\',district:\'' .$district. '\',ground:\'' . $ground . '\',hour:\'' .$hour. '\',day:\'' . $dayIT . '\',view:\'' . $view . '\'}'  ?>);">
										<?php echo $dayIT ?> 
									</a>
								</li>		
							<?php } ?>
							</ul>
						</li>
					</ul>
				</article>

		</li>
	</ul>
</section>

<script type="text/javascript">
		<?php if ($view == 'MAPA') { ?>
			$.loadSerchResultsMap(<?php echo '{state:\''.$state.'\',district:\''.$district.'\',sport:\''.$sport.'\',ground:\''.$ground.'\',hour:\''.$hour.'\',day:\''.$day.'\'}'?>);
		<?php } else {
			echo loadSerchResultsSentence('{state:\''.$state.'\',district:\''.$district.'\',sport:\''.$sport.'\',ground:\''.$ground.'\',hour:\''.$hour.'\',day:\''.$day.'\',view:\'' . $view . '\'}', '1', $pageCant->{"pageCant"});
		}
		?>
</script>
								