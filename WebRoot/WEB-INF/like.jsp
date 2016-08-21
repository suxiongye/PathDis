<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String path1 = (String) request.getAttribute("nodes1");
	String path2 = (String) request.getAttribute("nodes2");
	String tracks = (String) request.getAttribute("tracks");
	String similar = (String) request.getAttribute("similar");
	Integer type = (Integer) request.getAttribute("type");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>轨迹相似度计算</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script
	src="http://api.map.baidu.com/api?v=2.0&ak=MRUGgkRGpEtnZEVLUGpGlRNvcujriGlp"></script>
<script
	src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
<style type="text/css">
#allmap {
	
	height: 90%;
	overflow: hidden;
	margin: 0, auto;
	padding:0,10;
	text-align: center;
	font-family: "微软雅黑";
	-moz-border-radius: 30px; 
    -webkit-border-radius: 30px; 
    border-radius:30px;         
}
</style>
<script type="text/javascript">
	jQuery(document).ready(function($) {

		$("#like_path1").click(function(event) {
			$("#likepath1").click();
		});
		$("#likepath1").change(function(event) {

			$("#like_path1").val($("#likepath1").val());
		});
		$("#like_path2").click(function(event) {
			$("#likepath2").click();
		});
		$("#likepath2").change(function(event) {

			$("#like_path2").val($("#likepath2").val());
		});
		$('#upload').click(function() {
			var type = $("#type").val();
			document.uploadForm.action = "like?is_time=" + type;

			$('#form1').submit();
		});
	});
</script>
<!--start-smoth-scrolling-->
<script>
	$(document).ready(
			function() {
				var map = new BMap.Map("allmap");
				map.centerAndZoom(new BMap.Point(118.9, 31.9), 10);
				map.enableScrollWheelZoom();

				var path1 =
<%=path1%>
	;
				var path2 =
<%=path2%>
	;
				var tracks =
<%=tracks%>
	;

				var type =
<%=type%>
	;
				if (type == 0) {
					if (path1 != null && path2 != null && tracks != null) {
						map.centerAndZoom(new BMap.Point(path1[0].longitude,
								path1[0].latitude), 10);
						//画第一条路径
						var points1 = [];
						for (p in path1) {
							var point = new BMap.Point(path1[p].longitude,
									path1[p].latitude);
							points1.push(point);
						}
						var polyline = new BMap.Polyline(points1, {
							strokeColor : "red",
							strokeWeight : 3,
							strokeOpacity : 0.8
						}); //创建折线
						map.addOverlay(polyline);

						//画第二条路径
						var points2 = [];
						for (p in path2) {
							var point = new BMap.Point(path2[p].longitude,
									path2[p].latitude);
							points2.push(point);
						}
						var polyline2 = new BMap.Polyline(points2, {
							strokeColor : "blue",
							strokeWeight : 3,
							strokeOpacity : 0.8
						}); //创建折线
						map.addOverlay(polyline2);

						//画相似路径段
						var points3 = [];
						var temp_id = 0;
						for (p in tracks) {
							var point = new BMap.Point(tracks[p].longitude,
									tracks[p].latitude);
							var marker = new BMap.Marker(point);
							map.addOverlay(marker);
							//如果路径的id是连续的，则保存
							if (tracks[p].id == temp_id + 1) {
								points3.push(point);
								temp_id = tracks[p].id;
							}
							//否则绘制并清空数组
							else {
								if (points3.length > 1) {
									var polyline3 = new BMap.Polyline(points3,
											{
												strokeColor : "black",
												strokeWeight : 3,
												strokeOpacity : 0.8
											}); //创建折线
									map.addOverlay(polyline3);
								}
								points3 = [];
								temp_id = tracks[p].id;
								points3.push(point);
							}

						}
						//补画最后一段
						if (points3.length > 1) {
							var polyline3 = new BMap.Polyline(points3, {
								strokeColor : "black",
								strokeWeight : 3,
								strokeOpacity : 0.8
							}); //创建折线
							map.addOverlay(polyline3);
						}
						//console.log(tracks);
					}
				} else {
					if (path1 != null && path2 != null && tracks != null) {
						//绘制第一条路径
						var points1 = [];
						for (p in path1) {
							var point = new BMap.Point(path1[p].longitude,
									path1[p].latitude);
							points1.push(point);
						}
						var polyline = new BMap.Polyline(points1, {
							strokeColor : "red",
							strokeWeight : 2,
							strokeOpacity : 0.5
						}); //创建折线
						map.addOverlay(polyline);

						var points2 = [];
						for (p in path2) {
							var point = new BMap.Point(path2[p].longitude,
									path2[p].latitude);
							points2.push(point);
						}
						var polyline2 = new BMap.Polyline(points2, {
							strokeColor : "blue",
							strokeWeight : 2,
							strokeOpacity : 0.5
						}); //创建折线
						map.addOverlay(polyline2);

						//console.log(tracks);
						//画相似路径段
						var points3 = [];
						var temp_id = 0;
						for (p in tracks) {
							var point = new BMap.Point(tracks[p].longitude,
									tracks[p].latitude);
							var marker = new BMap.Marker(point);
							map.addOverlay(marker);
							var label = new BMap.Label(tracks[p].timeStr, {
								offset : new BMap.Size(20, -10)
							});
							marker.setLabel(label);
							//如果路径的id是连续的，则保存
							if (tracks[p].id == temp_id + 1) {
								points3.push(point);
								temp_id = tracks[p].id;
							}
							//否则绘制并清空数组
							else {
								if (points3.length > 1) {
									var polyline3 = new BMap.Polyline(points3,
											{
												strokeColor : "black",
												strokeWeight : 3,
												strokeOpacity : 0.8
											}); //创建折线
									map.addOverlay(polyline3);
								}
								points3 = [];
								temp_id = tracks[p].id;
								points3.push(point);
							}
						}
						//补画最后一段
						if (points3.length > 1) {
							var polyline3 = new BMap.Polyline(points3, {
								strokeColor : "black",
								strokeWeight : 3,
								strokeOpacity : 0.8
							}); //创建折线
							map.addOverlay(polyline3);
						}
					}
				}
			});
