<%@page import="java.util.ArrayList"%>
<%@page import="bean.PathHistory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	ArrayList<PathHistory> pathHistories = (ArrayList<PathHistory>)request.getAttribute("histories");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>历史记录</title>
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
#resultdiv{
	height:450px;
  	overflow-y:scroll;
}
</style>

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
						<li><a href="<%=basePath%>show" class="hvr-bounce-to-bottom">轨迹展示</a></li>
						<li><a href="<%=basePath%>like" class="hvr-bounce-to-bottom">轨迹相似度计算</a></li>
						<li><a href="<%=basePath%>query" class="hvr-bounce-to-bottom">相似轨迹查询</a></li>
						<li><a href="<%=basePath%>index" class="hvr-bounce-to-bottom">系统设置</a></li>
						<li><a href="<%=basePath%>history"
							class="active hvr-bounce-to-bottom">历史记录</a></li>

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

	<form id="form1" class="formheight-set" name="uploadForm" action=""
		method="post" enctype="multipart/form-data">
		<div class="banner bset">
			<div class="container">
				<div class="banner-top-set">
					<div id="resultdiv" >
					<h1 style="text-align:center;">上传路径历史记录</h1>
					<br><br>
						<table class="table">
							<thead>
								<tr>
									<th class="th3">#</th>
									<th class="th3">路径名</th>
									<th class="th3">时间</th>
								</tr>
							</thead>

							<%
								for(PathHistory pathHistory:pathHistories){
							%>
							<tr>
								<td><%=pathHistory.getId()%></td>
								<td><%=pathHistory.getPathName()%></td>
								<td><%=pathHistory.getTime()%></td>
							</tr>
							<%
								}
							%>
						</table>
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
				
				$("tr:odd").addClass("info");
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
				<h5>
					源代码请点击→<a href="https://github.com/suxiongye/PathDis">Github</a>,欢迎指导
				</h5>

			</div>
		</div>

	</div>

	<!--end-footer-->
</body>
</html>

