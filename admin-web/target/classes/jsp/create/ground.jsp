<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	validateGround = function() {
		return true;
	}
</script>
<div style="background-color:#000; margin-top:-50px; height:100%">
    <div id="main">
      <div id="site_content" style="margin-left: 22px; margin-right: 22px; margin-top: 22px; position: absolute; width: 515px;">
        <div id="content">
	        <form action="/admin-web/admin/grounds" id="ground" onsubmit="return validateGround();" method="post" style="width: 75%;">
	            <div class="form_settings" style="color:#FFF">
	              <p><span style="width: 140px;">Suelo</span><input type="text" id="name" name="name" value="" /></p>
	              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="submit" value="crear" style="height: 40px;" /></p>
	            </div>
	        </form>
      	</div>
      </div>
      </div>
    </div>
