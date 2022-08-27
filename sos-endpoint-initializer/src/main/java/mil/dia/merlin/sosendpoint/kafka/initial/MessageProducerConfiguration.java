package mil.dia.merlin.sosendpoint.kafka.initial;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
class MessageProducerConfiguration {
    @Value("${mil.dia.merlin.mockmessage.kafka.bootstrap-server}")
    private String bootstrapServer;

    @Value("${mil.dia.merlin.sos.kafka.partition-count:1}")
    private Integer partitionCount;

    @Value("${mil.dia.merlin.sos.kafka.replica-count:1}")
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
    public NewTopic sensorJson() {
        return TopicBuilder.name("merlin-sensors-ui")
                .partitions(partitionCount)
                .replicas(replicaCount)
                .build();
    }

    @Bean
    public NewTopic observationJson() {
        return TopicBuilder.name("merlin-observations-ui")
                .partitions(partitionCount)
                .replicas(replicaCount)
                .build();
    }
}
