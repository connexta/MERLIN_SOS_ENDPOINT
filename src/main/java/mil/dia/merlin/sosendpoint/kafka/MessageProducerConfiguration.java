package mil.dia.merlin.sosendpoint.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
class MessageProducerConfiguration {
    @Value("${mil.afdcgs.merlin.mockmessage.kafka.bootstrap-server}")
    private String bootstrapServer;

    @Value("${mil.afdcgs.merlin.sos.kafka.partition-count:1}")
    private Integer partitionCount;

    @Value("${mil.afdcgs.merlin.sos.kafka.replica-count:1}")
    private Integer replicaCount;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic sensorInput() {
        return TopicBuilder.name("merlin-sensor-input")
                .partitions(partitionCount)
                .replicas(replicaCount)
                .build();
    }

    @Bean
    public NewTopic observationInput() {
        return TopicBuilder.name("merlin-observation-input")
                .partitions(partitionCount)
                .replicas(replicaCount)
                .build();
    }

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate<Integer, String>(producerFactory);
    }
}
