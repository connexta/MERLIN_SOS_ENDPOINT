package mil.dia.merlin.sosendpoint.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
class KafkaConsumer {

    private SimpMessagingTemplate simpMessagingTemplate;

    public KafkaConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(id = "sos-endpoint-sensors", topics = "merlin-sensors-xml")
    public void listenSensors(String in) {
        simpMessagingTemplate.convertAndSend("/topic/semsors", in);
    }

    @KafkaListener(id = "sos-endpoint-observations", topics = "merlin-observations-xml")
    public void listenObservations(String in) {
        simpMessagingTemplate.convertAndSend("/topic/observations", in);
    }
}
