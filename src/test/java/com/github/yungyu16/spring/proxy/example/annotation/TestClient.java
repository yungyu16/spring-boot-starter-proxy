package com.github.yungyu16.spring.proxy.example.annotation;

import com.github.yungyu16.spring.proxy.annotation.ProxyStub;
import com.github.yungyu16.spring.proxy.example.service.InvocationDispatcherImpl1;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ProxyStub(InvocationDispatcherImpl1.class)
public @interface TestClient {
    String value();
}
