<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<% request.setCharacterEncoding("utf-8"); %>
<title>Insert title here</title>
</head>
<body>
id: <%= request.getAttribute("id") %><br><br>
pw: <%= request.getAttribute("pw") %><br><br>
id: <%= request.getAttribute("jumin1") %><br><br>
id: <%= request.getAttribute("jumin2") %><br><br>
id: <%= request.getAttribute("gender") %><br><br>
id: <%= request.getAttribute("tel1") %><br><br>
id: <%= request.getAttribute("tel2") %><br><br>
id: <%= request.getAttribute("tel3") %><br><br>
id: <%= request.getAttribute("hobby") %><br><br>
id: <%= request.getAttribute("intro") %><br><br>

</body>
</html>