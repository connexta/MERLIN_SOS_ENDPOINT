package mil.dia.merlin.sosendpoint.web;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sos")
class SosEndpointResource {
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
}
