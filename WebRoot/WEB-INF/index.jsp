<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Integer long_acc = (Integer) request.getAttribute("long_acc");
	Integer lat_acc = (Integer) request.getAttribute("lat_acc");
	Integer long_time_acc = (Integer) request
			.getAttribute("long_time_acc");
	Integer lat_time_acc = (Integer) request
			.getAttribute("lat_time_acc");
	Integer time_acc = (Integer) request.getAttribute("time_acc");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>系统设置</title>
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
	width: 1500px;
	height: 500px;
	overflow: hidden;
	margin: 0, auto;
	text-align: center;
	font-family: "微软雅黑";
}
</style>

<script>
	jQuery(document).ready(
			function($) {

				$("#config").click(
						function() {
							var long_acc = $("#long_acc").val();
							var lat_acc = $("#lat_acc").val();
							var long_time_acc = $("#long_time_acc").val();
							var lat_time_acc = $("#lat_time_acc").val();
							var time_acc = $("#time_acc").val();

							document.uploadForm.action = "index?long_acc="
									+ long_acc + "&&lat_acc=" + lat_acc
									+ "&&long_time_acc=" + long_time_acc
									+ "&&lat_time_acc=" + lat_time_acc
									+ "&&time_acc=" + time_acc;

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
							class="hvr-bounce-to-bottom">轨迹展示</a></li>
						<li><a href="<%=basePath%>like" class="hvr-bounce-to-bottom">轨迹相似度计算</a></li>
						<li><a href="<%=basePath%>query" class="hvr-bounce-to-bottom">相似轨迹查询</a></li>
						<li><a href="<%=basePath%>index" class="active hvr-bounce-to-bottom">系统设置</a></li>

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

	<!-- script-for-menu -->
	<!--start-banner-->

	<form id="form1" class="formheight-set" name="uploadForm" action="" method="post"
		enctype="multipart/form-data">
		<div class="banner bset">
			<div class="container">
				<div class="banner-top-set">
					<h1>设置（0~100→高精度匹配 1000及以上→低精度匹配）</h1>
					<div class="banner-bottom">
						<div class="bnr-one">
							<div class="bnr-left-set">
								<p>不带时间轨迹经度范围</p>
							</div>
							<div class="bnr-right-set" style="position:relative;">
								<input type="text" id="long_acc" value="<%=long_acc%>" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="bnr-one">
							<div class="bnr-left-set">
								<p>不带时间轨迹纬度范围</p>
							</div>
							<div class="bnr-right-set" style="position:relative;">
								<input type="text" id="lat_acc" value="<%=lat_acc%>" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
						<div class="bnr-one">
							<div class="bnr-left-set">
								<p>带时间轨迹经度范围</p>
							</div>
							<div class="bnr-right-set" style="position:relative;">
								<input type="text" id="long_time_acc"
									value="<%=long_time_acc%>" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
						<div class="bnr-one">
							<div class="bnr-left-set">
								<p>带时间轨迹纬度范围</p>
							</div>
							<div class="bnr-right-set" style="position:relative;">
								<input type="text" id="lat_time_acc" value="<%=lat_time_acc%>" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
						<div class="bnr-one">
							<div class="bnr-left-set">
								<p>带时间轨迹时间范围</p>
							</div>
							<div class="bnr-right-set" style="position:relative;">
								<input type="text" id="time_acc" value="<%=time_acc%>" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>

					<div class="bnr-btn-set">

						<input id="config" type="button" class="btn btn-primary" value="设置">

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
				<h5>源代码请点击→<a href="https://github.com/suxiongye/PathDis">Github</a>,欢迎指导</h5>
				
			</div>
		</div>
		
	</div>

	<!--end-footer-->
</body>
</html>

