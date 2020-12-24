package com.spring.springwebsocket2;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
  //���� �������̵��� �޼ҵ� ����
  
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
      WebSocketHandler wshandler, Map<String, Object> map) throws Exception{
    //���� �Ķ���� ��, attributes �� ���� �����ϸ� ������ �ڵ鷯 Ŭ������ websocketSession�� ���޵ȴ�
    
    System.out.println("Before Handshake");
    
    ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;//������ �޾ƿ��� ���� ó�� http��������->��������������
    System.out.println("URI : "+ request.getURI());
    
    HttpServletRequest req = ssreq.getServletRequest();
    
    /*
     * String name = (String) req.getSession().getAttribute("name");
     * map.put("userName", name);
     * System.out.println("HttpSession�� ����� name : "+ name);
     */
    String id = (String)req.getSession().getAttribute("id");  //���ǰ�ü ���̵�
    map.put("userId", id);//userId�� �̸��� id����
    System.out.println("HttpSession�� ����� id : " + id);
    
    return super.beforeHandshake(request, response, wshandler, map);
    } // beforehandshake
  
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wshandler, Exception ex) {
    System.out.println("After Handshake");
    super.afterHandshake(request, response, wshandler, ex);
  }
}