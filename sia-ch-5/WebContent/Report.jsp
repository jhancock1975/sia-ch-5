<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock Reports</title>
<script type="text/javascript" src="./js/jquery-1.10.1.js"></script>
<script type="text/javascript" src="./js/jquery.colorbox.js"></script>
<script type="text/javascript" src="./js/json-to-table.js"></script>
<script type="text/javascript" src="./js/jquery.number.js"></script>
<script type="text/javascript" src="./js/report.js"></script>
<script>
	var reportJson = '${report.report}';
</script>
<link rel="stylesheet" type="text/css" href="./css/report.css">
<link rel="shortcut icon" href="http://rootser.com/img/rootser-ico-orange.png"> 
</head>
<body>
	<div id="page-wrap">
		<img id="rootserLogo" src="http://rootser.com/img/banner.png" />
		<div id="msgid"></div>
	</div>
</body>
</html>