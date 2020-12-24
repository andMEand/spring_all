<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">

    <title>삼삼하개-my page</title>

   
    <!-- 웹 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
  
    <style>
@charset "utf-8";
body,
div,
ul,
li,
dl,
dt,
ol,
h1,
h2,
h3,
h4,
h5,
h6,
input,
fieldset,
legend,
p,
select,
table,
th,
td,
tr,
textarea,
button,
form {
  margin: 0;
  padding: 0;
}

/* a 링크 초기화 */
a {
  color: #222;
  text-decoration: none;
}
a:hover {
  color: #390;
}

/* 폰트 초기화 */
body,
input,
textarea,
select,
button,
table {
  font-family: "Nanum Gothic", AppleSDGothicNeo-Regular, "Malgun Gothic",
    "맑은 고딕", dotum, "돋움", sans-serif;
  color: #222;
  font-size: 13px;
  line-height: 1.5;
}

/* 블릿기호 초기화 */
dl,
ul,
li,
ol,
menu {
  list-style: none;
}

/* 제목 태그 초기화 */
h1,
h2,
h3,
h4,
h5,
h6 {
  font-size: 13px;
  color: #222;
  font-weight: normal;
}

/* IR 효과 */
.ir_pm {
  display: block;
  overflow: hidden;
  font-size: 0;
  line-height: 0;
  text-indent: -9999px;
} /* 의미있는 이미지의 대체 텍스트를 제공하는 경우 */
.ir_wa {
  display: block;
  overflow: hidden;
  position: relative;
  z-index: -1;
  width: 100%;
  height: 100%;
} /* 의미있는 이미지의 대체 텍스트로 이미지가 없어도 대체 텍스트를 보여주고자 할 때 */
.ir_su {
  overflow: hidden;
  position: absolute;
  width: 0;
  height: 0;
  line-height: 0;
  text-indent: -9999px;
} /* 대체 텍스트가 아닌 접근성을 위한 숨김 텍스트를 제공할 때 */




/* 스킵 내비게이션  */
#skip {
    position: relative;
}
#skip a {
    position: absolute;
    left: 0;
    top: -35px;
    border: 1px solid #fff;
    color: #fff;
    background: #333;
    line-height: 30px;
    width: 140px;
    text-align: center;
}
#skip a:active,
#skip a:focus {
    top: 0;
}

/* 레이아웃 */
#wrap {}
#header {
    height: 225px;
    background: #f2edc7;
}
#contents {}
#footer {
    height: 200px;
    background: #f6fdff;
}

/* 컨텐츠 레이아웃 */
#cont_nav {
    background-color: #f6fdff;
}
#cont_tit {
    background-color: #eaf7fd;
}
#cont_ban {
    background-color: #fffbf5;
    height: 400px;
}
#cont_cont {
    background-color: #f6fdff;
}

/* 컨테이너 */
.container {
    width: 990px;
    margin: 0 auto;
    height: inherit;
    /* background: rgba(255,255,255,0.3);*/
}

/* 헤더 */

.header .header-menu {
    text-align: right;
}
.header .header-menu a {
    color: #707062;
    padding: 10px 0 10px 13px;
    display: inline-block;
}
.header .header-menu a:hover {
    color: #666;
}
.header .header-tit {
    text-align: center;
}
.header .header-tit h1 {
    background-color: #f2edc7;
    font-size: 28px;
    padding: 5px 20px 6px;
    display: inline-block;
    color: #121210;
    margin-top: 40px;
    font-weight: normal;
    text-transform: uppercase;
}
.header .header-tit a {
    display: inline-block;
    background-color: #f2edc7;
    font-size: 18px;
    color: #fff;
    padding: 5px 20px 6px;
    margin-top: -5px;
}
.header .header-icon {
    text-align: center;
    margin-top: 30px;
}
.header .header-icon a {
    width: 60px;
    height: 60px;
    display: inline-block;
    background: url("./img/icon.png");
    margin: 0 3px;
}
.header .header-icon a.icon1 {
    background-position: 0 0;
}
.header .header-icon a.icon2 {
    background-position: 0 -60px;
}
.header .header-icon a.icon3 {
    background-position: 0 -120px;
}
.header .header-icon a.icon4 {
    background-position: 0 -180px;
}
.header .header-icon a.icon1:hover {
    background-position: -60px 0;
}
.header .header-icon a.icon2:hover {
    background-position: -60px -60px;
}
.header .header-icon a.icon3:hover {
    background-position: -60px -120px;
}
.header .header-icon a.icon4:hover {
    background-position: -60px -180px;
}

/* float: left로 인한 영역깨짐(height:0) 방지법 
1. 깨지는 영역에 똑같이 float:left를 사용한다.(X) --> 모든 박스에 float:left를 사용하게 됩니다.
2. float의 성질을 차단하는 clear: both;를 사용한다.(X) --> 어떤 영역이 깨졌는지 찾기 어려움.
3. float을 사용한 상위 박스한테 overflow: hidden을 사용합니다. (O) --> 현재는 제일 많이 사용.
4. clearfix를 사용합니다. (O)
*/

