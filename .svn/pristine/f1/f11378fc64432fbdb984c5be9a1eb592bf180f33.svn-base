(function($) {

	$.uploadImageForm = function(albumId) {

		_processImage = function(data) {
			var image = JSON.parse(data);
			
			$(".active").removeClass("active");
			
			$("#my_carousel_2").append('<div class="item active" style="height: 475px; text-align: left; width: 100%;">'
									+ '<div class="ts_border"> <img src="' + image.dir + '" style="width:' + image.width + 'px; margin-left:'
									+ Math.floor((1100 - image.width) / 2).toString() + 'px"/></div></div>');
			
			$("#overlay").dialog("close");
			
		};
		
		_handleError = function(jqXHR, textStatus, errorThrown) {
			alert("Imagen muy grande");
		};
		$("#overlay").dialog({
			modal : true,
			resizable : false,
			draggable : false,
			height : '50',
			width : '100',
			position : 'center'
		});
		
		$("#upload_form").ajaxForm({
			success :_processImage,
			error : _handleError,
			dataType : "text"
		}).submit();
		
	};

	$.uploadCover = function() {

		_handleError = function(jqXHR, textStatus, errorThrown) {
			alert("Imagen muy grande");
		};
		
		_processImage = function(data) {
			var image = JSON.parse(data);
			$("#cover").html('<img src="' + image.dir + '" onclick="$.openDialog(\'1100\', \'500\', \'/owner-web/owner/image/dialog/' + image.entity + '\')">');
		};
		
		$("#upload_cover_form").ajaxForm({
			success : _processImage,
			error : _handleError,
			dataType : "text"
		}).submit();
		
	};
	
	$.uploadImageAjax = function(album) {
	
		_processImage = function(data) {
			var image = JSON.parse(data);
			$("#" + album).html('<img src="' + image.dir + '" onclick="$.openDialog(\'1100\', \'500\', \'/owner-web/owner/image/dialog/' + image.entity + '\')">');

		};
		
		var oMyForm = new FormData();
		  oMyForm.append("file", $("#upload")[0].files[0]);
		 
		  $.ajax({
		    url: '/owner-web/owner/image/upload/' + album,
		    data: oMyForm,
		    dataType: 'text',
		    processData: false,
		    contentType: false,
		    type: 'POST',
		    success: _processImage
		  });
	};

	$.loadAlbumImagesDialog = function(albumId) {

		_processTemplate = function(response) {
			var data = null;
			$.template("stablishmentImages", response);
			$("#imagesDialog").html($.tmpl("stablishmentImages", data, {}));

			$("#imagesDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '500',
				width : '1100',
				position : 'center'
			});

			$('.ui-widget-overlay').on("click", function() {
				$("#imagesDialog").dialog("close");
				$("#imagesDialog").html('');
			});
			
		};

		$.ajax({
			url : '/owner-web/owner/image/dialog/' + albumId,
			type : "GET",
			success : _processTemplate
		});

	};

	$.loadMapEditDialog = function(mapMarkerId, entity) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("mapEdit", response);
			$("#mapEditDialog").html($.tmpl("mapEdit", data, {}));
			
			$("#mapEditDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '500',
				width : '1100',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#mapEditDialog").dialog("close");
				window.location.replace("/owner-web/owner/stablishment");
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/map/' + mapMarkerId + '?entity=' + entity,
			type : "GET",
			success : _processTemplate
		});
		
	};
	
	$.saveStablishmentMap = function(marker, mapMarkerId, entity) {
	
		$.ajax({
			url : '/owner-web/owner/map/?lat=' + marker.position.lat() + '&lng=' + marker.position.lng() + '&mmId=' + mapMarkerId + '&entity=' + entity,
			type : "POST",
			error : function(){alert('ocurrio un error al colocar marcador');}
		});
		
	};
	
	$.loadFieldUploadDialog = function() {

		_processTemplate = function(response) {
			var data = null;
			$.template("newField", response);
			$("#newFieldDialog").html($.tmpl("newField", data, {}));

			$("#newFieldDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '500',
				width : '1100',
				position : 'center'
			});

			$('.ui-widget-overlay').on("click", function() {
				$("#newFieldDialog").dialog("close");
				$("#newFieldDialog").html('');
			});
			
		};

		$.ajax({
			url : '/owner-web/owner/fields/new',
			type : "GET",
			success : _processTemplate
		});

	};

	$.loadTpServiceUploadDialog = function() {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("newTpService", response);
			$("#newTpServiceDialog").html($.tmpl("newTpService", data, {}));
			
			$("#newTpServiceDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '265',
				width : '1100',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#newTpServiceDialog").dialog("close");
				$("#newTpServiceDialog").html('');
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/services/new',
			type : "GET",
			success : _processTemplate
		});
		
	};
	
	$.loadConfirmationServiceDeleteDialog = function(id, name) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("confirmation", response);
			$("#confirmationDialog").html($.tmpl("confirmation", data, {}));
			
			$("#confirmationDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '265',
				width : '500',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#confirmationDialog").dialog("close");
				$("#confirmationDialog").html('');
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/services/delete?id=' + id + '&name=' + name,
			type : "GET",
			success : _processTemplate
		});
	};

	$.loadConfirmationFieldDeleteDialog = function(id, name) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("confirmation", response);
			$("#confirmationDialog").html($.tmpl("confirmation", data, {}));
			
			$("#confirmationDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '265',
				width : '500',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#confirmationDialog").dialog("close");
				$("#confirmationDialog").html('');
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/fields/delete?id=' + id + '&name=' + name,
			type : "GET",
			success : _processTemplate
		});
	};

	$.loadEditTpServiceDialog = function(id) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("edit", response);
			$("#editDialog").html($.tmpl("edit", data, {}));
			
			$("#editDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '265',
				width : '1100',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#editDialog").dialog("close");
				$("#editDialog").html('');
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/services/edit?id=' + id,
			type : "GET",
			success : _processTemplate
		});
	};

	$.loadEditFieldDialog = function(id) {
		
		_processTemplate = function(response) {
			var data = null;
			$.template("edit", response);
			$("#editDialog").html($.tmpl("edit", data, {}));
			
			$("#editDialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '500',
				width : '1100',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#editDialog").dialog("close");
				$("#editDialog").html('');
			});
			
		};
		
		$.ajax({
			url : '/owner-web/owner/fields/edit?id=' + id,
			type : "GET",
			success : _processTemplate
		});
	};
	
	$.createNewEvent = function() {
		_processTemplate = function(response) {
			var data = null;
			$.template("newEvent", response);
			$("#new-event-dialog").html($.tmpl("newEvent", data, {}));
			
			$("#new-event-dialog").dialog({
				modal : true,
				resizable : false,
				draggable : false,
				height : '270',
				width : '600',
				position : 'center'
			});
			
			$('.ui-widget-overlay').on("click", function() {
				$("#new-event-dialog").dialog("close");
				$("#new-event-dialog").html('');
			});
			
			
		};
		
		$.ajax({
			url : '/owner-web/owner/calendar/new',
			type : "GET",
			success : _processTemplate
		});
		
	};

})(jQuery);