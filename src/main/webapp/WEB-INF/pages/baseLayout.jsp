<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title>Material Lookup</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/startmin.css" rel="stylesheet">
<link href="css/Nlyte-css.css" rel="stylesheet">

<%-- <link href="<%=request.getContextPath()%>/css/bootstarp.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- Custom Fonts -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,300,700,600,800,400" rel="stylesheet" type="text/css">
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	
	<%
 	boolean flag = false;
	if(session!=null){
 	flag=true;
 	
	}
if(flag) {	
 %><div id="wrapper"> 
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	 <%}
	else{%>
		<jsp:forward page="nlyteLogin"></jsp:forward>
	<%}%> 
 	<script src="js/startmin.js"></script> 
 	<script src="js/metisMenu.min.js"></script> 
	
</body>
</html>