</script>
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
						<li><a href="<%=basePath%>show" class="hvr-bounce-to-bottom">轨迹展示</a></li>
						<li><a href="<%=basePath%>like"
							class="active hvr-bounce-to-bottom">轨迹相似度计算</a></li>
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
	<!-- script-for-menu -->
	<!--start-banner-->
	<form id="form1" class="formheight" name="uploadForm" action="" method="post"
		enctype="multipart/form-data">
		<div class="banner blike">
			<div class="container">
				<div class="banner-top">
					<h1>输入轨迹</h1>
					<div class="banner-bottom">
						<div class="bnr-one">
							<div class="bnr-left">
								<p>上传轨迹一</p>
							</div>
							<div class="bnr-right" style="position:relative;">
								<input class="date" type="file" name="file1" id="likepath1"
									value="" style="display:none" /> <input class="date"
									type="file" name="file2" id="likepath2" value=""
									style="display:none" /> <input class="date" type="text"
									id="like_path1" />
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="bnr-one">
							<div class="bnr-left">
								<p>上传轨迹二</p>
							</div>
							<div class="bnr-right" style="position:relative;">


								<input class="date" type="text" id="like_path2" />
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


	<!--FlexSlider-->

	<script defer src="js/jquery.flexslider.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				start : function(slider) {
					$('body').removeClass('loading');
				}
			});
		});
	</script>

	<!--End-slider-script-->
	<!--start-blog-->
	<div class="news" id="news">
		<div class="">
			<div class="news-top heading">
				<%
					if (similar == null) {
				%>
				<h3>相似度:0 %</h3>
				<%
					} else {
				%>
				<h3>
					相似度:<%=similar%>
					%
				</h3>
				<%
					}
				%>
			</div>
			<div>
				<center>
					<div id="allmap"></div>
				</center>
			</div>

		</div>
	</div>
	<!--end-blog-->

	<!--end-events-->
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

