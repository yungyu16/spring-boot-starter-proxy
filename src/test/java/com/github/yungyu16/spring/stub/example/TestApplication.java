package com.github.yungyu16.spring.stub.example;

import com.github.yungyu16.spring.stub.annotation.ProxyStubScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ProxyStubScan("com.github.yungyu16.spring.stub.processor")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
