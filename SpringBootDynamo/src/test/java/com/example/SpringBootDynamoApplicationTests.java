package com.example;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

public class SpringBootDynamoApplicationTests extends AbstractIntegrationTest {

    private static final String TABLE_NAME = "test";

    @Autowired
    private DynamoDbClient dynamoDB;

    @BeforeEach
    public void setup() {
        dynamoDB.createTable(
                CreateTableRequest.builder()
                                  .tableName(TABLE_NAME)
                                  .attributeDefinitions(AttributeDefinition.builder()
                                                                           .attributeName("id")
                                                                           .attributeType(ScalarAttributeType.S)
                                                                           .build())
                                  .keySchema(KeySchemaElement.builder()
                                                             .attributeName("id")
                                                             .keyType(KeyType.HASH)
                                                             .build())
                                  .provisionedThroughput(ProvisionedThroughput.builder()
                                                                              .readCapacityUnits(5L)
                                                                              .writeCapacityUnits(5L)
                                                                              .build())
                                  .build());
    }

    @Test
    public void 아이템_삽입_후_스캔_데이터_확인_성공() {
        // given
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s("1").build());
        item.put("name", AttributeValue.builder().s("wonchul").build());
        item.put("birth_year", AttributeValue.builder().n("1992").build());

        // when
        dynamoDB.putItem(PutItemRequest.builder()
                                       .tableName(TABLE_NAME)
                                       .item(item)
                                       .build());

        // then

        ScanResponse response = dynamoDB.scan(ScanRequest.builder().tableName(TABLE_NAME).build());
        assertThat(response.count()).isEqualTo(1);
        assertThat(response.items().get(0).get("id").s()).isEqualTo("1");
        assertThat(response.items().get(0).get("name").s()).isEqualTo("wonchul");
        assertThat(response.items().get(0).get("birth_year").n()).isEqualTo("1992");
    }

    @Test
    public void 아이템_삽입_후_해당_데이터_검색_확인_성공() {
        // given
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s("1").build());
        item.put("name", AttributeValue.builder().s("wonchul").build());
        item.put("birth_year", AttributeValue.builder().n("1992").build());
        dynamoDB.putItem(PutItemRequest.builder()
                                       .tableName(TABLE_NAME)
                                       .item(item)
                                       .build());

        // when
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s("1").build());
        GetItemResponse response = dynamoDB.getItem(GetItemRequest.builder()
                                                                  .tableName(TABLE_NAME)
                                                                  .key(key)
                                                                  .build());

        // then
        assertThat(response).isNotNull();
        assertThat(response.item()).isNotNull();
        assertThat(response.item().get("id").s()).isEqualTo("1");
        assertThat(response.item().get("name").s()).isEqualTo("wonchul");
        assertThat(response.item().get("birth_year").n()).isEqualTo("1992");
    }

    @Test
    public void 아이템_삽입_후_해당_데이터_수정_성공() {
        // given
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s("1").build());
        item.put("name", AttributeValue.builder().s("wonchul").build());
        item.put("birth_year", AttributeValue.builder().n("1992").build());
        dynamoDB.putItem(PutItemRequest.builder()
                                       .tableName(TABLE_NAME)
                                       .item(item)
                                       .build());

        // when
        Map<String, AttributeValue> updateKey = new HashMap<>();
        updateKey.put("id", AttributeValue.builder().s("1").build());

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#N", "name");
        nameMap.put("#BY", "birth_year");

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":name", AttributeValue.builder().s("WONCHUL").build());
        valueMap.put(":birth_year", AttributeValue.builder().n("1992").build());
        dynamoDB.updateItem(UpdateItemRequest.builder()
                                             .tableName(TABLE_NAME)
                                             .key(updateKey)
                                             .updateExpression("set #N = :name, #BY = :birth_year")
                                             .expressionAttributeNames(nameMap)
                                             .expressionAttributeValues(valueMap)
                                             .build());

        // then
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s("1").build());
        GetItemResponse response = dynamoDB.getItem(GetItemRequest.builder()
                                                                  .tableName(TABLE_NAME)
                                                                  .key(key)
                                                                  .build());
        assertThat(response).isNotNull();
        assertThat(response.item()).isNotNull();
        assertThat(response.item().get("id").s()).isEqualTo("1");
        assertThat(response.item().get("name").s()).isEqualTo("WONCHUL");
        assertThat(response.item().get("birth_year").n()).isEqualTo("1992");
    }

    @Test
    public void 아이템_삽입_후_해당_데이터_삭제_성공() {
        // given
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s("1").build());
        item.put("name", AttributeValue.builder().s("wonchul").build());
        item.put("birth_year", AttributeValue.builder().n("1992").build());
        dynamoDB.putItem(PutItemRequest.builder()
                                       .tableName(TABLE_NAME)
                                       .item(item)
                                       .build());

        // when
        Map<String, AttributeValue> deleteKey = new HashMap<>();
        deleteKey.put("id", AttributeValue.builder().s("1").build());
        dynamoDB.deleteItem(DeleteItemRequest.builder().tableName(TABLE_NAME).key(deleteKey).build());

        // then
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s("1").build());
        GetItemResponse response = dynamoDB.getItem(GetItemRequest.builder()
                                                                  .tableName(TABLE_NAME)
                                                                  .key(key)
                                                                  .build());

        assertThat(response).isNotNull();
        assertThat(response.item()).isEmpty();
    }

    @AfterEach
    public void clear() {
        dynamoDB.deleteTable(DeleteTableRequest.builder().tableName(TABLE_NAME).build());
    }

}