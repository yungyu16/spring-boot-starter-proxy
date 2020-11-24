package com.github.yungyu16.spring.proxy.example;

import com.github.yungyu16.spring.proxy.example.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void testHello() {
        helloService.hello();
    }
}
