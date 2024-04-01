package pcy.study.sns.config;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class RabbitMqTestContainer {

    private static final String RABBIT_MQ_DOCKER_IMAGE = "rabbitmq:3-alpine";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1q2w3e4r!";

    static{
        GenericContainer<?> RABBIT_MQ_CONTAINER = new GenericContainer<>(DockerImageName.parse(RABBIT_MQ_DOCKER_IMAGE))
                .withExposedPorts(5672)
                .withEnv("RABBITMQ_DEFAULT_USER", USERNAME)
                .withEnv("RABBITMQ_DEFAULT_PASS", PASSWORD);
        RABBIT_MQ_CONTAINER.start();

        System.setProperty("spring.rabbitmq.host", RABBIT_MQ_CONTAINER.getHost());
        System.setProperty("spring.rabbitmq.port", RABBIT_MQ_CONTAINER.getMappedPort(5672).toString());
        System.setProperty("spring.rabbitmq.username", USERNAME);
        System.setProperty("spring.rabbitmq.password", PASSWORD);
    }
}
