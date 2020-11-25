package com.github.yungyu16.spring.stub.example.annotation;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import com.github.yungyu16.spring.stub.example.service.InvocationDispatcherImpl1;
import org.springframework.core.annotation.AliasFor;

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
    @AliasFor(annotation = ProxyStub.class, attribute = "beanName")
    String value() default "";
}
