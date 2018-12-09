package com.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class SpringBootDynamoApplicationTests extends AbstractIntegrationTest {

    @Autowired
    private AmazonDynamoDB dynamoDB;

    @Before
    public void setup() {
        dynamoDB.createTable(new CreateTableRequest(
                Collections.singletonList(new AttributeDefinition("id", ScalarAttributeType.S)),
                "test",
                Collections.singletonList(new KeySchemaElement("id", KeyType.HASH)),
                new ProvisionedThroughput(5L, 5L))
        );

        System.out.println("==== print table ====");
        dynamoDB.listTables().getTableNames().forEach(System.out::println);
    }

    @Test
    public void tbd() {
        // tdb
    }

    @After
    public void clear() {
        dynamoDB.deleteTable("test");
    }

}