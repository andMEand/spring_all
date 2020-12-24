<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "com.spring.memberboard2.member.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<form name="updateForm" action="updateMember.bo"method ="post" >
<table border=1 width=300>
	<tr> <h2>회원정보 수정</h2></tr>
	<tr align=center><td>아이디 : </td><td><input type="text" name="id"></td></tr>
	<tr align=center><td>비밀번호 : </td><td>${memberVO.getPassword()}</td></tr>
	<tr align=center><td>이름 : </td><td>${memberVO.getName()}</td></tr>
	<tr align=center><td>나이 : </td><td>${memberVO.getAge()}</td></tr>
	<tr align=center><td>성별 : </td><td>${memberVO.getGender()}</td></tr>
	<tr align=center><td>이메일 주소 : </td><td>${memberVO.getEmail()}</td></tr>
	<tr align=center>
		<td colspan=2><a href="memberlist.me">리스트로 돌아가기</a></td>
	</tr>
</table>
</form>
</center>
</body>
</html>