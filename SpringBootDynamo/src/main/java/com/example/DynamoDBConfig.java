package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDbClient amazonDynamoDB(
            @Value("${aws.region}") String region,
            @Value("${aws.dynamo.endpoint}") String dynamoEndpoint,
            @Value("${aws.access-key}") String accessKey,
            @Value("${aws.secret-key}") String secretKey) throws URISyntaxException {
        return DynamoDbClient.builder()
                             .region(Region.of(region))
                             .credentialsProvider(StaticCredentialsProvider.create(
                                     AwsBasicCredentials.create(accessKey, secretKey))
                             )
                             .endpointOverride(new URI(dynamoEndpoint))
                             .build();
    }
}