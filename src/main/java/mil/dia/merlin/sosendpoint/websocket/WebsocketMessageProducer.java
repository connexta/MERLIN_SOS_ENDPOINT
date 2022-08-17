package mil.dia.merlin.sosendpoint.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.net.http.WebSocket;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Component
class WebsocketMessageProducer implements Consumer<String> {
    Map<String, Set<WebSocketSession>> sessionMap;

    public WebsocketMessageProducer(Map<String, Set<WebSocketSession>> sessionMap) {
        this.sessionMap = sessionMap;
    }

    @Override
    public void accept(String messageText) {
//        for (Set<WebSocketSession> sessionSet : sessionMap.values()) {
//            sessionSet.stream()
//                    .forEach(s -> s.sendMessage(new TextMessage(messageText));
//        }
    }


}
