<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="com.spring.springform.DeptVO" %>
    <%
    DeptVO deptvo = (DeptVO)request.getAttribute("deptvo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<br>
<table align=center; border=1>
<tr>
	<td width="80">&nbsp; 부서번호</td>
	<!-- 
	<td width="120">&nbsp; ${deptvo['deptno']}</td>
	 -->
	<td width="120">&nbsp; ${deptvo.deptno}</td>
</tr>
<tr>
	<td >&nbsp; 부서명</td>
	<td >&nbsp; ${deptvo['dname']}</td>
	
</tr>
<tr>
	<td >&nbsp; 위치</td>
	<td >&nbsp; ${deptvo['loc']}</td>
</tr>
<tr align=center>
	<td >&nbsp; 위치</td>
	<td colsapn=2><a href="selectProcess.me" >사원정보</a></td>
</tr>


</table>
</body>
</html>