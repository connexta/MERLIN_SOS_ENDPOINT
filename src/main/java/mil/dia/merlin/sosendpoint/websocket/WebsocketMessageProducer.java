package mil.dia.merlin.sosendpoint.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Component
class WebsocketMessageProducer implements Consumer<String> {
    private Logger logger = LoggerFactory.getLogger(WebsocketMessageProducer.class);

    private Map<String, Set<WebSocketSession>> sessionMap;

    public WebsocketMessageProducer(Map<String, Set<WebSocketSession>> sessionMap) {
        this.sessionMap = sessionMap;
    }

    @Override
    public void accept(String messageText) {
        for (Set<WebSocketSession> sessionSet : sessionMap.values()) {
            sessionSet.stream()
                    .forEach(session -> sendMessage(session, messageText));
        }
    }

    private void sendMessage(WebSocketSession session, String messageText) {
        try {
            session.sendMessage(new TextMessage(messageText));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
