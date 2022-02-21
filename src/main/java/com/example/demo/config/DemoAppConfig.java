package com.example.demo.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DemoAppConfig {

    private static final String LOCALSTACK_SIGNING_REGION = "us-east-1";

    @Bean
    @ConditionalOnProperty(name = "aws.paramstore.endpoint")
    AWSSimpleSystemsManagement simpleSystemsManagement() {
        return AWSSimpleSystemsManagementClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(System.getProperty("aws.paramstore.endpoint"), LOCALSTACK_SIGNING_REGION))
                .build();
    }


}
