<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!-- 없어도 된다  -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
    
    <%@ page import="java.util.*" %>
    <%
    request.setCharacterEncoding("utf-8");
    ArrayList<String> orgfile_list = (ArrayList<String>)request.getAttribute("orgfile_list");
    ArrayList<String> storedfile_list = (ArrayList<String>)request.getAttribute("storedfile_list");
    ArrayList<Long> filesize_list = (ArrayList<Long>)request.getAttribute("filesize_list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body> 
<h1>업로드 완료</h1>
 이름 : ${name }<br /><br/>
 <%
 for(int i=0;i<orgfile_list.size(); i++){
	 String downlink ="fileDownload.bo?of=" + storedfile_list.get(i)+"&of2=" + orgfile_list.get(i);// 파라메터 2개 전달
	 String storedfile = storedfile_list.get(i);
	 
 %>
 파일명 : <%=orgfile_list.get(i) %><br/>
 파일 사이즈 : <%=filesize_list.get(i) %><br/>
 <a href=<%=downlink %>>다운로드</a><br/>
 <img src="/springfileupload1/upload/<%=storedfile_list.get(i) %>"/> <!-- 외부에 있는 업로드 파일을 사용하기 위해 server.xml의 path경로를 기술하는것! -->
 <br /><br />
 <%
 }
 %>
 
 
</body>
</html>