/* 전체 메뉴 */
.nav {
    overflow: hidden;
    padding: 20px;
}
.nav > div {
    float: left;
    width: 20%;
}
.nav > div:last-child {
    text-align: right;
}
/* ie9부터 사용 가능 */
.nav > div.last {
    width: 15%;
}
.nav > div h3 {
    font-size: 18px;
    color: #999;
    margin-bottom: 4px;
    text: bold;
}
.nav > div ol {
    overflow: hidden;
}
.nav > div ol li {
    float: left;
    width: 50%;
}
.nav > div.last ol li {
    width: 100%;
}
.nav > div ol li a:hover {
    text-decoration: underline;
}
.nav i {
    color: #999;
    font-size: 2rem;
}

/* 타이틀 */

.tit h2 {
    font-size: 40px;
    text-align: center;
    padding: 5px 0;
    letter-spacing: 2px;
    color: #2c94c4;
    font-family: "Nanum Brush Script", cursive;
}
.tit .btn {
    position: absolute;
    top: 5px;
    right: 0;
    width: 60px;
    height: 60px;
    background: url("../img/icon.png") no-repeat 0 -600px;
}

/* 배너 */
.ban {
    position: relative;
    padding: 24px 0 20px;
}
.ban a.prev {
    position: absolute;
    left: -80px;
    top: 60px;
    width: 43px;
    height: 43px;
    background: url("../img/icon.png") no-repeat -150px 0;
}
.ban a.next {
    position: absolute;
    right: -80px;
    top: 60px;
    width: 43px;
    height: 43px;
    background: url("../img/icon.png") no-repeat -150px -43px;
}
.ban a.prev:hover {
    background-position: -193px 0;
}
.ban a.next:hover {
    background-position: -193px -43px;
}

.ban ul {
    overflow: hidden;
}
.ban ul li {
    float: left;
    width: 330px;
}
.ban ul li:last-child {
    text-align: right;
}
.ban ul li:nth-child(2) {
    text-align: center;
}
.ban ul li .ban_img1 {
    text-align: left;
}
.ban ul li .ban_img2 {
    text-align: center;
}
.ban ul li .ban_img3 {
    text-align: right;
}
.ban ul li img {
    border: 4px solid #dcdcdc;
}
.ban ul li img:hover {
    border-color: #98bcdc;
}

/* 컨텐츠*/
.cont {
    padding: 30px;
}
.cont .column {
    height: 500px;
    margin: 0 30px 30px 0;
}
.cont .col1 {
    border-bottom: 1px solid #c8c8c8;
}
.cont .col2 {}
.cont .col3 {}

.cont .ico_tit {
    padding-left: 70px;
    margin-top: 0;
    font-size: 26px;
    border-bottom: 1px solid #d0d0d0;
}

i {
    font-size: 3rem;
    left: 0;
    top: 0;
}
      .footer, .footer>div>a{
        display:flex;
        justify-content : space-between;
        color : #d0d0d0;
      }
       

/*게시판*/
.notice {}
.notice h4 {}
.notice ul {}
.notice ul li {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.notice ul li a {}
.notice a.more {
    position: absolute;
    top: 0;
    right: 0;
}

    </style>
  
  </head>
<body>

    <div id="wrap">
        <div id="header">
            <div class="container">
                <div class="header">
                    <div class="header-menu">
                        <input type="text">검색</input>       <!-- 검색양식????-->
                        <a href="#">Login</a>
                        <a href="#">Join</a>
                    </div>
                    <div class="header-tit">
                      <a href="#"><h1>삼삼하개</h1>
                      </a>
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
                            <a href="#"><h3>분양</h3></a>
                            
                        </div>
                        <div>
                          <a href="#"><h3>보호소</h3></a>
                              
                        </div>
                        <div>
                          <a href="#"><h3>커뮤니티</h3></a>
                        </div>
                        <div >
                          <a href="#"><h3>고객지원</h3></a>
                            
                        </div>
                        <div class="last">
                          <i class="fas fa-comment-dots"></i><a href="" class="ir_pm">문의하기</ㅁ></a>
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- //cont_nav -->
          
            <div id ="body_content">
                <div class="container">
                <div class="mymenu" style="background: #BDBDBD;">
                    <img class="profile" src="#">
                </div>
                <div class ="name">이름/닉네임</div>
                <nav class ="my_menu">
                 <ul>
                    <li><a href="#">책임분양관리</a></li>
                    <li><a href="#">회원정보</a></li>
                    <li><a href="#">작성글관리</a></li>
                    <li><a href="#">판매허가번호인증</a></li>
                 </ul>
                </nav>
                </div>
                <span class = "boardlist">
                    <span class="board-cont" width="300px">
                        <a href="">작성글</a>
                        <a href="">작성댓글</a>
                    </span>
                    <span class="boardlists">
                      <table border="1px solid">
                        <tr>
                          <td><a href="">제목</a></td>
                          <td><a href="">적성일</a></td>
                        </tr>
                        <tr>
                        <td></td>
                        </tr>
                      </table>
                    </span>
              </div>
              </div>    
  

                        <!-- 게시판 리스트 -->

               
        <div id="footer">
            <div class="container">
                <h2 class="ir_su">푸터 영역</h2>
                <div class="footer">
                        <div><a href="#">사이트 도움말</a></div>
                        <div><a href="#">사이트 이용약관</a></div>
                        <div><a href="#">사이트 운영규칙</a></div>
                        <div><a href="#"><strong>개인정보취급방침</strong></a></div>
                        <div><a href="#">책임의 한계와 법적고지</a></div>
                        <div><a href="#">게시중단요청 서비스</a></div>
                        <div><a href="#">고객센터</a></div>
                    
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