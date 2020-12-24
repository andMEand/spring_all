<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%
    ArrayList<String> list_text = (ArrayList<String>)request.getAttribute("list_text");
    ArrayList<String> list_link =(ArrayList<String>)request.getAttribute("list_link");
    ArrayList<String> list_pj =(ArrayList<String>)request.getAttribute("list_pj");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>네이버 영화 랭킹</title>
<style>
tr td a{
text-decoration:none; color:black;
}
tr td a:hover{
background-color:#F6EAE8;
}
.nav td { background-color:#gray;}

</style>
</head>
<body>
<br/>
<table class="nav" border="0" align="center">
	<tr form="5px" align="center">
	<td ><a href=<%="crawl.do" %>>조회순</a></td>
	<td><a href=<%="crawl1.do" %>>평점순(현재상영영화)</a></td>
	<td><a href=<%="crawl2.do" %>>평점순(모든영화)</a></td>
	
	</tr>
	<tr>
		<th>&nbsp;</th>
		
	</tr>
	<tr>
		<td> 순위</td>
		<td> 영화명</td>
		<td> 변동폭</td>
	</tr>
<%
for(int i=0; i<list_text.size();i++){
%>
	<tr>
		<td><%=i+1 %></td>
		<td><a href=<%=list_link.get(i).toString() %>><%=list_text.get(i).toString() %></a></td>
		<td colspan="1"><%=list_pj.get(i).toString()%></td>
		
	</tr>
	<%
}
	%>	
</table>

</body>
</html>