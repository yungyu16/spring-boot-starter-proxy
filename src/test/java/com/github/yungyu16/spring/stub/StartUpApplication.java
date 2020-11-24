package com.github.yungyu16.spring.stub;

import com.github.yungyu16.spring.stub.annotation.SpringStubScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringStubScan("com.github.yungyu16.spring.stub.processor")
public class StartUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartUpApplication.class, args);
    }
}
