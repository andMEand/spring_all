package com.spring.springwebsocket2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//http���γ��� >>websoket ���� �� !!����ϴ� ����!!
public class SocketHandler extends TextWebSocketHandler {
	Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	
	public SocketHandler() {
		super();
	}
	//���ᰴü�� ���� �� �� �۾�
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)throws Exception{
		super.afterConnectionClosed(session, status);
		sessionSet.remove(session);
		
	}
	//���ἳ���� �Ǿ����� ����
	public void afterConnectionEstablished(WebSocketSession session )throws Exception{
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		
	}
	
	//*** �޼��� ó��// �׷���Ȳ�� �Ǹ� �ڵ����� ȣ���� �ȴ� �ٸ�Ŭ������ ������� �ʴ���/Ŭ���� �޼��� ������ ����ȴ�! session�����Ͽ� ���� ���ǰ�ü�� ���޹޴´� 
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)throws Exception{
		System.out.println("�޽���" + message);
		super.handleMessage(session,message);
		
		// session.getAttributes(); �����ڵ彦��ů���� �ʿ� ������ �������� �о�ͼ� map�� �����Ѵ��ϰ�  userId�� �о�ͼ� userId�� ����
		Map<String, Object> map = session.getAttributes();
		String userId = (String) map.get("userId");
		System.out.println("userId : " + userId);
		//userId ���� �������� ������� ���� ����ϰ� ������ ����ϸ�Ǳ⶧���� �����س���
		
		// this.sessionSet)���ǰ�ü�� �����ϱ� ���� sessionSet:���⿡ ���ǰ�ü�� ����ȴ�
		for(WebSocketSession client_session : this.sessionSet) {
			
			//������ �����ִ��� Ȯ��
			if(client_session.isOpen()) {
				System.out.println("�޽��� " + message);
				try {
					client_session.sendMessage(message); //***sendMessage() Ŭ���̾�Ʈ�� ���Ҥ�Ĺ�� ���� ������ �����͸�  ��� Ŭ���̾�Ʈ���� �������ֱ� ���� ���
				}catch(Exception ignored) {
					System.out.println("fail to send message! "+ ignored);
				}
			}
		}
	}
	public void handleTeansportError (WebSocketSession session,Throwable exception)throws Exception{
		System.out.println("web socket error!"+ exception);
	}
	public boolean supportPartialMessage() {  //�ʹ� ��� �߶� ������ �۾� /�� ����� ���� ���� ��
		System.out.println("call method!");
		return false;
	}

}
