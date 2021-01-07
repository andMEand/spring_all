<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.project.samsam.board.*" %>
<%@ page import="com.project.samsam.board.BoardVO"%>
<%
	List<BoardVO> searchList =(List<BoardVO>)request.getAttribute("searchList");
	List<BoardVO> free_doc =(List<BoardVO>)request.getAttribute("free_doc");
	List<BoardVO> community =(List<BoardVO>)request.getAttribute("community");
	List<BoardVO> adopt_list =(List<BoardVO>)request.getAttribute("adopt_list");

 %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
    
<link href="resources/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
 <link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
 
<title>나의 반려친구, 삼삼하개</title>
 
 <script>
 $(document).ready(function(){
 var start =5;
 $(".free_adopt").slice(5).hide();
 $(".community").slice(5).hide(); 
 $(".adopt_list").slice(5).hide();
 
 $("#check1").click(function(e){
	 e.preventDefault();
	 start +=5;
	 $(".free_adopt").slice(0, start).show();
	 
	 if($(".free_adopt").length <= start){
		 $(".error1").html("더이상 표시할 내용이 없습니다.");
	 };
		 
 });
 
 $("#check2").click(function(e){
	 e.preventDefault();
	 start +=5;
	 $(".community").slice(0, start).show();
	 
	 if($(".community").length <= start){
		 $(".error2").html("더이상 표시할 내용이 없습니다.");
	 };
		 
 });
 
 $("#check3").click(function(e){
	 e.preventDefault();
	 start +=5;
	 $(".adopt_list").slice(0, start).show();
	 
	 if($(".adopt_list").length <= start){
		 $(".error3").html("더이상 표시할 내용이 없습니다.");
	 };
		 
 });
 }); 
 

 
 </script>
 <style>
 #wrap {
}
#allcontent {
magin : auto o;
}
 .body, html {
	margin: 0;
	font-family: 'Noto Serif KR', serif;
}
  .body_content{
  	margin : 0;
  	height:100vh;
    display : flex;
	justify-content: center;
  }
  #aside{
  	width : 250px;
    flex-direction:column;
    justify-content: space-around;
  }
  .box .name .m_menu{
    align-self: auto;
  }
  .name {
    margin-right : 50px;
    padding : 0;
    text-align:center;
  }
.box {
	margin : 30px;
	padding: 0;
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
}
.profile {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
ul{
padding:0;
margin-top:30px;
}
li{
width: 100%;
height : 48px;
list-style-type:none;
display:flex;
justify-content: flex-start;
}
a{
color : black;
text-decoration:none;
}
.content{
	margin-top:100px;
	width : 550px;
    display : flex;
    flex-direction: column;
    justify-content : flex-start;
}

#check1, #check2,#check3{
width : 300px;
height : 30px;
border : 1px solid skyblue;
background-color : rgba(0,0,0,0);
color : skyblue;
padding : 5px;
border-radius : 5px;
align-self : right;

}
#check1:hover,
#check2:hover,
#check3:hover{
color : white;
background-color:skyblue;
}
.textbox {
  display:flex;
  flex-direction : column;
  align-items : left;
  max-height: 60px;
  margin-left:80px;
  padding: 10px 8px 4px 8px;
  width: 500px;
  height: 100px;
  position: relative;
 }
 
input {
  font-family: 'Noto Serif KR', serif;
  outline: 0;
  border: 1px solid #eeeeee;
  border-radius : 5px;
  margin-top:10px;
  display: block;
  width:270px;
  height : 40px;
  font-size:1em;
}

.error1, .error2, .error3{
font-size: 0.9em;
color : red;
padding-left: 0px;
text-align : left;
}
 </style>
  
</head>
<body>


    <div id="wrap">
    	<div id ="allcontent">
        <div id="header">
            <div class="container">
                <div class="header">
                    <div class="header-beaner">My Alien, 나와 함께할 친구</div>
                    <div class="header-menu">
                     <span id="home_search">
                    	<form action="home_search.me" method="post" name="home_search">
                    		<input type="text" id="keyword" name="keyword" value="" onkeypress="searchList()">
                    		
                    		<button type="submit" class="h_sc"><i class="fas fa-search fa-lg"></i></button>
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

            <div id="cont_search_list">
                <div class="body_content">
               
                    
                    <form class = "content">
                    	 <div id="ud_tab-content1" class="ud_content">
                    	 
	                   		<h3>책임분양 list</h3>
	                    	<table>
	                    	<thead>
	                    		<tr><td>글번호</td><td>제목</td><td>작성자</td><td>작성이</td></tr>
	                    	</thead>
	                    	<tbody>
	                    	
	                    	<%
	                    	if(free_doc !=null){
	                    		for(BoardVO f_list: free_doc){
	                    	
	                    	%>
	                    	<tr class="free_board">
	                    		<td><%=f_list.getNum()%></td>
	                    		<td> <%=f_list.getSubject()%></td>
	                    		<td> <%=f_list.getNick() %></td>
	                    		<fmt:formatDate var="formatDate" value="<%=f_list.getC_date()%>" pattern="yyyy-MM-dd"/>
	    						<td>${formatDate}</td>
	                    
	                     	</tr>
	                     	<% 
	                    	}}
	                    	%>
	                    	  </tbody>       	  
	                    	</table>
	                    	<input type="button" id="check1" value="더보기"><br>
                    	 <div class="error1"></div>
                    </div>
                    
                    <div id="ud_tab-content2" class="ud_content">
                   		<h3>커뮤니티 list</h3>
                    	<table>
                    	<thead>
                    		<tr><td>글번호</td><td>제목</td><td>작성자</td><td>작성이</td></tr>
                    	</thead>
                    	
                
                    	<tbody>
                    	<%
                    	if(community !=null){
                    		for(BoardVO c_list: community){
                    	%>
                    	<tr class="community_board">
                    		<td><%=c_list.getNum()%></td>
                    		<td> <%=c_list.getSubject()%></td>
                    		<td> <%=c_list.getNick() %></td>
                    		<fmt:formatDate var="formatDate" value="<%=c_list.getC_date()%>" pattern="yyyy-MM-dd"/>
    						<td>${formatDate}</td>
                    	</tr>
                    	<% 
                    	}}
                    	%>
                    	<tbody>
                    	</table>
                    	<input type="button" id="check2" value="더보기"><br>
                    	 <div class="error2"></div>
                    </div>
                    
                    <div id="ud_tab-content3" class="ud_content">
                   		<h3> 분양 list</h3>
                    	<table>
                    	<thead>
                    		<tr><td>글번호</td><td>제목</td><td>작성자</td><td>작성이</td></tr>
                    	</thead>
                    		<tbody>
                    	<%
                    	if(adopt_list !=null){
                    		for(BoardVO a_list: adopt_list){
                    	
                    	%>
                    	
                    	<tr class="adopt_board">
                    		<td><%=a_list.getNum()%></td>
                    		<td> <%=a_list.getSubject()%></td>
                    		<td> <%=a_list.getNick() %></td>
                    		<fmt:formatDate var="formatDate" value="<%=a_list.getC_date()%>" pattern="yyyy-MM-dd"/>
    						<td>${formatDate}</td>
                    	</tr>
                    	<% 
                    	}}
                    	%>
                    		<tbody>
                    	</table>
                    	<input type="button" id="check3" value="더보기"><br>
                    	 <div class="error3"></div>
                    </div>
                
            </div>
        </div>
        <!-- //cont -->
       
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
      </div>  
   </div>    
    
</body>
</html>