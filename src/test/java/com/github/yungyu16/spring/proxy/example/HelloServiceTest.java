package com.github.yungyu16.spring.proxy.example;

import com.github.yungyu16.spring.proxy.example.service.HelloService1;
import com.github.yungyu16.spring.proxy.example.service.HelloService2;
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
    private HelloService1 helloService1;
    @Autowired
    private HelloService2 helloService2;

    @Test
    public void testHello1() {
        helloService1.hello();
        helloService2.hello();
    }
}
