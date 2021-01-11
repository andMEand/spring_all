<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>

<head>

<meta charset="utf-8" />
<head profile="http://www.w3.org/2005/10/profile">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	<script src="resources/js/login_form.js"></script>

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

     input_form.submit(); // 서버로 전송
    }
  </script>
<style>
* { box-sizing:border-box; }
a{
	text-decoration: none;
}
body {
	font-family: Helvetica;
	background: #fcfcfc;
  -webkit-font-smoothing: antialiased;
}
.login-header {
	margin-top: 200px;
}
hgroup { 
	
	text-align:center;
	
}

h1, h3 { font-weight: 300; }

h1 { color: #636363; }

h3 { color: #4a89dc; }

form {
	width: 500px;
	margin:1em auto;
	padding: 3em 2em 2em 2em;
	background: #fcfcfc;
	border-radius: 5px;
}
form .kkoData {
	display: none;
}
.group { 
	position: relative; 
	margin-bottom: 45px; 
}

input {
	font-size: 18px;
	padding: 10px 10px 10px 5px;
	-webkit-appearance: none;
	display: block;
	background: #fcfcfc;
	color: #636363;
	width: 100%;
	border: none;
	border-radius: 0;
	border-bottom: 1px solid #757575;
}

input:focus { outline: none; }


/* Label */

label {
	color: #999; 
	font-size: 15px;
	font-weight: normal;
	position: absolute;
	pointer-events: none;
	left: 5px;
	top: 10px;
	transition: all 0.2s ease;
}


/* active */

input:focus ~ label, input.used ~ label {
	top: -20px;
  transform: scale(.75); left: -2px;
	/* font-size: 14px; */
	color: #4a89dc;
}


/* Underline */

.bar {
	position: relative;
	display: block;
	width: 100%;
}

.bar:before, .bar:after {
	content: '';
	height: 2px; 
	width: 0;
	bottom: -1px; 
	position: absolute;
	background: #4a89dc; 
	transition: all 0.2s ease;
}

.bar:before { left: 50%; }

.bar:after { right: 50%; }


/* active */

input:focus ~ .bar:before, input:focus ~ .bar:after { width: 50%; }


/* Highlight */

.highlight {
	position: absolute;
	height: 60%; 
	width: 100px; 
	top: 25%; 
	left: 0;
	pointer-events: none;
	opacity: 0.5;
}


/* active */

input:focus ~ .highlight {
	animation: inputHighlighter 0.3s ease;
}


/* Animations */

@keyframes inputHighlighter {
	from { background: #4a89dc; }
	to 	{ width: 0; background: transparent; }
}


/* Button */

.button {
  position: relative;
  display: inline-block;
  padding: 12px 24px;
  margin: .3em 0 1em 0;
  width: 100%;
  vertical-align: middle;
  color: #fff;
  font-size: 16px;
  line-height: 20px;
  -webkit-font-smoothing: antialiased;
  text-align: center;
  letter-spacing: 1px;
  background: transparent;
  border: 0;
  border-bottom: 2px solid #3160B6;
  cursor: pointer;
  transition: all 0.15s ease;
  border-radius: 5px;
}
.button:focus { outline: 0; }


/* Button modifiers */

.buttonBlue {
  background: #4a89dc;
  text-shadow: 1px 1px 0 rgba(39, 110, 204, .5);
}

.buttonBlue:hover { background: #357bd8; }


/* Ripples container */

.ripples {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: transparent;
}


/* Ripples circle */

.ripplesCircle {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0;
  width: 0;
  height: 0;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.25);
}

.ripples.is-active .ripplesCircle {
  animation: ripples .4s ease-in;
}


/* Ripples animation */

@keyframes ripples {
  0% { opacity: 0; }

  25% { opacity: 1; }

  100% {
    width: 200%;
    padding-bottom: 200%;
    opacity: 0;
  }
}

.sns_login {
	display:flex;
	justify-content: space-evenly;
	flex-direction:column;
	margin-top:10px;
	height : 150px;
}

#naver_id_login{
	margin : 0 auto;
}
img{
	width: 222px;
	height: 48px;
}
#kkoData, #nidData {
	background: #fcfcfc;
}
.login-item {
	display:flex;
	justify-content: space-between;
}
</style>
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
	<input type="text"name="email"><span class="highlight"></span><span class="bar"></span>
    <label for="email" class="label-email"><span class="content-email">Email</span></label>
</div>
<div class="group">
	<input type="text"name="name"><span class="highlight"></span><span class="bar"></span>
    <label for="name" class="label-email"><span class="content-email">이름</span></label>
</div>
<div class="group">
	<input type="password" name="pw"><span class="highlight"></span><span class="bar"></span>
    <label>password</label>
</div>
<div class="group">
	<input type="password" name="pw2"><span class="highlight"></span><span class="bar"></span>
    <label>password 확인</label>
</div>
<div class="group">
	<input type="number" name="phone"><span class="highlight"></span><span class="bar"></span>
    <label>휴대폰 번호</label>
</div>
<div class="group">

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
	</select><span class="bar">
	
   
</div>

<div class="group">
			<div class="checkbox">
            <label>
              <input type="checkbox"> 이용약관 동의 <a href="#">Terms and Conditions</a>
            </label>
          </div>
</div>		
<button type="button" class="button buttonBlue" value=" 회원가입" onclick="check_input();" >회원가입
    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
  </button>
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
 
 

