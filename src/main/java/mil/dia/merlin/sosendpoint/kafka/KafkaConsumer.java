package mil.dia.merlin.sosendpoint.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
class KafkaConsumer {

    private SimpMessagingTemplate simpMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public KafkaConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @KafkaListener(id = "sos-endpoint-sensors", topics = "merlin-sensors-json")
    public void listenSensors(String in) {
        logger.info("received sensor from kafka: " + in);
        simpMessagingTemplate.convertAndSend("/topic/sensors", in);
    }

    @KafkaListener(id = "sos-endpoint-observations", topics = "merlin-observations-json")
    public void listenObservations(String in) {
        logger.info("received observation from kafka: " + in);
        simpMessagingTemplate.convertAndSend("/topic/observations", in);
    }
}
