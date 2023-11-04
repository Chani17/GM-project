package inu.swcontest.gm.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * <afterConnectionEstablished>
     * WebSocket 연결이 성공적으로 수립된 후에 호출되는 콜백 메소드
     * 클라이언트가 서버에 연결된 직후에 수행해야 하는 작업을 정의하는데 사용
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " 연결 됨");
    }

    /**
     * <afterConnectionClosed>
     * WebSocket 연결이 닫힌 후에 호출되는 콜백 메소드
     * 클라이언트와 서버 사이의 연결이 종료된 후 수행해야 하는 작업을 정의하는데 사용
     * @param session : 연결된 WebSocket을 나타내는 객체
     * @param status : WebSocket 연결이 종료된 이유를 나타내는 객체
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session.getId() + " 연결 끊김");
        System.out.println("status.getReason() = " + status.getReason());
    }
}
