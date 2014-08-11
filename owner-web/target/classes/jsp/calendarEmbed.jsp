<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Read-only events</title>
</head>
	<script src="/owner-web/calendar2/codebase/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
	<script src="/owner-web/calendar2/codebase/ext/dhtmlxscheduler_year_view.js" type="text/javascript" charset="utf-8"></script>
	<script src="/owner-web/calendar2/codebase/datastore.js" type="text/javascript"></script>
	<script src="/owner-web/calendar2/codebase/ext/dhtmlxscheduler_readonly.js" type="text/javascript" charset="utf-8"></script>
	<script src="/owner-web/calendar2/codebase/ext/dhtmlxscheduler_minical.js" type="text/javascript" charset="utf-8"></script>	
	<script src="/owner-web/calendar2/codebase/locale/locale_es.js" charset="utf-8"></script>
	<script src="/owner-web/js/jquery-1.9.1.js" type="text/javascript" ></script>
	<link rel="stylesheet" href="/owner-web/calendar2/codebase/dhtmlxscheduler.css" type="text/css" title="no title" >
	
<style type="text/css" media="screen">
	html, body{
		margin:0px;
		padding:0px;
		height:100%;
		overflow:hidden;
	}	
	/* Important !!! */
	.dhx_scale_hour{ 
		line-height:normal;
	}
</style>

<script type="text/javascript" charset="utf-8">
	var NO_PREPAYMENT_YET = 0 ;
	var CANCELED_PREPAYMENT = 1;
	var NO_PLAYED_YET = 2;
	var NO_RATED_YET = 3;
	var RATED = 4;
	var CANCELED_PLAYER = 5;
	var CANCELED_OWNER = 6;

	function init() {
		
		var data = new dhtmlXDataStore({
			url:"/owner-web/owner/calendar/events/" + $("#status").val() + "?entity=" + $("#entity").val(),
			scheme:{
				$init:function(obj){
					if (typeof obj.start_date == "string"){
						obj.start_date = scheduler.templates.xml_date(obj.start_date);
						obj.end_date = scheduler.templates.xml_date(obj.end_date);
					}
				}
			}
		});
		
		
		var step = 30;
		var format = scheduler.date.date_to_str("%H:%i");
		scheduler.config.hour_size_px = (60 / step) * 22;
		scheduler.templates.hour_scale = function(date){
			html="";
			for (var i = 0; i < 60 / step; i++){
				html += "<div style='height:22px;line-height:22px;'>" + format(date) + "</div>";
				date = scheduler.date.add(date, step, "minute");
			}
			return html;
		};
		
		scheduler.locale.labels.year_tab = "Año";
		scheduler.locale.labels.month_tab = "Mes";
		scheduler.locale.labels.week_tab = "Semana";
		scheduler.locale.labels.day_tab = "Día";
		scheduler.locale.labels["dhx_cal_today_button"] = "Hoy";
		
		scheduler.config.max_month_events = 5;
		scheduler.config.xml_date="%d/%m/%Y %H:%i";
		scheduler.config.api_date="%d/%m/%Y %H:%i";
		scheduler.config.details_on_dblclick = true;
		scheduler.init('scheduler_here', new Date(), "week");
		
	    scheduler.templates.event_text = function(start, end, event){
	        return "<b>" + event.entityName + "</b><br><i>" + event.text + "</i>";
	    };
				
		scheduler.templates.lightbox_header = function(start, end, event){
			return "<b>" + event.entityName +  " - " + start.getDate() + "/" + start.getMonth() + 1 + "/" 
						 + start.getFullYear() + " " + ((start.getHours() < 10) ? ("0" +  start.getHours()) : start.getHours()) 
						 + ":" + ((start.getMinutes()	< 10) ? ("0" +  start.getMinutes()) : start.getMinutes()) + "</b>" ;
		};
		
		scheduler.attachEvent("onBeforeLightbox", function(event_id) {
			scheduler.resetLightbox();
			scheduler.config.lightbox.sections = [	
			                        				{ name:"description", map_to:"text", type:"textarea", height:40},
			                          				{ name:"text", height:40, map_to:"details", type:"textarea"}
			                        			];
			
			scheduler.form_blocks.textarea.set_value = function(node, value, ev) {
				node.firstChild.value = value || "";
				node.firstChild.disabled = true; 
			};
			
			scheduler.config.buttons_left = [];
			scheduler.config.buttons_right = ["dhx_cancel_btn"];
			scheduler.locale.labels["dhx_cancel_btn"] = "Salir";
			scheduler.locale.labels.section_description = "Estado";
			scheduler.locale.labels.section_text= "Jugador";

			var ev = scheduler.getEvent(event_id);
			switch(ev.status) {
			case NO_PREPAYMENT_YET:
				scheduler.config.buttons_left = ["dhx_custom_btn_prepayment", "dhx_custom_btn_cancel"];
				scheduler.locale.labels["dhx_custom_btn_prepayment"] = "Pagó seña";
				scheduler.locale.labels["dhx_custom_btn_cancel"] = "Cancelar Reserva";
				break;
			case NO_RATED_YET: 
				scheduler.config.buttons_left = ["dhx_custom_btn_send_notification"];
				scheduler.locale.labels["dhx_custom_btn_send_notification"] = "Enviar notificación para que califique";
				break;
			case RATED:
			case NO_PLAYED_YET: 
			case CANCELED_PREPAYMENT: 
			case CANCELED_OWNER: 
			case CANCELED_PLAYER: 
				break;
			}
			return true;
		});
		
		scheduler.attachEvent("onLightboxButton", function(button_id, node, e){
			if(button_id == "dhx_custom_btn_cancel") {
				cancel(scheduler.getState().lightbox_id);
			} else if(button_id == "dhx_custom_btn_prepayment") {
				prepayment(scheduler.getState().lightbox_id);
			}
		});
		
		function block_readonly(id){
			return false;
		}
		
		scheduler.attachEvent("onBeforeDrag", block_readonly);
		scheduler.attachEvent("onClick", block_readonly);
		scheduler.sync(data);
		
	}
	
	function show_minical(){
		if (scheduler.isCalendarVisible())
			scheduler.destroyCalendar();
		else
			scheduler.renderCalendar({
				position:"dhx_minical_icon",
				date:scheduler._date,
				navigation:true,
				handler:function(date, calendar){
					scheduler.setCurrentView(date);
					scheduler.destroyCalendar();
				}
			});
	}
	function cancel(id) {
		var x = window.confirm("Seguro que desea cancelar la reserva?");
		if (x) {
			$.ajax({
				url : '/owner-web/owner/calendar/cancel?id=' + id,
				type : "GET",
				success : (function(){
					scheduler.endLightbox(false, scheduler.getLightbox());
					init();
				})
			});
		}
	};
	function prepayment(id) {
		var x = window.confirm("Seguro pagó la seña?");
		if (x) {
			$.ajax({
				url : '/owner-web/owner/calendar/prepayment?id=' + id,
				type : "GET",
				success : (function(){
					scheduler.endLightbox(false, scheduler.getLightbox());
					init();
				})
			});
		}
	};
</script>

<body onload="init();">
	<input type="hidden" id="status" value="${status}">
	<input type="hidden" id="entity" value="${entity}">
	<div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_minical_icon" id="dhx_minical_icon" onclick="show_minical()" style="left:298px;">&nbsp;</div>
			<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
			<div class="dhx_cal_tab" name="year_tab" style="right:197px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>
</body>