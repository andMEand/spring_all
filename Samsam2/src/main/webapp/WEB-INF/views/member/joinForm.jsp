<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>

<head>

<meta charset="utf-8" />
<head profile="http://www.w3.org/2005/10/profile">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	<link href="resources/css/join_form.css"rel="stylesheet"/>
<script src="resources/js/login_form.js"></script>

<script type="text/javascript">


$(document).ready(function(){
	$('#emailbtn').on("click", function(){
		
		var check = {
				"check" : $('#email').val(),
		}
		console.log(check);

		$.ajax({
			url: '/samsam/idcheckajax.do',
			type: 'POST',
			data: JSON.stringify(check),
			contentType : 'application/json;charset=utf-8',
			dataType : 'json',

			success : function(checkres) {
				if(checkres == 1){  //이메일 중복
					$("#labelemail").html("dd");
				$("#email").empty();
				$("#email").on(focus);
					return false;
				}
			},//success
			error : function(){
				alert("통신실패");
			}
		});//ajax
	})
	//
    $('.tel_size').keyup(function () {
      var charLimit = $(this).attr("maxlength");
      if (this.value.length >= charLimit) {
        console.log('길이' + this.value.length + '맥스'+ $(this).attr("maxlength"));
        $(this).next().next('.tel_size').focus();
        
        return false;
      }
    });//전화번호 길이 끝
   
});  //

var pw = $('#pw').val();
var confirmPw = $('#pw2').val();
function isSame(){
  
  console.log($('#pw').val())
  if($('#pw').val().length <6 || $('#pw').val().length >16){
    $('#pw').val('');
    $('#pass').html('');
    }
  console.log("pw:"+$('#pw').val()+ "pw2:"+$('#pw2').val())
  if($('#pw').val() !=''&& $('#pw2').val()!=''){
  if($('#pw').val() == $('#pw2').val()){
  $('#pass').text('비밀번호가 일치합니다.');
  $('#pass').css("color","blue");
  }
  else{
  $('#pass').text('비밀번호가  일치하지 않습니다');
  $('#pass').css("color","red");
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
//

    function check_input() {
     if(document.input_form.name.value==""){
    	 alert("이메일을 입력하세요");
         document.input_form.email.focus();
         return false;
     }

      // 패스워드 체크 ---->
      if (document.input_form.pw.value == "") {
        alert("패스워드를 입력하세요!!!");
        document.input_form.pw.focus();
        return false;
      }
      else {
        str = document.input_form.pw.value;
        if (str.length < 6 || str.length > 16) {
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

      // 전화번호 체크 --->
      if (document.input_form.phone.value.length == 11) {
        if (!num_check(document.input_form.phone.value)) {
          alert("전화번호입력 오류 !!!");
          document.input_form.phone.focus();
          return false;
        }
      }
      else {
        alert("전화번호를 입력하세요!!!");
        document.input_form.phone.focus();
        return false;
      }
      
      //
      if(document.input_form.local.value == ""){
    	  alert("이용지역을 선택하세요.");
          document.input_form.local.focus();
          return false;
      }
      if(!$("input:checked[id=check]").is(":checked")){
    	  alert("동의 후 회원가입이 완료됩니다");
    	  return;
      }
     


     input_form.submit(); // 서버로 전송
    }
  </script>

<title>회원가입</title>
</head>

<body>

<div class="join-header">
<hgroup>
  <h1>SIGN UP</h1>
  <h3>smasmahagae</h3>
</hgroup>
</div>

<form name="input_form" action = "signUp.me" method="post" class="joinform" onsubmit="return check_input();" >

<div class="group">
  <div class="id">
	<input type="text"name="email" id="email"><span class="highlight"></span>
    <label for="email" class="label-email"id="labelemail"><span class="content-email">Email</span></label>
  <button type="button" class="idbtn" id="emailbtn" >중복확인</button>
  </div>
</div>
<div class="group">
	<input type="text"name="name" ><span class="highlight"></span><span class="bar"></span>
    <label for="name" class="label-email"><span class="content-email">이름</span></label>
</div>
<div class="group">
      <div class="nick">
	<input type="text"name="nick"><span class="highlight"></span>
    <label for="nick" class="label-email"><span class="content-email">닉네임</span></label>
      <button type="button" class="nickbtn" onclick="nickCheck();">중복확인</button>
</div>
  </div>
<div class="group">
	<input type="password" name="pw"><span class="highlight"></span><span class="bar"></span>
    <label class="pass">password</label>
</div>
<div class="group">
	<input type="password" name="pw2"><span class="highlight"></span><span class="bar"></span>
    <label class="pass">password 확인</label>
</div>

<div class="group">
	<input type="text" name="phone" id="phone"><span class="highlight"></span><span class="bar"></span>
    <label>휴대폰 번호</label>
</div>
<div class="group">
   <div class="local-group">
	 <span><h5>이용지역</h5></span>
	 <select  name="local" class="local">
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
	</select><span class="bar"></span>
  </div>
</div>

<div class="wrap centre">
	<div class="checkbox">
		<input id="check" type="checkbox" name="check" value="check">
		<label for="check">i agree.</label>&nbsp;<a href="#">Terms and Conditions</a>
	</div>
</div>
	
	
<div class="group">	
<button type="button" class="button buttonBlue" value="회원가입" onclick="check_input();" >
회원가입</button>
  </div>
<div class="join-item">			
			<span>	<a href="home.me">홈으로</a> </span>
</div>
<div class="etc">
			<input type="hidden" name="grade"value="일반">
			<input type="hidden" name="authkey"value="">
			<input type="hidden" name="status"value="">
		</div>
			

</form>
</body>
</html>
 
 

