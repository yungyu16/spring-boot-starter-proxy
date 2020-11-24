package com.github.yungyu16.spring.proxy.annotation;

import com.github.yungyu16.spring.proxy.service.InvocationDispatcherImpl;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringStub(dispatcherType = InvocationDispatcherImpl.class)
public @interface TestClient {
    String value();
}
