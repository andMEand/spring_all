<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.project.samsam.member.MemberVO"%>
<%	
	MemberVO vo = (MemberVO) request.getAttribute("MemberVO"); 
%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 회원가입 페이지</title>
<script type="text/javascript">

$(document).ready(function(){
    $('.tel_size').keyup(function () {
      var charLimit = $(this).attr("maxlength");
      if (this.value.length >= charLimit) {
        console.log('길이' + this.value.length + '맥스'+ $(this).attr("maxlength"));
        $(this).next().next('.tel_size').focus();
        return false;
      }
    });
   
});
/* 전화번호 길이 끝*/
var pw = $('#pw').val();
var confirmPw = $('#pw2').val();
function isSame(){
  
  console.log($('#pw').val())
  if($('#pw').val().length <6 || $('#pw').val().length >16){
    $('#pw').val('');
    $('#same').html('');
    }
  console.log("pw:"+$('#pw').val()+ "pw2:"+$('#pw2').val())
  if($('#pw').val() !=''&& $('#pw2').val()!=''){
  if($('#pw').val() == $('#pw2').val()){
  $('#same').html('비밀번호가 일치합니다.');
  $('#same').css("color","blue");
  }
  else{
  $('#same').html('비밀번호가  일치하지 않습니다');
  $('#same').css("color","red");
  }
  }
  }
/* 비밀번호 확인 끝*/
 
 
 
 
 
 
 
</script>
</head>
<body>
<form name = "k_joinform" action = "kkoJoin.me" method ="post">
<input type="hidden" name = "grade" value =<%=vo.getGrade() %>>
<center>
<table border= 1>
	<tr>
		<td colspan="2" align=center>
			<b><font size=5>회원가입 페이지</font></b>
		</td>
	</tr>
	<tr><td>이메일 : </td><td><input type="text" name= "email" readonly value=<%=vo.getEmail() %> ></td></tr>
	<tr><td>비밀번호 : </td><td><input type="password" name= "pw"/></td></tr>
	<tr><td>비밀번호 확인: </td><td><input type="password" name= "pw2"/></td></tr>
	<tr><td>이름 : </td><td><input type="text" name= "name" /></td></tr>
	<tr><td>닉네임 : </td><td><input type="text" name= "nick" value=<%=vo.getNick() %>></td></tr>
	<tr>
		<td>전화번호 : </td>
		<td>
			<input type="text" name ="phone">
		</td>
	</tr>
	<tr><td>이용지역 : </td><td><select name="local">
						<option value="">지역선택
						<option value="서울">서울
						<option value="경기">경기
						<option value="강원">강원
						<option value="경남">경남
						<option value="경북">경북
						<option value="광주">광주
						<option value="대구">대구
						<option value="대전">대전
						<option value="부산">부산
						<option value="세종">세종
						<option value="울산">울산
						<option value="인천">인천
						<option value="전남">전남
						<option value="전북">전북
						<option value="제주">제주
						<option value="충남">충남
						<option value="충북">충북
				</select></td></tr>
	
	<tr><td colspan="2" algin ="center">
		<a href="javascript:k_joinform.submit()">회원가입</a>&nbsp;&nbsp;
		<a href="javascript:k_joinform.reset()">다시작성</a>
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>