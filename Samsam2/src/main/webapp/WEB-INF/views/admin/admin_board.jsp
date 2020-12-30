<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.project.samsam.board.*" %>
<%

//	클래스 변수이름 = (클래스)request.getAttribute("모델로 저장한 이름");
//	int b_no = 변수이름.getB_no();
 %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>layout2</title>
    <style>
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

dl,
ul,
li,
ol,
menu {
  list-style: none;
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


/* 초기화 끝 */
/* 스타일 시작 */

        * {margin: 0; padding: 0;}
        #wrap {width: 100%;}   
        header {width: 100%; height: 150px; background: #85af87;}
        aside {float: left; width: 20%; height: 700px; background: #a4c2a5;}
        section {float: left; width: 60%; height: 700px; background: #8ebe90;}
        article {float: left; width: 20%; height: 700px; background: #829683;}
        footer {clear: both; width: 100%; height: 150px; background: #509954;}

        /* 화면 너비 0 ~ 1200px */
        @media (max-width: 1220px){
            aside {width: 40%;}
            section {width: 60%;}
            article {width: 100%; height: 300px;}
        }
        /* 화면 너비 0 ~ 768px */
        @media (max-width: 768px){
            aside {width: 100%; height: 300px;}
            section {width: 100%; height: 300px;}
        }
        /* 화면 너비 0 ~ 480px */
        @media (max-width: 480px){
            header {height: 300px;}
            aside {height: 200px;}
            section {height: 200px;}
        }
    .search_bar .search_area1 {
        float: left;
    }
    .search_bar .search_area2 {
    }
    
    </style>

<!-- 검색 달력 설정-->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
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
    });
</script>
<!-- 검색 달력 함수 END-->

<!-- 검색 리스트 함수-->
<script type="text/javascript">
$(document).ready(function(){
    $("input_data")/click(function(event){
        var params = $('insert_form').serialize();
        alert(params);
        $.ajax({
            url: '/ajacx/insertWord.do',
            type: 'POST',
            data:params,
            contentType:  'application/x-www-form-urlencoded;charset=utf-8',
            dataType: 'json',
            
            success: function(retVal){
                if(retVal.res =="OK"){
                    selectData();

                    $('#startdata').val('');
                    $('#enddata').val('');
                    $('#writing').val('');
                    $('#warning').val('');
                    $('#adopt_list').val('');
                    $('#community').val('');
                    $('#keyword').val('');
                }
                else{
                    <% 
                    System.out.println("Insert Fail");
                    %>
                }
            },
            error: function(){
                <% 
                System.out.println("ajax 통신실패!!!!2");
                %>
            }
        });
        //기본 이벤트 제거
	event.preventDefault();
    });
});


function selectData(){
    $('output').empty(); 
    $.ajax({
        url: '/ajax/getAd_boardList.do',
        type:'POST',
        contentType:'application/x-www-form-urlencoded; charset=utf-8',
        success:function(data){
            $.each(data,function(index,item){
                var output='';
                output += '<tr>';
                output += '<td>' + item.b_no + '</td>';
                output += '<td><a href="/ajax/text_view.do?b_no='+'<%= b_no %>' +' ">' + item.b_subject +'</td>';
                output += '<td>' + item.b_date + '</td>';
                output += '</tr>';
                console.log("output:" + output);
				$('#output').append(output);
            });

        },
        error:function(){
            <% 
                System.out.println("ajax 통신실패!!!!2");
                %>
        }
    });// 여기까지 에이젝스
}

</script>

<!-- 검색 리스트 함수 END-->



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
                        <form action="search_input.me" methos="post" name="serarch_input">
                            <div class="search_area1">
                                <div class="search_item1">
                                    <label for="date">기간 :</label> 
                                    <input type="text" id="startDate"> - <input type="text" id="endDate"></div>
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
                                    검색어 : <input type="text" name="keyword" value="">
                                </div>
                            </div>
                            <div class="search_are2">
                                <input type="button" value="조회">
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