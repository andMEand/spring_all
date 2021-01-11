<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

</head>
<body>
   <script>
      var naverLogin = new naver.LoginWithNaverId(
         {
            clientId: "x3Tys5ssECV0IWRL3l7E",
            callbackUrl: "http://localhost:8080/samsam/callback.me",
            isPopup: false,
            callbackHandle: true
         }
      );
      naverLogin.init();
      
     
      window.addEventListener('load', function () {
         naverLogin.getLoginStatus(function (status) {

            if (status) {
               var email = naverLogin.user.getEmail()
               var nickname = naverLogin.user.getNickName();
               console.log(email)
                             
               $('.n_email').val(email);
		  	   $('.n_name').val(nickname);
		  		console.log("네이버 이메일: " + $('.n_email').val() + "네이버 닉네임 : " + $('.n_name').val() )
		  		
		  		$('#nidData').submit();
               //window.location.replace("http://127.0.0.1/test2.html");
            } else {
               console.log("callback 처리에 실패하였습니다.");
            }
         });
      });
   </script>
<form id = "nidData" action ="nidLogin.me" method="post">
<input type="hidden" class="n_email" name = "email" value = "">
<input type="hidden" class="n_name" name = "nick" value="">
</form>
</body>
</html>