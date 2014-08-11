<?php
	include 'restRequest.php';
	include 'player.php';
	session_start();
	
	if (isset($_REQUEST['action'])) {
		if ($_REQUEST['action'] == 'login') {
			$user = getRequest($urlServices . 'players/?login=' . $_REQUEST['login'] . '&password=' . $_REQUEST['password']);
			if (!property_exists($user, 'status')) {
				$player = new Player;
				$player->__set('username', $user->{'username'});
				$player->__set('email', $user->{'email'});
				$player->__set('id', $user->{'id'});
				$player->__set('fbPlayer', $user->{'fbPlayer'});
				$_SESSION['player'] = serialize($player);
?>
				<script type="text/javascript">
					location.reload();
				</script>
<?php
			} elseif ($user->{"status"} == 406) {
?>
				<script type="text/javascript">
					$.loadLoginDialog('<?php echo $user->{"message"}?>');
				</script>
<?php				
			}
			
		} elseif ($_REQUEST['action'] == 'logout') {
			unset($_SESSION['player']);
?>
			<script type="text/javascript">
				location.reload();
			</script>
<?php		
		} elseif ($_REQUEST['action'] == 'register') {
			$player = new Player;
			$player->input();
			$player->__set('emailConfirmed', false);
			$player->__set('enabled', true);
			$resp = postRequest($urlServices . 'players/', $player);
			
			if (!property_exists($resp, 'status')) {	
				$mailResp = getRequest($urlServices . 'players/sendmail?email=' . $player->__get('email') . '&username=' . $player->__get('username'). '&id=' . $player->__get('id') , $player);
				if ($mailResp == 'ok') {
?>
					<script type="text/javascript">
						$('#register-dialog').dialog('close');
						$('#login-dialog').dialog('close');
						$.loadEmailSendDialog('<?php echo $player->__get('username')?>', 'new');
					</script>
<?php		
				} else {
				//delete del usuario
?>
					<script type="text/javascript">
						$('#register-dialog').dialog('close');
						$('#login-dialog').dialog('close');
						$.loadEmailSendErrorDialog('<?php echo $player->__get('id')?>');
					</script>
<?php				
				}
			} elseif ($resp->{"status"} == 406) {
?>
				<script type="text/javascript">
					$.loadRegisterDialog('<?php echo $resp->{"message"}?>');
				</script>
<?php					
			}
			
		} elseif ($_REQUEST['action'] == 'update') {
			$player = unserialize($_SESSION['player']);
			$player->__set('email', $_REQUEST['email']);
			$player->__set('emailConfirmed', false);
			$resp = postRequest($urlServices . 'players/' . $player->__get('id') , $player);
			if (is_null($resp)) {	
				$_SESSION['player'] = serialize($player);
?>
				<script type="text/javascript">
					$.loadPlayerProfile();
					$.loadEmailSendDialog('<?php echo $player->__get('username')?>', 'old');
				</script>
<?php	
			} elseif ($resp->{"status"} == 406) {
?>
				<script type="text/javascript">
					$.loadPlayerProfile('<?php echo $resp->{"message"}?>');
				</script>
<?php		
			}
			
		}
	}
	
	
	
	if (isset($_REQUEST['fbJson']) && !isset($_SESSION['player'])) {
		$user = json_decode($_REQUEST['fbJson']);
		$resp = getRequest($urlServices . 'players/' . $user->{'id'});
		if (isset($resp->{'id'})) {
			$player = new Player;
			$player->__set('username', $resp->{'username'});
			$player->__set('email', $resp->{'email'});
			$player->__set('id', $resp->{'id'});
			$player->__set('fbId', $resp->{'fbId'});
			$_SESSION['player'] = serialize($player);		
?>
			<script type="text/javascript">
				location.reload();
			</script>
<?php
		} else {
		
			$player = new Player;
			$player->__set('username', $user->{'name'});
			$player->__set('email', $user->{'email'});
			$player->__set('fbId', $user->{'id'});	
			$resp = postRequest($urlServices . 'players/', $player);
			
			if (is_null($resp)) {

				$resp = getRequest($urlServices . 'players/' . $user->{'id'});
				$player = new Player;
				$player->__set('username', $resp->{'username'});
				$player->__set('email', $resp->{'email'});
				$player->__set('id', $resp->{'id'});
				$player->__set('fbId', $resp->{'fbId'});
				$_SESSION['player'] = serialize($player);
?>
				<script type="text/javascript">
					location.reload();
					$.loadEmailSendDialog('<?php echo $player->__get('username')?>', 'fb');
				</script>
<?php
			} else {
					echo 'error';
			}
		}
		
	}

?>