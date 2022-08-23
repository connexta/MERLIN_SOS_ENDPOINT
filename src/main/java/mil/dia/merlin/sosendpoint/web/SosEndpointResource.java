package mil.dia.merlin.sosendpoint.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sos")
class SosEndpointResource {
    private Logger logger = LoggerFactory.getLogger(SosEndpointResource.class);

    private KafkaTemplate<Integer, String> kafkaTemplate;

    public SosEndpointResource(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/sensor")
    public String createSensor(@RequestBody String sensor) {
        kafkaTemplate.send("merlin-sensor-input", sensor);
        return "sensor message successfully processed";
    }

    @PostMapping("/observation")
    public String createObservation(@RequestBody String observation) {
        kafkaTemplate.send("merlin-observation-input", observation);
        return "observation message successfully processed";
    }

    @MessageMapping("/observations")
    @SendTo("/topic/observations")
    public String observation(@RequestBody String observationText) {
        logger.info(observationText);
        return observationText;
    }

    @MessageMapping("/sensors")
    @SendTo("/topic/sensors")
    public String sensor(@RequestBody String sensorText) {
        logger.info(sensorText);
        return sensorText;
    }
}
