(function($) {

	$.openDialog = function(width, height, url) {
		_processTemplate = function(response) {
			var data = null;
			$.template(url, response);
			$("body").append("<div id='dialog'></div>");
			$("#dialog").html($.tmpl(url, data, {}));
			$("#dialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : height,
				width : width,
				position : 'center',
			});

			$('.ui-widget-overlay').on("click", function() {
				$("#dialog").dialog("close");
				$("#dialog").remove();
			});

		};

		$.ajax({
			url : url,
			type : "GET",
			success : _processTemplate
		});

	};

	$.openDialogFn = function(width, height, url, onopen) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template(url, response);
			$("body").append("<div id='dialog'></div>");
			$("#dialog").html($.tmpl(url, data, {}));
			$("#dialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : height,
				width : width,
				position : 'center',
				open : onopen
			});

			$('.ui-widget-overlay').on("click", function() {
				$("#dialog").dialog("close");
				$("#dialog").remove();
			});

		};

		$.ajax({
			url : url,
			type : "GET",
			success : _processTemplate
		});

	};
	
	$.replaceContent = function(url, id) {
		_processTemplate = function(response) {
			var data = null;
			$.template(url, response);
			$("#" + id).html($.tmpl(url, data, {}));
		};

		$.ajax({
			url : url,
			type : "GET",
			success : _processTemplate
		});

	};

})(jQuery);