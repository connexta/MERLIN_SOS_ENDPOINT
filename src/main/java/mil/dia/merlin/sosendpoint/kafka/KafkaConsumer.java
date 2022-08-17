package mil.dia.merlin.sosendpoint.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
class KafkaConsumer {
    private Consumer<String> consumeFunction;

    public KafkaConsumer(Consumer<String> consumeFunction) {
        this.consumeFunction = consumeFunction;
    }

    @KafkaListener(topics = "merlin-observations-xml")
    public void listen(String in) {
        consumeFunction.accept(in);
    }
}
