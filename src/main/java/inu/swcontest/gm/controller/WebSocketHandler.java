package inu.swcontest.gm.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
    private static volatile String lastMessage = "";
    private static volatile boolean webSocketStatus = false;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        lastMessage = payload;
        System.out.println("received = " + payload + " : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        CLIENTS.get(session.getId()).sendMessage(message);
    }

    /**
     * <afterConnectionEstablished>
     * WebSocket 연결이 성공적으로 수립된 후에 호출되는 콜백 메소드
     * 클라이언트가 서버에 연결된 직후에 수행해야 하는 작업을 정의하는데 사용
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " 연결 됨");
        webSocketStatus = true;
        CLIENTS.put(session.getId(), session);
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
        webSocketStatus = false;
        CLIENTS.remove(session.getId());
    }

    public static String getLastMessage() {
        System.out.println("CLIENTS = " + CLIENTS);
        System.out.println("lastMessage = " + lastMessage);

        // total epoch:recent epoch:time
        String[] data = lastMessage.split(":");

        int totalEpoch = Integer.parseInt(data[0]);
        int recentEpoch = Integer.parseInt(data[1]);
        double avgEpoch = ((double) recentEpoch / totalEpoch) * 100;
        double elapsedTime = Double.parseDouble(data[2]);
        double expectTime = elapsedTime * totalEpoch;

        String response = totalEpoch + " " + recentEpoch + " " + String.format("%.2f", avgEpoch) + " " + elapsedTime + " " + expectTime;
        System.out.println("response = " + response);
        return response;
    }

    public static boolean getWebSocketStatus() {
        System.out.println("webSocketStatus = " + webSocketStatus);
        return webSocketStatus;
    }
}
