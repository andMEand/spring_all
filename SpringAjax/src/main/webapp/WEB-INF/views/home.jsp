<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>데이터 베이스 연동</title>
<style type="text/css">

form {
	width: 500px;
	margin: 10px auto;
}

ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

ul li {
	padding: 0;
	margin: 0, 0, 10px, 0;
}

label {
	width: 150px;
	float: left;
}

table {
	border: 1px solid gray;
	width: 500px;
	margin: 0 auto;
	border-collapse: collapse;
}

td {
	border: 1px solid gray;
}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
//목록
//#중복된 값X 사용, 유일하게 식벽하기 위해 사용 /  클래스는 중복값 사용
function selectData(){
	$('#output').empty();//table내부 내용을 제거 (초기화)
	//작업한 결과 데이터를 가지고 와서 원하는 위치에 데이터를 뿌려주는 역할 / 통신비용이 적게든다
	$.ajax({  
		url:'/ajax/getPeopleJson.do',   //이동할 문서 주소
		type:'POST',   //파라미터를 보내는 방식 게 or 포스트
		//datatype :'json',//서버에서 보내줄 데이터 타입
		
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
		//
		success:function(data){  //peoplevo 데이터를 가지고 있다
			$.each(data, function(index, item){
				var output = '';
				output +='<tr>';
				output +='<td>' + item.id + '</td>';
				output +='<td>' + item.name + '</td>';
				output +='<td>'+ item.job + '</td>';
				output +='<td>'+ item.address + '</td>';
				output +='<td>'+ item.bloodtype + '</td>';
				output +='<td><a href="/ajax/deletePeople.do" ';
				output +='class="delete_data"';
				output +='id='+ item.id + '>삭제</a></td>';
				output +='</tr>';
				console.log("output:" + output);
				$('#output').append(output);
			});
		},
		error:function(){
			alert("ajax통신 실패!!!1");
		}
	});// 여기까지 에이젝스
}

$(document).ready(function(){  //로딩이 끝나면 함수 호출 /온로드와 비슷한 개념
	$("#input_data").click(function(event){  //input_data에 대해 클릭이 생기면 이벤트함수 호출
		var params =$('#insert_form').serialize();  //insert_form 네임과 밸루 를 짝으로 해서 직렬화 (하나의 문자열) 네임콜롬값이 하나의 문자열이 되어 저장한다 
		alert(params);
		jQuery.ajax({  //$.ajax 같은 표현식
			url: '/ajax/insertPeople.do',
			type: 'POST',
			data: params, //서버로 보낼 데이터
			/*(참고) 파일 첨부시 필요함
			-cache :fals 로 선언 시 ajax로 통신 중 cache가 남아서 갱신된 데이처를 받아오지 못할 경우를 대비
			-contentType :false 로 선언시 content-Type헤더가 multipart/form-data 로 전송되게 함
			-pocessData : false 로 선언시  formData를 string으로 반환하지 않음
			*/
			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
			dataType:'json',//서버에서 보내줄 데이터 타입 /키 밸류 구조의 제이슨 타입
			success: function(retVal){//해쉬맥 객체
				if(retVal.res == "OK"){
					//데이터 성공일때 이벤트 작성
					selectData();
					//초기화
					$('#id').val('');
					$('#name').val('');
					$('#job').val('');
					$('#address').val('');
					$('#bloodtype').val('');
				}
				else{
					alert("Insert Fail!!! 힝;ㅅ");
				}
				
			},
			error:function(){
				alert("ajax 통신실패!!!!2");
			}
		});
	//기본 이벤트 제거
	event.preventDefault();  // 또다른 방법 retrun false;
	});
	
	
	$(document).on('click', '.delete_data', function(event){//delete_data에 이번트 등록  /동적인 이벤트
		jQuery.ajax({
			url: $(this).attr("href"),
			type: 'POST',
			data :{'id': $(this).attr("id")},  //키 밸류 구죠(제이슨)//this클릭이란 이벤트가 발생한 그 객체!
			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
			dataType:'json', 
			success:function(retVal){
				if(retVal.res == "no"){//데이터 성공일 때 이벤트 작성
					selectData();
				}
				else{
					alert("delete Fail!!!");
				}
			},
			error:function(){
				alert("ajax 통신 실패!!");
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	selectData();
});
</script>
</head>
<body>
<form id="insert_form"method="post">
<fieldset>
	<legend>데이터 추가</legend>
	<ul>
	<li>
	<label for="id">아이디</label>
	<input type="text" name="id" id="id">
	</li>
	<li>
	<label for="name" >이름	</label>
	<input type="text"name="name"id="name">
	</li>
	<li>
	<label for="job" >직업	</label>
	<input type="text"name="job"id="job">
	</li>
	<li>
	<label for="address" >주소	</label>
	<input type="text"name="address"id="address">
	</li>
	<li>
	<label for="bloodtype" >혈액형	</label>
	<input type="text"name="bloodtype"id="bloodtype">
	</li><br>
	<li align="center">
	<input type="button"value="추가" id="input_data" >
	</li>
	
	</ul>
</fieldset>
</form >
<form id="update_form"method="post">
<table id="output"></table>
</form>
</body>
</html>