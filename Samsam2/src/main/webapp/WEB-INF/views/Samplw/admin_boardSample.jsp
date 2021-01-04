<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.project.samsam.board.*" %>
<%@ page import="com.project.samsam.member.MemberVO"%>
<%@ page import="com.project.samsam.board.ABoardVOto"%>
<%@ page import="com.project.samsam.board.ABoardVO"%>
<%

String email = (String) session.getAttribute("email");

/*
if (session.getAttribute("email") == null){
	out.println("<script>");
	out.println("location.href='loginForm.me'");
	out.println("</script>");
s
*/

ArrayList<ABoardVOto> list =(ArrayList<ABoardVOto>)request.getAttribute("list");


//	클래스 변수이름 = (클래스)request.getAttribute("모델로 저장한 이름");
//	int b_no = 변수이름.getB_no();
 %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>layout2</title>


<!-- 검색 달력 설정-->
 <link href="resources/css/adminstyle.css" rel="stylesheet" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js" integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30=" crossorigin="anonymous"></script>

<!-- 모달 /제이쿼리 필수 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!-- 검색 달력 함수-->
<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#startDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 maxDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                      //시작일(startDate) datepicker가 닫힐때
                      //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                     $("#endDate").datepicker( "option", "minDate", selectedDate );
                    
                 }    
 
            });
            $( "#endDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 maxDate: 0,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                     // 종료일(endDate) datepicker가 닫힐때
                     // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                     $("#startDate").datepicker( "option", "maxDate", selectedDate );
                     
                 }    
 
            }); 

            <!-- 검색 리스트 함수--> 

            $("#find_data").click(function(event){
            	
  				console.log($('#startDate').val())
                var params = $('#find_form').serialize();     //단순 확인용
                $.ajax({
                    url: '/samsam/boardFind.do',
                    type: 'POST',
                    data: params,
                    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                    dataType: 'json',
                    
                    success: function(list){
                    	 $.each(list,function(index,item){
                    		 $('output').empty(); 
                             var output='';
                             output += '<tr>';
                             output += '<td>' + item.no + '</td>';
                             output += '<td><a href=""' + item.subject +'</a></td>';
                             output += '<td>' + item.nick + '</td>';
                             output += '<td>' + item.date + '</td>';
                             output += '<td>' + item.readcount + '</td>';
                             output += '</tr>';
                             console.log("output:" + output);
             				$('#output').append(output);
                         });
                    },
                    error: function(){
                       alert("통신실패");
                     
                    }
                });
                //기본 이벤트 제거
        	event.preventDefault();
            });
            <!-- 검색 리스트 함수 END--> 

    });
</script>
<!-- 검색 달력 함수 END-->

</head>
<body>
    <div id="wrap">
        <header>
            <div id="header">
                <div class="container">
                    <div class="con_banner">
                        배너
                    </div>
                    <div class="con_account">
                        관리자 계정
                    </div>
                </div>
                
            </div>
        </header>
        <aside>
            <div id="side_nav">
                <div class="container">
                    <div class="con_nav">
                        <ul>
                            <li class="nav"><a href="">게시글관리</a></li>
                            <li class="nav"><a href="">회원관리</a></li>
                            <li class="nav"><a href="">이용권관리</a></li>
                            <li class="nav"><a href="">책임분양</a></li>
                        </ul>
                    </div>
                </div>
            </div> 
        </aside>
        <section>
            <div id="section"><!--  -->
                <div class="container">
                    <div class="search_bar">
                        <form  method="post" id="find_form">
                            <div class="search_area1">
                                <div class="search_item1">
                                    <label for="date">기간 :</label> 
                                    <input type="text" id="startDate" name="datepicker1"> - <input type="text" id="endDate" name="datepicker2"></div>
                                    <div class="search_item2">
                                        <div class="search_item2 1">
                                            분류 :  <input type="radio" name="kind" id="writing" value=""> 게시글
                                                    <input type="radio" name="kind" id="warning" value=""> 신고
                                        </div>
                                    <div class="search_item2 2">
                                            카테고리 : <select name="category">
                                                <option value="">선택하세요</option>
                                                <option value="adopt_list">분양게시판</option>
                                                <option value="community">커뮤니티</option>
                                                </select>
                                        </div>
                                    </div>
                                <div class="search_item3">
                                    검색어 : <input type="text" id="keyword" name="keyword" value="">
                                </div>
                            </div>
                            <div class="search_are2">
                                <input type="button" id="find_data"value="조회">
                            </div>
                        </form>
                    </div>
                    <div class="search_lists">
                        <table id="output"></table>

                    </div>
                </div>
            </div>
        </section>
        <article>
            <div id="article">
                <div class="container">
                    <div class="con_art">
                        <div class="art_item">달력</div>
                        <div class="art_item">투두 리스트</div>
                        <div class="art_item">visitor</div>
                    </div>
                </div>
            </div>
        </article>
        <footer></footer>
    </div>
</body>
</html>