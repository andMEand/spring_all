<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.project.samsam.board.*" %>
<%
	BoardVO board = (BoardVO)request.getAttribute("bvo");
%>
<style>
	.searchView {
	margin 0 auto;
	}
</style>

<html>
<head>
	<title>MVC 게시판</title>
</head>

<body>
<!-- 검색 목록 글보기 -->

<table class="searchView" cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-size:12">
		<%=board.getSubject()%>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style=" font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-size:12">
					<%=board.getC_content() %>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-size:12">
			<div align="center">첨부파일</div>
		</td>
		<td>
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="./boardreplyform.bo?num=<%=board.getNum() %>">
			[답변]
			</a>&nbsp;&nbsp;
			<a href="./boardmodifyform.bo?num=<%=board.getNum() %>">
			[수정]
			</a>&nbsp;&nbsp;
			
			
			
			<a href="./home.me"
			>[Home]
			</a>&nbsp;&nbsp;
			<a href="javascript:history.back()">[pre]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
	
	<tr>
		<td>
</table>
<!-- 게시판 수정 -->
<br/>


</body>
</html>