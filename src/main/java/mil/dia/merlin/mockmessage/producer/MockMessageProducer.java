package mil.dia.merlin.mockmessage.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
class MockMessageProducer {
    private KafkaTemplate<Integer, String> kafkaTemplate;
    private int count = 0;

    @Value("${mil.afdcgs.merlin.mockmessage.target-topic}")
    private String targetTopic;

    public MockMessageProducer(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void produceMessage() {
        kafkaTemplate.send(targetTopic, count, "Message #" + count);
    }
}
