package com.github.yungyu16.spring.proxy;

import com.github.yungyu16.spring.proxy.annotation.ProxyStubScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ProxyStubScan("com.github.yungyu16.spring.stub.processor")
public class StartUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartUpApplication.class, args);
    }
}
