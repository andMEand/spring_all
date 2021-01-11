<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
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
HashMap<Object, Object> map = (HashMap<Object, Object>)request.getAttribute("map");


//	클래스 변수이름 = (클래스)request.getAttribute("모델로 저장한 이름");
//	int b_no = 변수이름.getB_no();

	//email.toUpperCase();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>admin_board</title>

<!-- 폰트 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap" rel="stylesheet">
<!-- 어드민페이지 -->
<link href="resources/css/admin_sidebar.css" rel="stylesheet">
<!-- 아이콘 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>

<link href="resources/css/modal.css" rel="stylesheet" /> 
<script src="resources/js/admin_sidebar.js"></script>

<!-- 검색 달력 설정-->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- 모달 플러그인 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!-- 검색 달력 함수-->
<script type="text/javascript">
		$.datepicker.setDefaults($.datepicker.regional['ko']);
						$("#startDate").datepicker({
									changeMonth : true,
									changeYear : true,
									nextText : '다음 달',
									prevText : '이전 달',
									dayNames : [ '일요일', '월요일', '화요일', '수요일',
											'목요일', '금요일', '토요일' ],
									dayNamesMin : [ '일', '월', '화', '수', '목',
											'금', '토' ],
									monthNamesShort : [ '1월', '2월', '3월', '4월',
											'5월', '6월', '7월', '8월', '9월',
											'10월', '11월', '12월' ],
									monthNames : [ '1월', '2월', '3월', '4월',
											'5월', '6월', '7월', '8월', '9월',
											'10월', '11월', '12월' ],
									dateFormat : "yy-mm-dd",
									maxDate : 0, // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
									onClose : function(selectedDate) {
										//시작일(startDate) datepicker가 닫힐때
										//종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
										$("#endDate").datepicker("option","minDate", selectedDate);
									
									}

								});
						$("#endDate").datepicker(
								{
									changeMonth : true,
									changeYear : true,
									nextText : '다음 달',
									prevText : '이전 달',
									dayNames : [ '일요일', '월요일', '화요일', '수요일',
											'목요일', '금요일', '토요일' ],
									dayNamesMin : [ '일', '월', '화', '수', '목',
											'금', '토' ],
									monthNamesShort : [ '1월', '2월', '3월', '4월',
											'5월', '6월', '7월', '8월', '9월',
											'10월', '11월', '12월' ],
									monthNames : [ '1월', '2월', '3월', '4월',
											'5월', '6월', '7월', '8월', '9월',
											'10월', '11월', '12월' ],
									dateFormat : "yy-mm-dd",
									maxDate : 0, // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
									onClose : function(selectedDate) {
										// 종료일(endDate) datepicker가 닫힐때
										// 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
										$("#startDate").datepicker("option","maxDate", selectedDate);

									}

								});
						 $('#startDate').datepicker('setDate', '-7D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
						    $('#endDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
						//date picker 끝
				
						
						//검색 시작
						$("#find_data")
								.click(
										function(event) {
											console.log($('#kategorie').val())
											var params = $('#find_form').serialize(); //단순 확인용
											var data = {
												"startDate" : $('#startDate').val(),
												"endDate" : $('#endDate').val(),
												"keyword" : $('#keyword').val(),
												"kategorie" : $('#kategorie').val(),
												"kind" : $('.kind').val()

											}
											
											console.log(params)
											console.log("date" + data)

											if ($('#kind').val() == "writing") {  //게시글

												$.ajax({
															url : '/samsam/boardFind.do',
															type : 'POST',
															data : JSON.stringify(data),
															contentType : 'application/json;charset=utf-8',
															dataType : 'json',

															success : function(list) {
																console.log("게시글")
																$('#output').empty();
															$.each(list,function(index, item){
																	var output = '';
																	output += '<tr>';
																	output += '<td>'+ item.num+ '</td>';
																	output += '<td><a href="">'+ item.subject+ '</a></td>';
																	output += '<td>'+ item.nick+ '</td>';
																	output += '<td>'+ item.c_date+ '</td>';
																	output += '<td>'+ item.readcount+ '</td>';
																	output += '<td><button id="modal_open_btn" onclick="board_detail(this)" value="상세보기" number="'+ item.num +'" category ="' + item.category + '">상세보기</button></td>';
																	output += '</tr>';
																	console.log("output:"+ output);
																	$('#output').append(output);
															});	
														}, //성공
															error : function(error) {
																alert("통신실패"+ error);
															}
														}); // if 에이젝스
												event.preventDefault();
											} else {
												$.ajax({url : '/samsam/boardWFind.do',
															type : 'POST',
															data : JSON.stringify(data),
															contentType : 'application/json;charset=utf-8',
															dataType : 'json',

															success : function(list) {
																console.log("신고")
																$('#output').empty();
																$.each(list,function(index,item) {
																	var output = '';
																	output += '<tr>';
																	output += '<td>'+ item.num+ '</td>';
																	output += '<td>'+ item.subject+'</td>';
																	output += '<td>'+ item.nick+ '</td>';
																	output += '<td>'+ item.c_date+ '</td>';
																	output += '<td>'+ item.readcount+ '</td>';
																	output += '</tr>';
																	console.log("output:"+ output);
																	$('#output').append(output);
																});
															},
															error : function(error) {
																alert("통신실패"+ error);

															}

														});
												event.preventDefault();

											}//else

										});

				
	//홈페이지 Datepicker & 리스트 에이젝스 함수 끝
function checkOnlyOne(element) {
		  
		  const checkboxes = document.getElementsByName("kind");
		  
		  checkboxes.forEach(function(cb) {
		    cb.checked = false;
		  })
		  
		  element.checked = true;
	}
	//분류 체크박스 선택값 radio 함수
	
	function board_detail(obj){	
		var setData= {
				"number" : $(obj).attr('number'),
				"category" : $(obj).attr('category')
		}
		

		$.ajax({
			url: '/samsam/admin_b_detail.do',
			type : 'POST',
			data : JSON.stringify(setData),
			dataType :'json',
			contentType : 'application/json;charset=utf-8',
			success : function(map) {
				console.log("map : " + map)
				
				$('input').val("");

				$('#email').val(map.MemberVO.email);		   //회원정보
				$('#nick').val(map.MemberVO.nick);
				$('#local').val(map.MemberVO.local);
				$('#grade').val(map.MemberVO.grade);
				//
				
				$('#num').val(map.ABoardVOto.num); 
				$('#category').val(map.ABoardVOto.category); 
				$('#c_date').val(map.ABoardVOto.c_date); 
				$('#readcount').val(map.ABoardVOto.readcount); 
				$('#subject').val(map.ABoardVOto.subject);
				$('#c_content').val(map.ABoardVOto.c_content);  //게시글
				console.log($('#grade').val());

//				$('#w_count').val(map.WarningVO.wcount);		   
				//
				if(map.wList != null){					//신고리스트
					$.each(map.wList, function(index,item){
							$('.w-table').html($('.w-table').html()+'<div class="result-table-row"><div class-table-cell">'+item.w_email +'</div><div class="result-table-cell">'+ item.w_reason+'</div><div class="result-table-cell">' + item.w_date +'</div>')
							console.log("w-table : "+ $('.w-table').html)
						});
				}else{
						$('.w-table').html($('.w-table').html()+'<div class="result-table-row"><div class="result-table-cell">신고글이 없습니다</div></div>')	
				}
				if(map.cList != null){                  //댓글리스트
					$.each(map.cList, function(index, item){
						$('.c-table').html($('.c-table').html()+'<div class="result-table-row"><div class="result-table-cell">'+ item.co_content+'</div><div class="result-table-cell"><span>'+ item.co_nick +'</span><span>'+ item.co_date +'</span></div><hr width="300px"color="gray" noshade />')
					}); 
					}else{
						$('.c-table').html($('.c-table').html()+'<div class="result-table-row"><div class="result-table-cell">작성댓글이 없습니다</div></div>')	
					}
					
				
				
				$('#detail_form').modal('show');
			},//success
			error : function() {
				alert("ajax 통신 실패!!!");
		}
		})//ajax
	}//boardDetail

</script>	

</head>
<body>
<div class ="body_content">

<header id = "header">
	<div class="d-flex flex-column">
		<div class ="profile">
			<img src = "resources/img/ssong.PNG" alt class = "img-circle">
			<h1 class = "text-light">
				<a href = "#" class = "onMember">  <%= email.toUpperCase()%> </a>
			</h1>
		</div>
		<div class ="admin_inout">
			<button type="button" class ="grade">ADMIN</button>
			<button type="button" class ="grade" onclick="location.href='loginForm.me'">LOGOUT</button>
		</div>
						
		<ul>
			<li>
				<a href = "#" class = "nav-menu"><i class= "far fa-clipboard menu"></i><span class="a-menu"> 게시물관리</span></a>
			</li>
			<li>
				<a href="admin_main.me" class = "nav-menu"><i class = "fas fa-users menu"></i><span class="a-menu"> 회원관리</span></a>
			</li>
			<li>
				<a href="admin_pay.me" class = "nav-menu"><i class = "fas fa-ticket-alt menu"></i><span class="a-menu"> 이용권관리</span></a>
			</li>
			<li>
				<a href="#" class = "nav-menu"><i class = "fas fa-dog menu"></i><span class="a-menu"> 책임분양</span></a>
			</li>
		</ul>
</div>
</header>

<div class = "main_content">
<!-- 메인컨텐트 -->
<div class="container">

					<div class="search_bar">
						<form method="post" id="find_form">
							<div class="search_area1">
								<div class="search_item1">
									<label for="date">기간 :</label> <input type="text"
										id="startDate" name="datepicker1"> - <input
										type="text" id="endDate" name="datepicker2">
								</div>
								<div class="search_item2">
									<div class="search_item2 1">
										
										분류 <input type='checkbox' name='kind' id='kind' value='writing'
											onclick='checkOnlyOne(this)' /> 게시글 
										 <input	type='checkbox' name='kind'id='kind' value='warning''
											onclick='checkOnlyOne(this)' /> 신고
									</div>
									

									<div class="search_item2 2">
										카테고리 : <select id="kategorie">
											<option value="">선택하세요</option>
											<option value="adopt_list">분양게시판</option>
											<option value="community" checked>커뮤니티</option>
										</select>
									</div>
								</div>
								<div class="search_item3">
									검색어 : <input type="text" id="keyword" name="keyword" value="">
								</div>
							</div>
							<div class="search_are2">
								<input type="button" id="find_data" value="조회">
							</div>
						</form>
					</div>
					<div class="search_lists">
						<table id="output"></table>

					</div>
				</div>

</div><!-- 메인컨텐트 끝 -->
<!-- right-sidebar -->
<div class="right-container">
<!-- 달력 --> 
<widget class="no-drag">
  <table id="calendar">
    <thead>
      <tr height="35px">
        <td><label onclick="prev()" style="color: #ccc;"><</label></td>
        <td colspan="5" id="monthTable"></td>
        <td><label onclick="next()" style="color: #ccc;">></label></td>
      </tr>
      <tr id="dateHead">
        <td>S</td>
        <td>M</td>
        <td>T</td>
        <td>W</td>
        <td>T</td>
        <td>F</td>
        <td>S</td>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
  <script>
    makeArray();
  </script>
</widget>
<!-- 투두리스트 -->
	<!-- 입력 -->
	<div class = "todolist">
      <input type="text" placeholder="Add A Task" class ="txt"> 
    <!-- to do list -->
      <div class="notdone">
        <h3>To Do List</h3>
      </div>
	<!-- done list -->      
      <div class="done">
        <h3>Done</h3>
      </div>
     </div>

<!-- pageup button -->
<div class ="back-to-top">
<a href="#" class ="back-to-top" style="display: inline;">
	<i class = "fas fa-angle-up"></i>
</a>
</div>
</div><!-- right-sidebar -->
</div><!-- 바디컨텐트 -->

<!-- 모달 내용 -->
	<form id="detail_form" class="modal">
	<div class="admin_b_view">
		<h3>상세보기</h3>
		<div class="modal_view side">
			<div class="admin_nav">
				<label>아이디</label><input type="text" id ="email" readonly>
				<label>닉네임</label><input type="text" id ="nick" readonly>
				<label>지역</label><input type="text" id ="local" readonly>
				<label>회원분류</label><input type="text" id ="grade" readonly>
				<br/>
				<label>글번호</label><input type="text" id ="num" readonly>
				<label>카테고리</label><input type="text" id ="category" readonly>
				<label>작성일</label><input type="text" id ="c_date" readonly>
				<label>조회수</label><input type="text" id ="readcount" readonly>
				<label>제목</label><input type="text" id ="subject" readonly>
				<label>내 용</label><input type="text" id ="c_content" readonly>
				
		
		</div> 
	</div>
	<!--admin_nav  -->

	<div class ="commentlist">
		<h3> comment</h3>
		<div class="c-table">
		</div>
	</div> 
	<!-- commentlist -->
		
		<!-- 사이드 -->
	<div class="modal_view side">
		<div class ="warninglist">
			<h3>warning</h3>
			<div class="w-table">
			
			</div>
		</div>	
	</div>
	<!-- warninglist -->
	
	
	</div>

	</form>

<!-- #modal 끝 -->
</body>
</html>