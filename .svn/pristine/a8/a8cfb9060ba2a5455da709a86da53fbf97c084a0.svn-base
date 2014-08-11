<?php
include '../php/player.php';
include '../php/restRequest.php';
session_start();
$player = unserialize($_SESSION['player']);
?>

<script type="text/javascript">
$('#image').click(function(){
	$('#input_image').click();
});

var emailOriginalValue;

function changeEmailAction() {
	if ($('#email_edit_input').val() != emailOriginalValue) {
		if ($.validateEmail($('#email_edit_input').val())) {
			$.updatePlayerEmail();  
		} else {
			$.loadPlayerProfile("email inválido");
			
		}
	} else {
		$('#email_edit').css({'display':'none'});
		$('#email_label').css({'display':'block'});
	}

}

$('#email_label').click(function(){
	$('#email_label').css({'display':'none'});
	$('#email_edit').css({'display':'block'});
	$('#email_edit_input').focus();
	emailOriginalValue = $('#email_edit_input').val();
});

$('#email_edit_input').keypress(function(event){
	var keycode = (event.keyCode ? event.keyCode : event.which);
    if(keycode == '13') {
		changeEmailAction();
    }
	
});

$('#email_edit_input').blur(function(event){
	changeEmailAction();
});

	
	var formdata = false;

	function showUploadedItem (source) {
  		var img = document.getElementById("image");
	  	img.src = source;
	}   


	
 	$('#input_image').change(function() {
		if (window.FormData) {
			formdata = new FormData();
		}
 		
 		var i = 0, len = this.files.length, img, reader, file;
	
		for ( ; i < len; i++ ) {
			file = this.files[i];
	
			if (file.type.match(/image.*/)) {
				if ( window.FileReader ) {
					reader = new FileReader();
					reader.onloadend = function (e) { 
						showUploadedItem(e.target.result);
					};
					reader.readAsDataURL(file);
				}
				if (formdata) {
					formdata.append("images[]", file);
				}
			}	
		}
	
		if (formdata) {
			$.ajax({
				url: "php/upload.php?type=" + file.type.split("/")[1],
				type: "POST",
				data: formdata,
				processData: false,
				contentType: false,
				success: function() {
					$("#player-dialog").dialog("close");
					$.loadPlayerProfile();
				}
			});
		}
	});
</script>

<div id="main-wrapper-dialog">
	<div class="containerLogin">	
		<section class="main">
			<div class="form-2" style="top:35px">
				<h1>
					<span class="log-in">Perfil del Jugador</span>
					<span id="registerError" class="loginError" style="visibility:hidden"></span>
				</h1>
				<p class="float">
				<?php if ($player->__get('fbId') != null) { ?>
					<a class="image image-left"><img src="http://graph.facebook.com/<?php echo $player->__get('fbId') ?>/picture?width=160&height=160" alt="" /></a>
				<?php } else { 
						
						if (checkImage($urlImages.$player->__get('id').'.gif')) {
				?>		
						<a class="image image-left"><img src="images/<?php echo $player->__get('id').'.gif'; ?>" alt="" /></a>
						
				<?php } else { ?>
				
					<a class="image image-left" id="image"><img src="images/pic06.jpg" alt="" /></a>
					<input type="file" id="input_image" style="display:none">
				<?php 
						} 
					}
					?>
				</p>
				<p class="float">
					<label class="icon-user" style="margin-bottom: -30px;"><i style="margin-right:3px"></i><?php echo $player->__get('username') ?></label>
					<ul id="email_edit" style="display:none;">
						<li style="float:left"><input type="text" id="email_edit_input"  value="<?php echo $player->__get('email') ?>"></li>
					</ul>
					<label id="email_label" class="icon-inbox-edit"><i style="margin-right:3px"></i><?php echo $player->__get('email') ?></label>
				</p>
				<p class="clearfix"> 					
				<?php if ($player->__get('fbId') != null) { ?>
					<a class="submit" onclick="FB.logout(function(response) {$.logoutPlayer();});">Cerrar Sessión</a>
				<?php } else { ?>
					<a class="submit" onclick="$.logoutPlayer();">Cerrar Sessión</a>
				<?php } ?>
				</p>
			</div>​​
		</section>
	</div>
</div>