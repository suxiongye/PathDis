<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>主页</title>
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	This is my JSP page.
	<br> 地图
</body>
</html>
