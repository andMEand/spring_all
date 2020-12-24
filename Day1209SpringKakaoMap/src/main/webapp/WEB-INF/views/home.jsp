<%@ page language = "java" contentType = "text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="utf-8">
	<title>클릭한 위치에 마커 표시하기</title>
</head>
<body>
<div id="map" style="width:100%; height:350px"></div>
<p><em>지도를 클릭해주세요!</em></p>
<div id="clickLatlng"></div>

<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=78677a9982a26fe34a330a8d59d8fa65"></script>
<script>
	var mapContainer = document.getElementById('map'), //지도를 표시할 div
	mapOption ={
			center:new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심
			level:3 //지도의 확대 레벨
	};
	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	//지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({
		//지도 중심 좌표에 마커를 생성힙니다
	position: map.getCenter()
	});
	//지도에 마커를 표시합니다
	marker.setMap(map);
	
	//지도에 클릭 이벤트를 등록합니다
	//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	kakao.maps.event.addListener(map,'click',function(mouseEvent){
		
		//클릭한 위도, 경도 정보를 가져옵니다
		var latlng = mouseEvent.latLng;
		
		//마커위치를 클학한 위치로 옮깁니다
		marker.setPosition(latlng);
		
		var message ='클릭한 위치의 위도는' + latlng.getLat() + ' 이고, ';
		message +=  '경도는'+latlng.getLng()+'입니다';
		
		var resultDiv = document.getElementById('clickLatlng');
		resultDiv.innerHTML = message;
	});
	
</script>
</body>
</html>
