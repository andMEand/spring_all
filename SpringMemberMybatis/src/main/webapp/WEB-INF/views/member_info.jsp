<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="com.spring.mybatis.*" %>


<%
 String id = null;
 if ((session.getAttribute("id") == null) ||
  (!((String)session.getAttribute("id")).equals("admin"))) {
  out.println("<script>");
  out.println("location.href='./loginform.me'");
  out.println("</script>");
 }
 
 
%>   
<!DOCTYPE html>
<html>
<head>
<title>회원관리 시스템 관리자모드( 회원정보 보기)</title>
</head>
<body>
<table border = 1 width =300 align=center>
  <tr align =center><td>아이디 : </td><td>${member.getId() }</td></tr>
  <tr align =center><td>비밀번호 : </td><td> ${member.getPassword() } </td></tr>
  <tr align =center><td>이름 : </td><td>${member.getName() }</td></tr>
  <tr align =center><td>나이 : </td><td>${member.getAge() }</td></tr>
  <tr align =center><td>성별 : </td><td>${member.getGender() }</td></tr>
  <tr align =center><td>이메일 : </td><td>${member.getEmail() }</td></tr>
  <tr align=center>
    <td colspan=2><a href="memberlist.me">리스트로 돌아가기</a></td>
  </tr>
</table>
</body>
</html>