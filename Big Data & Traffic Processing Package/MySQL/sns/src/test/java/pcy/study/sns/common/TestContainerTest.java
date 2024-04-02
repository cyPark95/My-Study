package pcy.study.sns.common;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class TestContainerTest {

    @ClassRule
    @Container
    public static GenericContainer<?> RABBIT_MQ_CONTAINER = new GenericContainer<>(DockerImageName.parse("rabbitmq:3-alpine"))
            .withExposedPorts(5672);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.rabbitmq.host", () -> RABBIT_MQ_CONTAINER.getHost());
        dynamicPropertyRegistry.add("spring.rabbitmq.port", () -> RABBIT_MQ_CONTAINER.getMappedPort(5672).toString());
    }
}
