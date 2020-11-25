package com.github.yungyu16.spring.stub.example;

import com.github.yungyu16.spring.stub.example.service.HelloService1;
import com.github.yungyu16.spring.stub.example.service.HelloService2;
import com.github.yungyu16.spring.stub.example.service.HelloService3;
import org.junit.Assert;
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
    @Autowired
    private HelloService3 helloService3;

    @Test
    public void testHello1() {
        String hello1 = helloService1.hello();
        String hello2 = helloService2.hello();
        String hello3 = helloService3.hello();
        Assert.assertSame("InvocationDispatcherImpl1", hello1);
        Assert.assertSame("InvocationDispatcherImpl2", hello2);
        Assert.assertSame("StubProxyFactory3", hello3);
    }
}
