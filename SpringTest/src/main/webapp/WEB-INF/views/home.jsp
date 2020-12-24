<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P>  The time on the server is <%=request.getAttribute("serverTime") %>. </P>
<P>  The time on the server is ${val}. </P>
<P>  The time on the server is <%=request.getAttribute("val") %>. </P>
</body>
</html>
