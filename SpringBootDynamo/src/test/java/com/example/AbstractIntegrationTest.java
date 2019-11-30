package com.example;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
public abstract class AbstractIntegrationTest {

    private static final String DOCKER_IMAGE = "amazon/dynamodb-local";
    private static final String DOCKER_TAG = "latest";
    private static final int EXPOSED_PORT = 8000;

    static {
        GenericContainer dynamodb =
                new GenericContainer(String.format("%s:%s", DOCKER_IMAGE, DOCKER_TAG)).withExposedPorts(EXPOSED_PORT);
        dynamodb.start();

        final String endpoint = String.format("http://%s:%s",
                dynamodb.getContainerIpAddress(),
                dynamodb.getMappedPort(EXPOSED_PORT));
        System.setProperty("aws.dynamo.endpoint", endpoint);
    }
}
