<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 로그인 페이지</title>
	<link href="resources/css/login_form2.css"rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	<script src="resources/js/login_form.js"></script>
	<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> <!-- 카카오톡 자바스크립트 라이브러리 -->
	<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script> <!-- 네이버 js 라이브러리 -->
	

	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function(){
			//카카오톡 디벨로퍼 api 사이트에서 발급받은 자바스크립트 키
			Kakao.init("4658e426b678bd2dd9ed7731ab1a1868"); // 내어플리케이션 클릭해서 앱 클릭 >> 자바스크립트 키 입력 
			//카카오 로그인 버튼을 생성합니다.
			Kakao.Auth.createLoginButton({ //로그인 버튼 만들어줌
				container : "#kakao-login-btn", //연결자
				success: function(authObj){
					console.log(authObj);
					Kakao.API.request({
						url: '/v2/user/me', //v1/user/me api는 종료
						success: function(res) {
						    console.log(res);
						    						    
						  	$('.email').val(res.kakao_account.email);
						  	$('.name').val(res.properties.nickname);
						  	console.log("이메일: " + $('.email').val() + "닉네임 : " + $('.name').val() )
						  	
						  	$('#kkoData').submit();
						     
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
	
		  function kakaoLogout() {
		    if (!Kakao.Auth.getAccessToken()) {
		      alert('Not logged in.')
		      return
		    }
		    Kakao.Auth.logout(function() {
		      alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken())
		    })
		  }
		</script>
<!-- 네이버 로그인 -->
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function(){

	  var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "x3Tys5ssECV0IWRL3l7E",
			callbackUrl: "http://localhost:8080/samsam/callback.me",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			callbackHandle: false,
			loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();

		naverLogin.getLoginStatus(function (status) {
			if (status) {
				var email = naverLogin.user.getEmail();
				var name = naverLogin.user.getNickName();
				var profileImage = naverLogin.user.getProfileImage();
				var birthday = naverLogin.user.getBirthday();			
				var uniqId = naverLogin.user.getId();
				var age = naverLogin.user.getAge();
			
				$('.n_email').val(naverLogin.user.getEmail());
		  		$('.n_name').val(naverLogin.user.getNickName());
		  		console.log("네이버 이메일form: " + $('.n_email').val() + "네이버 닉네임 : " + $('.n_name').val() )
		  		
		  	
		} else {
			console.log("AccessToken이 올바르지 않습니다.");
		}
	});
});
</script>
<!-- // 네이버아이디로로그인 초기화 Script -->
		<style type="text/css">
		#naver_id_login{
		width:222px;	}
		</style>
</head>
<body>
<div class="login-header">
<hgroup>
  <h1>Login</h1>
  <h3>smasmahagae</h3>
</hgroup>
</div>
<form name="loginform" action = "login.me" method="post" class="loginform" >
<div class="group">
	<input type="text"name="email"><span class="highlight"></span><span class="bar"></span>
    <label for="email" class="label-email"><span class="content-email">Email</span></label>
</div>
<div class="group">
	<input type="password" name="pw"><span class="highlight"></span><span class="bar"></span>
    <label>password</label>
</div>
	<button type="submit" class="button buttonBlue" onclick="login();" >로그인
    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
  </button>
  <div class="login-item">
 	 <a href ="joinform.me">비밀번호찾기</a>
	<a href ="joinform.me">회원가입</a>
	</div>
</form>

<div class="sns_login" > 
	<div id="kakao_id_login" style="text-align:center"><a id="kakao-login-btn"></a><br/>
	</div>
	<div id="naver_id_login" style="text-align:center">
	<div id="naverIdLogin"></div>
	</div>
</div>

<form id = "kkoData" action = "kkoLogin.me" method= "post">
<input type="hidden" class="email" name = "email" value = "">
<input type="hidden" class="name" name = "nick" value="">
</form>
<form id = "nidData" action ="nidLogin.me" method="post">
<input type="hidden" class="n_email" name = "email" value = "">
<input type="hidden" class="n_name" name = "nick" value="">
</form>

</body>
</html>