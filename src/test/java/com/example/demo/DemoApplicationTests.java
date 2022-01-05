package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"aws.paramstore.endpoint=http://localhost:4566"})
class DemoApplicationTests {


    @Value("${demo.param}")
    private String demoParam;

    @Test
    void contextLoads() {
        assertEquals("test", demoParam);
    }

}
