(function($){
	
	$.validateEmail = function(email) { 
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	};
	
	$.validateStablishment = function(){
		var name = $("#name");
		var openHour = $("#openHour");
		var openDay = $("#openDay");
		var closeDay = $("#closeDay");
		var closeHour = $("#closeHour");
		var district = $("#districts");
		var web = $("#web");
		var email = $("#email");
		var address = $("#address");
		var valid = true;
		
		if (name.val() == null || name.val() == "") {
			name.css({
				'border-color' : 'red'
			});
			valid = false;
				
		} else {
			name.removeAttr('style');
		}
		
		
		if (openHour.val() == null || openHour.val() == "") {
			openHour.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			openHour.removeAttr('style');
		}
		
		if (openDay.val() == null || openDay.val() == "") {
			openDay.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			openDay.removeAttr('style');
		}
		
		if (closeDay.val() == null || closeDay.val() == "") {
			closeDay.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			closeDay.removeAttr('style');
		}
		
		if (closeHour.val() == null || closeHour.val() == "") {
			closeHour.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			closeHour.removeAttr('style');
		}
		
		if (district.val() == null || district.val() == "") {
			district.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			$('#' + district.val()).attr('name', 'district.name');
			district.removeAttr('style');
		}
		
		if (web.val() == null || web.val() == "") {
			web.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			web.removeAttr('style');
		}
		
		if (email.val() == null || email.val() == "" || !$.validateEmail(email.val())) {
			email.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			email.removeAttr('style');
		}

		if (address.val() == null || address.val() == "") {
			address.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			address.removeAttr('style');
		}
		
		return valid;
	};
	
	$.validateField = function(){
		var name = $("#name");
		
		var valid = true;
		
		if (name.val() == null || name.val() == "") {
			name.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			name.removeAttr('style');
		}
		
		return valid;
	};
	
	$.validateTpService = function(){
		var name = $("#name");
		
		var valid = true;
		
		if (name.val() == null || name.val() == "") {
			name.css({
				'border-color' : 'red'
			});
			valid = false;
			
		} else {
			name.removeAttr('style');
		}
		
		return valid;
	};
	
})(jQuery);