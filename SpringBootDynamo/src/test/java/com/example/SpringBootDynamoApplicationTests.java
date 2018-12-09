package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDynamoApplicationTests {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Before
    public void setup() {
        dynamoDbClient.createTable(CreateTableRequest.builder()
                .tableName("test")
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(5L)
                        .writeCapacityUnits(5L)
                        .build())
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("id")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .build());

        System.out.println("==== print table ====");
        dynamoDbClient.listTables().tableNames().forEach(System.out::println);
    }

    @Test
    public void tbd() {
        // tdb
    }

    @After
    public void clear() {
        dynamoDbClient.deleteTable(DeleteTableRequest.builder()
                .tableName("test")
                .build());
    }

    @TestConfiguration
    static class Config {

        @Bean
        public DynamoDbClient dynamoDbClient() throws URISyntaxException {
            return DynamoDbClient.builder()
                    .endpointOverride(new URI("http://localhost:8000"))
                    .region(Region.AP_SOUTH_1)
                    .credentialsProvider(() -> new AwsCredentials() {
                        @Override
                        public String accessKeyId() {
                            return "accessKeyId";
                        }

                        @Override
                        public String secretAccessKey() {
                            return "secretAccessKey";
                        }
                    })
                    .build();

        }
    }

}