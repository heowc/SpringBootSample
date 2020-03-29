package com.example;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public abstract class AbstractIntegrationTest {

    private static final GenericContainer dynamodb =
            new GenericContainer("amazon/dynamodb-local:latest").withExposedPorts(8000);

    static {
        dynamodb.start();
    }

    @DynamicPropertySource
    static void dynamoDbProperties(DynamicPropertyRegistry registry) {
        final String endpoint = String.format("http://%s:%s",
                dynamodb.getContainerIpAddress(),
                dynamodb.getMappedPort(8000));
        registry.add("aws.dynamo.endpoint", () -> endpoint);
    }


}
