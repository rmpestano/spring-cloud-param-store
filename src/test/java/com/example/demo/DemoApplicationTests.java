package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SSM;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
@AutoConfigureWebTestClient
class DemoApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Container
    static LocalStackContainer localstack = new LocalStackContainer(
            DockerImageName.parse("localstack/localstack:0.14.0"))
            .withServices(SSM)
            .withExposedPorts(4566)
            .withReuse(true);

    @BeforeAll
    public static void setUpCredentials() {
        System.setProperty("aws.accessKeyId", "accesskey");
        System.setProperty("aws.secretKey", "secretkey");
        System.setProperty("aws.paramstore.region", "");
        System.setProperty("aws.paramstore.endpoint", localstack.getEndpointConfiguration(SSM).getServiceEndpoint());
        putParameter(localstack, "/config/demo/demo.param", "from param store");
    }

    @Value("${demo.param}")
    private String demoParam;

    @Test
    void shouldGetParameterFromParamStore() {
        assertEquals("from param store", demoParam);
    }

    @Test
    void shouldGetUserConfigFromApplicationProperties() {
        webClient.get()
                .uri("/config")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.users[0].name").isEqualTo("user1")
                .jsonPath("$.users[0].pass").isEqualTo("pass1")
                .jsonPath("$.users[0].roles[0].name").isEqualTo("admin")
                .jsonPath("$.users[0].roles[1].name").isEqualTo("user");
    }

    private static void putParameter(LocalStackContainer localstack, String parameterName, String parameterValue) {
        try {
            localstack.execInContainer("awslocal", "ssm", "put-parameter", "--name", parameterName, "--type", "String",
                    "--value", parameterValue, "--region", localstack.getEndpointConfiguration(SSM).getSigningRegion());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

