package com.example.demo.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoAppConfig {

    private static final String LOCAL_PARAM_STORE_ENDPOINT = "http://localhost:4566";
    private static final String LOCALSTACK_SIGNING_REGION = "eu-west-1";

    @Bean
    @ConditionalOnProperty(name = "aws.paramstore.endpoint", havingValue = LOCAL_PARAM_STORE_ENDPOINT)
    AWSSimpleSystemsManagement simpleSystemsManagement() {
        return AWSSimpleSystemsManagementClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(LOCAL_PARAM_STORE_ENDPOINT, LOCALSTACK_SIGNING_REGION))
                .build();
    }


}
