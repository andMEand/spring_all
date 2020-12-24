package com.spring.springwebsocket2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//http프로노톨 >>websoket 연결 후 !!통신하는 과정!!
public class SocketHandler extends TextWebSocketHandler {
	Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	
	public SocketHandler() {
		super();
	}
	//연결객체가 닫힌 후 할 작업
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)throws Exception{
		super.afterConnectionClosed(session, status);
		sessionSet.remove(session);
		
	}
	//연결설정이 되었을땐 수행
	public void afterConnectionEstablished(WebSocketSession session )throws Exception{
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		
	}
	
	//*** 메세지 처리// 그런상황이 되면 자동으로 호출이 된다 다른클래스에 명시하지 않느다/클에서 메세지 보낼때 실행된다! session웹소켓에 대한 세션객체도 전달받는다 
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)throws Exception{
		System.out.println("메시지" + message);
		super.handleMessage(session,message);
		
		// session.getAttributes(); 비포핸드쉐이킁에서 맵에 저장한 내용으로 읽어와서 map에 저장한다하고  userId를 읽어와서 userId에 저장
		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
		System.out.println("userId : " + userId);
		//userId 현재 페이지는 사용하지 않음 사용하고 싶을때 사용하면되기때문에 생성해놓음
		
		// this.sessionSet)세션객체를 관리하기 위한 sessionSet:여기에 세션객체가 저장된다
		for(WebSocketSession client_session : this.sessionSet) {
			
			//세션이 열려있는지 확인
			if(client_session.isOpen()) {
				System.out.println("메시지 " + message);
				try {
					client_session.sendMessage(message); //***sendMessage() 클라이언트가 웹소ㄱ캣을 통해 보내온 데이터를  모든 클라이언트에세 ㅃ무려주기 위해 사용
				}catch(Exception ignored) {
					System.out.println("fail to send message! "+ ignored);
				}
			}
		}
	}
	public void handleTeansportError (WebSocketSession session,Throwable exception)throws Exception{
		System.out.println("web socket error!"+ exception);
	}
	public boolean supportPartialMessage() {  //너무 길면 잘라서 보내는 작업 /잘 사용할 일은 없을 것
		System.out.println("call method!");
		return false;
	}

}
