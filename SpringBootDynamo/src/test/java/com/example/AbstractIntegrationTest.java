package com.example;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

    private static final String DOCKER_IMAGE = "amazon/dynamodb-local";
    private static final String DOCKER_TAG = "latest";
    private static final int EXPOSED_PORT = 8000;

    @ClassRule
    public static GenericContainer dynamodb =
            new GenericContainer(String.format("%s:%s", DOCKER_IMAGE, DOCKER_TAG)).withExposedPorts(EXPOSED_PORT);

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String endpoint = String.format("aws.dynamo.endpoint=http://%s:%s",
                    dynamodb.getContainerIpAddress(),
                    dynamodb.getMappedPort(EXPOSED_PORT));

            TestPropertyValues.of(endpoint).applyTo(configurableApplicationContext);
        }
    }
}
