package com.dynamodb.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pxp167
 * @Date: 4/4/2019
 *
 */
@Configuration
public class DynamoDbConfig {

    /*@Value("${amazon.access.key}")
    private String aswAccessKey;

    @Value("${amazon.access.secret-key}")
    private String awsSecretKey;*/

    @Value("${amazon.region}")
    private String awsRegion;

    @Value("${amazon.end-point.url}")
    private String awsDynamoDbEndpoint;

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    public AmazonDynamoDB amazonDynamoDBConfig() {
        return AmazonDynamoDBClientBuilder.standard().withRegion(awsRegion)
          //.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDbEndpoint, awsRegion))
          //.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(aswAccessKey, awsSecretKey)))
          .build();
    }
}
