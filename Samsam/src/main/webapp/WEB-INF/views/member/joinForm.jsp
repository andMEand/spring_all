<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!doctype html>
<html>

<head>

<meta charset="utf-8" />
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
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
var pw = $('#pw').val();
var confirmPw = $('#pw2').val();
function isSame(){
  
  console.log($('#pw').val())
  if($('#pw').val().length <3 || $('#pw').val().length >16){
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
  
  
    function num_check(str) {
      var i;
      for (i = 0; i < str.length; i++) {
        if ((str.substring(i, i + 1) < "0" || str.substring(i, i + 1) > "9")) {
          return false;
        }
      }
      return true;
    }


    function check_input() {
      var str, i, ch, chk, chk_list, email= "";
      //아이디 체크 ---->
      if ($('[name="email"]') == "") {
        alert("아이디를 입력하세요");
        document.input_form.email.focus();
        return false;
      }
      else {
        str = $('[name="email"]').val();
        if (str.length < 6 || str.length > 10) {
          alert("아이디 길이 확인(6~10자리)!!!");
          $('[name="email"]').focus();
          return false;
        }
        else{
        	email =$('[name="email"]').val();
        	email += '@';
        	email += $('[name="email2"]').val();
        	console.log("아이디"+email)
        }

      } // 아이디 체크 <---
      
      
      // 패스워드 체크 ---->
      if (document.input_form.pw.value == "") {
        alert("패스워드를 입력하세요!!!");
        document.input_form.pw.focus();
        return false;
      }
      else {
        str = document.input_form.pw.value;
        if (str.length < 3 || str.length > 16) {
          alert("패스워드 길이 확인(6~16자리)!!!");
          document.input_form.pw.focus();
          return false;
        }

      }
      if (document.input_form.pw.value != document.input_form.pw2.value) {
        alert("패스워드 입력 오류!!!")
        document.input_form.pw2.focus();
        return false;
      }//패스워드 체크 <----
      chk_list += "pw=" + str + "\n"


      // 전화번호 체크 --->
      if (document.input_form.tel1.value.length == 3) {
        if (!num_check(document.input_form.tel1.value)) {
          alert("전화번호 앞3자리 입력 오류 !!!");
          document.input_form.tel1.focus();
          return false;
        }
      }
      else {
        alert("전화번호 앞3자리 입력하세요!!!");
        document.input_form.tel1.focus();
        return false;
      }
      if (document.input_form.tel2.value.length == 4) {
        if (!num_check(document.input_form.tel2.value)) {
          alert("전화번호 중간4자리 입력 오류 !!!");
          document.input_form.tel2.focus();
          return false;
        }
      }
      else {
        alert("전화번호 중간4자리 입력하세요!!!");
        document.input_form.tel2.focus();
        return false;
      }

      if (document.input_form.tel3.value.length == 4) {
        if (!num_check(document.input_form.tel3.value)) {
          alert("전화번호 끝4자리 입력 오류 !!!");
          document.input_form.tel3.focus();
          return false;
        }
      }
      else {
        alert("전화번호 끝4자리 입력하세요!!!");
        document.input_form.tel3.focus();
        return false;
      }

      var phone =  $('[name="tel1"]');
      phone +=  $('[name="tel2"]');
      phone +=$('[name="tel3"]');
      

      //이메일(앞부분) 체크 ---->
      if ($('[name="email1"]')== "") {
        alert("이메일(앞부분)을 입력하세요");
        document.input_form.email1.focus();
        return false;
      }//이메일(앞) 체크 <---
      //이메일(뒤) 체크 ---->
      if ($('[name="tel2"]') == "") {
        alert("이메일(뒷부분)을 입력하세요");
        document.input_form.email2.focus();
        return false;
      }//이메일(뒤) 체크 <---
      chk_list += "이메일 =" +$('[name="email1"]') + "@"
        +$('[name="email2"]');

     input_form.submit(); // 서버로 전송
    }
  </script>
<style>
table {
	border: 1 solid black;
}
</style>
<title>회원가입</title>
</head>

<body>
	<form name="input_form" action="joinprocess.me" method="post"
		onsubmit="return check_input();">
		<div align="center">
			<h1>회원가입</h1>
		</div>

		<table align="center" border="1 solid black">
		<tr>
			<td>이용 선택</td>
			<td><input type="radio" name="grade"value="일반 회원">일반 회원<input type="radio" name="grade"value="사업자 회원">사업자 회원</td>
		</tr>
			<tr>
				<td width="110">이메일</td>
				<td width="400"><input type="text" name="email" size="25">
				<button type="submit" name="submit" onclick="email_confirm();">인증하기</button></td>
			</tr>

			<tr>
				<td width="110">이름</td>
				<td width="400"><input type="text" name="name" size="30"
					placeholder="이름을 입력하세요"></td>
			</tr>
			<tr>
				<td width="110">닉네임</td>
				<td width="400"><input type="text" name="nick" size="30"
					placeholder="닉네임을 입력하세요"></td>
			</tr>
			<tr>
				<td width="110">비밀번호</td>
				<td width="400"><input type="password" id="pw" name="pw"
					size="30" onchange="isSame();" style="ime-mode: inactive"
					placeholder="최소6~최대16글자 이내 설정"></td>
			</tr>
			<tr>
				<td width="110">비밀번호 확인</td>
				<td width="400"><input type="password" id="pw2" name="pw2"
					size="30" onchange="isSame();" style="ime-mode: inactive"
					placeholder="최소6~최대16글자 이내 설정"> &nbsp;&nbsp;<span id="same"></span>
				</td>
			</tr>




			<tr>
				<td>휴대폰 번호</td>
				<td><input type="number" class="tel_size" name="tel1" size="3"
					maxlength="3"><span>-</span> 
					<input type="number"class="tel_size" name="tel2" size="4" maxlength="4"><span>-</span>
					<input type="number" class="tel_size" name="tel3" size="4"
					maxlength="4"></td>
			</tr>

			<tr>
				<td width="110">이용지역</td>
				<td width="400"><select name="local">
						<option value=""disabled selected hidden>지역선택
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
				</select></td>
			</tr>
			약관동의
			<td colspan="2" align="center" width="500">
			<input type="button"
				value=" 회원가입" onclick="check_input();">
				<input type="reset"
				value=" 다시작성"> 
				<input type="button" value="홈으로"
				src="home.me"> 
				<a href="home.me">홈으로 돌아가기</a></td>

		</table>

	</form>


</body>

</html>