<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
    
  <link href="resources/css/style.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
  
  <title>나의 반려친구, 삼삼하개</title>
</head>
<body>

    <div id="wrap">
        <div id="header">
            <div class="container">
                <div class="header">
                    <div class="header-beaner">My Alien, 나와 함께할 친구</div>
                    <div class="header-menu">
                    <span id="home_search">
                    	<form action="home_search.me" method="post" name="home_search">
                    		<input type="submit" class="h_search" value="f002">
                    	</form>
                      </span> 
                        <a href="loginform.me">Login</a>
                        <a href="joinform.me">Join</a>
                    </div>
                    <div class="header-tit">
                        <h1>삼삼하개</h1><br>
                    </div>

                </div>
            </div>
        </div>
        <!-- 헤더 끝-->

        <div id="contents">
            <div id="cont_nav">
                <div class="container">
                    <h2 class="ir_su">전체 메뉴</h2>
                    <div class="nav">
                        <div>
                            <h3>분양</h3>
                            
                        </div>
                        <div>
                            <h3>보호소</h3>
                            
                        </div>
                        <div>
                            <h3>커뮤니티</h3>
                        </div>
                        <div class="last">
                            <h3>고객센터</h3>
                            
                        </div>
                    </div>
                </div>
            </div>

            <div id="cont_search">
                <div class="container">
                    <div class="search_list">
                    	
                      	
                    </div>
                </div>
            </div>
        </div>
        <!-- //cont_nav -->
       
        <!-- //con_ban -->
        <div id="footer">
            <div class="container">
                <h2 class="ir_su">푸터 영역</h2>
                <div class="footer">
                    <ul>
                        <li><a href="#">사이트 도움말</a></li>
                        <li><a href="#">사이트 이용약관</a></li>
                        <li><a href="#">사이트 운영규칙</a></li>
                        <li><a href="#"><strong>개인정보취급방침</strong></a></li>
                        <li><a href="#">책임의 한계와 법적고지</a></li>
                        <li><a href="#">게시중단요청 서비스</a></li>
                        <li><a href="#">고객센터</a></li>
                    </ul>
                    <address>
                        Copyright©samsamhagea.co.kr All Right Rederved
                    </address>
                   
                </div>
            </div>
        </div>
        <!-- //footer -->
    </div>
    <!-- //wrap -->
    <!-- script -->
    <script src="js/jquery.min_1.12.4.js"></script>
    <script src="js/slick.min.js"></script>
    <script src="js/lightgallery.min.js"></script>
    <script src="js/lightgallery-all.min.js"></script>
    <script src="js/custom.js"></script></body>
                        
</body>
</html>