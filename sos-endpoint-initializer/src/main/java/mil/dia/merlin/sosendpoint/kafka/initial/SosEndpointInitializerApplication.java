package mil.dia.merlin.sosendpoint.kafka.initial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SosEndpointInitializerApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(SosEndpointInitializerApplication.class);

    public static void main(String... args) {
        SpringApplication.run(SosEndpointInitializerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Completed creating topics.");
    }
}
