<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ page import="com.spring.testaaa.AaaVO" %>
    <%
    	AaaVO avo = (AaaVO)request.getAttribute("avo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
	<td>번호</td>
	<td >&nbsp; ${avo.no }</td>
	</tr>
	
	<tr>
	<td>이름</td>
	<td >&nbsp; ${avo.name }</td>
	</tr>
	
	<tr>
	<td>나이</td>
	<td >&nbsp; ${avo.age }</td>
	</tr>
	
	<tr>
	<td>주소</td>
	<td >&nbsp; ${avo.addr}</td>
	</tr>
	
	<tr>
	<td>취미</td>
	<td >&nbsp; ${avo.hooby}</td>
	</tr>
	
	
	
</table>
</body>
</html>