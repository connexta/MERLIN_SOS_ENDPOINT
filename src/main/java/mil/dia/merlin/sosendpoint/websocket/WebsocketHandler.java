package mil.dia.merlin.sosendpoint.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
class WebsocketHandler extends TextWebSocketHandler {
    private Map<String, Set<WebSocketSession>> sessionMap;

    public WebsocketHandler(Map<String, Set<WebSocketSession>> sessionMap) {
        this.sessionMap = sessionMap;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session == null) {
            return;
        }

        String id = session.getId();

        if (sessionMap.containsKey(id)) {
            sessionMap.remove(id);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (session == null) {
            return;
        }

        String id = session.getId();

        if (!sessionMap.containsKey(id)) {
            sessionMap.put(id, new HashSet<WebSocketSession>());
        }

        sessionMap.get(id).add(session);
    }
}
