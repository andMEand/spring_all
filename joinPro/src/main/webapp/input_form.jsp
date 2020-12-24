<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.spring.joinpro.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%
 request.setCharacterEncoding("utf-8");
MemberVO memberVO =(MemberVO)request.getAttribute("memberVO");

String hobby[] =memberVO.getHobby();
String hobby_str="";
int i;
for(i=0; i<hobby.length-1;i++){
	hobby_str += hobby[i] + ", ";}
	hobby_str += hobby[i];

%>
<table align="center" border="1"width="280">
	<tr>
		<td width="80">아이디</td>
		<td width="200"><%=memberVO.getId() %></td></tr>
		
	<tr>
  		<td > 비밀번호 </td>
  		<td ><%=memberVO.getPw() %></td></tr>
  		
  	<tr>
  		<td > 주민번호 </td>
  		<td ><%=memberVO.getJumin1() %>-<%=memberVO.getJumin2() %></td></tr>
  	
  	<tr>
  		<td > 성별 </td>
  		<td ><%=memberVO.getGender() %></td></tr>
  	
  	<tr>
  		<td > 성별 </td>
  		<td ><%=memberVO.getTel1() %>-<%=memberVO.getTel2() %>-<%=memberVO.getTel3() %></td></tr>
  	
  	<tr>
  		<td > 이메일 </td>
  		<td ><%=memberVO.getEmail() %>@<%=memberVO.getEmail2() %></td></tr>
  	
  	<tr>
  		<td > 취미 </td>
  		<td ><%=hobby_str %></td></tr>
  	<tr>
  		<td > 자기소개 </td>
  		<td ><%=memberVO.getIntro() %></td></tr>


</table>

</body>
</html>