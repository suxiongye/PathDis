<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mapPath = (String) request.getAttribute("nodes");
	Integer type = (Integer) request.getAttribute("type");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>轨迹展示</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"></script>
<!---- start-smoth-scrolling---->
<script src="js/move-top.js"></script>
<script src="js/easing.js"></script>
<script
	src="http://api.map.baidu.com/api?v=2.0&ak=MRUGgkRGpEtnZEVLUGpGlRNvcujriGlp"></script>
<script
	src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
<style type="text/css">
#allmap {
	overflow: hidden;
	margin: 0, auto;
	text-align: center;
	font-family: "微软雅黑";
	-moz-border-radius: 30px;
	-webkit-border-radius: 30px;
	border-radius: 30px;
}
</style>

<script>
	jQuery(document).ready(function($) {

		$("#filepath1").click(function(event) {

			$("#path1").click();
		});
		$("#path1").change(function(event) {

			$("#filepath1").val($("#path1").val());
		});
		$("#upload").click(function() {
			var type = $("#type").val();
			document.uploadForm.action = "show?is_time=" + type;

			$('#form1').submit();
		});
	});
</script>
<!--start-smoth-scrolling-->
</head>
<body>
	<!--start-header-->
	<div class="header" id="home">
		<div class="container">
			<div class="head">
				<div class="logo">
					<a href="<%=basePath%>index"><img src="images/logo.png" alt="" /></a>
				</div>
				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="<%=basePath%>show"
							class="active hvr-bounce-to-bottom">轨迹展示</a></li>
						<li><a href="<%=basePath%>like" class="hvr-bounce-to-bottom">轨迹相似度计算</a></li>
						<li><a href="<%=basePath%>query" class="hvr-bounce-to-bottom">相似轨迹查询</a></li>
						<li><a href="<%=basePath%>index" class="hvr-bounce-to-bottom">系统设置</a></li>

					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- script-for-menu -->
	<!-- script-for-menu -->
	<script>
		$("span.menu").click(function() {
			$(" ul.navig").slideToggle("slow", function() {
			});
		});
	</script>
	<script>
		$(document).ready(
				function() {
					var map = new BMap.Map("allmap");
					map.centerAndZoom(new BMap.Point(118.9, 31.9), 10);
					map.enableScrollWheelZoom();

					var path =
	<%=mapPath%>
		;
					if (path == null) {
						return;
					}
					map.centerAndZoom(new BMap.Point(path[0].longitude,
							path[0].latitude), 10);
					var type =
	<%=type%>
		;
					if (type == 0) {
						if (path != null) {
							var points = [];
							for (p in path) {
								var point = new BMap.Point(path[p].longitude,
										path[p].latitude);
								points.push(point);
							}
							var polyline = new BMap.Polyline(points, {
								strokeColor : "red",
								strokeWeight : 3,
								strokeOpacity : 0.8
							}); //创建折线
							map.addOverlay(polyline);
						}
					}
					if (type == 1) {
						var points = [];
						for (p in path) {
							var point = new BMap.Point(path[p].longitude,
									path[p].latitude);
							var marker = new BMap.Marker(point);
							map.addOverlay(marker);
							var label = new BMap.Label(path[p].timeStr, {
								offset : new BMap.Size(20, -10)
							});
							marker.setLabel(label);
							points.push(point);
						}
						var polyline = new BMap.Polyline(points, {
							strokeColor : "blue",
							strokeWeight : 2,
							strokeOpacity : 0.8
						}); //创建折线
						map.addOverlay(polyline);
					}
				});
	</script>
	<!-- script-for-menu -->
	<!--start-banner-->

	<form id="form1" class="formheight" name="uploadForm" action=""
		method="post" enctype="multipart/form-data">
		<div class="banner bhome">
			<div class="container">
				<div class="banner-top">
					<h1>输入轨迹</h1>
					<div class="banner-bottom">
						<div class="bnr-one">
							<div class="bnr-left">
								<p>上传轨迹</p>
							</div>
							<div class="bnr-right" style="position:relative;">

								<input class="date" type="text" id="filepath1" /> <input
									class="date" type="file" name="file1" id="path1" value=""
									style="display:none" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="bnr-one">
						<div class="bnr-left">
							<p>轨迹类型</p>
						</div>
						<div class="bnr-right">
							<select name="type" id="type">
								<option value="0">不带时间轨迹</option>
								<option value="1">带时间轨迹</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="bnr-btn">
						<input id="upload" type="button" class="btn btn-default" value="上传">
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--end-banner-->

	<div>
		<!--FlexSlider-->

		<script defer src="js/jquery.flexslider.js"></script>
		<script>
			$(window).load(function() {
				$('.flexslider').flexslider({
					animation : "slide",
					start : function(slider) {
						$('body').removeClass('loading');
					}
				});
			});
		</script>
	</div>
	<!--End-slider-script-->
	<!--start-blog-->

	<div class="news" id="news">
		<div class="">
			<div class="news-top heading">

				<h3>轨迹展示</h3>

			</div>
			<div>
				<center>
					<div id="allmap" class="mapshow"></div>
				</center>
			</div>

		</div>
	</div>
	<!--end-blog-->

	<!--start-footer-->
	<div class="footer">
		<div class="container">
			<div class="touch-top heading">
				<h3>第五届中国软件杯</h3>
			</div>
			<div class="touch-bottom">
				<img src="images/logo-web.png" width="20%" alt="" />

			</div>
			<div class="footer-link">
				<h5>
					源代码请点击→<a href="https://github.com/suxiongye/PathDis">Github</a>,欢迎指导
				</h5>

			</div>
		</div>
	</div>

	<!--end-footer-->
</body>
</html>

