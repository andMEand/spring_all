<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset ="utf-8" http-equiv = "X-UA-Compatible" content="IE=ege"/>
<!-- 반응형 웹 관련 작업 -->
<meta name ="viewport" content = "user-scalable=no, inital-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
  <title>Login page</title>
  <script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> <!-- 카카오톡 자바스크립트 라이브러리 -->
 
  <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(){
      //카카오톡 디벨로퍼 api 사이트에서 발급받은 자바스크립트 키
      Kakao.init("222fdbe1f594bdfccaa157a7740b0cd5"); // 내어플리케이션 클릭해서 앱 클릭 >> 자바스크립트 키 입력 
      //카카오 로그인 버튼을 생성합니다.
      Kakao.Auth.createLoginButton({ //로그인 버튼 만들어줌
        container : "#kakao-login-btn", //연결자
        success: function(authObj){
          console.log(authObj);
          Kakao.API.request({
            url: '/v2/user/me', //v1/user/me api는 종료
            success: function(res) {
                 console.log(res);
                 //개발자 페이지에서 해당 앱의 제품설정 >> 카카오 로그인을 on으로 하고 
                 //제품설정 >> 동의항목에서 개인정보 보호항목에서 프로필 정보 설정에서 필수동의로 설정한다.
                 //아이디
                 document.getElementById("kakaoIdentity").innerHTML = res.id;
                 //카카오톡 계정
               //   document.getElementById("kakaoAccount").innerHTML = res.kakao_account;
                 //닉네임
                 document.getElementById("kakaoNickName").innerHTML = res.properties.nickname;
                 //프로필 이미지
                 document.getElementById("kakaoProfileImg").src = res.properties.profile_image;
                 //썸네일 이미지
                 document.getElementById("kakaoThumbnailImg").src = res.properties.thumbnail_image;
            },
            fail: function(error) {
               alert(JSON.stringify(error));
                 console.log(error);
            }
          });
        }, //성공
        fail : function(error){
          alert(JSON.stringify(error));
        }
      });//카카오로그인버튼
    });//이벤트리스너 추가
  </script>

<style>
.login {
    text-align: center;
    letter-spacing: 3px;
}
a{
    text-decoration: none;
}
a:hover{
    color: #390;
}
</style>
<script>

</script>
</head>
<body>
    <div class="login">
<form name="loginform" action="login.me" method="post">

<table align="center">
<tr margin="2px 2px"><td colspan="2" align=center >
<b> <font size=5>로그인 페이지 </font></b></td></tr>

<tr><td>아이디: </td><td><input type="text" name="email"/></td></tr>
<tr><td>비밀번호: </td><td><input type="password" name="pw"/></td></tr>


<tr><td colspan="2" align=center>
<a href="javascript:loginform.submit()"> 로그인</a>&nbsp;&nbsp;
<a href="joinform.me"> 회원가입</a>
<a href="#"> 이메일 찾기</a>
<a href="#"> 비밀번호 찾기</a></td></tr>
</table>
<br/>
<div class="easylogin lo1">
	<a id="kakao-login-btn"></a>
</div>
<div class="easylogin lo2">
	<img src="#" alt="네이버로그인"><a href="#" >네이버로그인</a>
</div>






</form>
</div>
</body>
</html>