<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page session="true" %>
    <%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("id")== null){
    	out.println("<script>");
    	out.println("location.href='loginForm.do'");
    	out.println("</script>");
    }
    String id = (String)session.getAttribute("id");
    String name =(String)session.getAttribute("name");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WebSocket</title>
<script>
	var log= function(s){
		document.getElementById("output").textContent += (s + "\n");
	}
	
	
	w = new WebSocket("ws://localhost:8080/springwebsocket2/broadcasting.do");
	w.onopen = function(){
		alert('WebSocket Connected!!!');
		//ws웹소켓으로 변환이 되었다 
		//ws://localhost:8080/springwebsocket2/broadcasting.do url이 주어지면 서블렛 :소켓핸들러가 실행 &http에서 웹프로토콜로 전환이 필요하기에 핸드쉐이크인터셉터의 비포핸쉐->애프터해쉔실행 ->소캣핸들이 실행된다 (서블렛) ::웹소켓 변환작업이 성공!
		
		//on__methos 해당 관련된 이벤트가 실행되면 자동 호출된다
	}
	w.onmessage = function(e){  //semdmessage()를 전달 받는다 (서버에서 클로 뿌려준다)
		log(e.data.toString());
	}
	w.onclose = function(e){
		log("WebSocket Closed");
	}
	w.onerror = function(e){
	    log('WebSocket error : ' + e.data);
	  }
	  
	  window.onload = function(){
		document.getElementById("send_button").onclick =
			function(){
			var input = 
			document.getElementById("input").value;
			w.send("<%=id%>"+ ">" + input);  
			document.getElementById("input").value = "";
		}// w.send() 클->서버로
	}
</script>
</head>
<body>
	<input type='text' id='input' placeholder='input message...'size='55'/>
	<button id='send_button' >SEND</button>
	대화명 : <%=id %>
	<p/>
	<textarea id='output' readonly rows='30' cols='80'></textarea>
</body>
</html>