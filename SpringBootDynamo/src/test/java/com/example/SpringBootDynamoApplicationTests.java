package com.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SpringBootDynamoApplicationTests extends AbstractIntegrationTest {

    private static final String TABLE_NAME = "test";

    @Autowired
    private AmazonDynamoDB dynamoDB;

    @Before
    public void setup() {
        dynamoDB.createTable(new CreateTableRequest()
                .withTableName(TABLE_NAME)
                .withAttributeDefinitions(Arrays.asList(
                        new AttributeDefinition("id", ScalarAttributeType.S)
                ))
                .withKeySchema(Arrays.asList(
                        new KeySchemaElement("id", KeyType.HASH)
                ))
                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L))
        );
    }

    @Test
    public void 아이템_삽입_후_스캔_데이터_확인_성공() {
        // given
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", new AttributeValue().withS("1"));
        item.put("name", new AttributeValue().withS("wonchul"));
        item.put("birth_year", new AttributeValue().withN("1992"));

        // when
        dynamoDB.putItem(new PutItemRequest().withTableName(TABLE_NAME).withItem(item));

        // then
        ScanResult result = dynamoDB.scan(new ScanRequest(TABLE_NAME));
        assertThat(result.getCount()).isEqualTo(1);
        assertThat(result.getItems().get(0).get("id").getS()).isEqualTo("1");
        assertThat(result.getItems().get(0).get("name").getS()).isEqualTo("wonchul");
        assertThat(result.getItems().get(0).get("birth_year").getN()).isEqualTo("1992");
    }

    @After
    public void clear() {
        dynamoDB.deleteTable("test");
    }

}