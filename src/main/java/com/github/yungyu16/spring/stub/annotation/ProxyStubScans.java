package com.github.yungyu16.spring.stub.annotation;

import com.github.yungyu16.spring.stub.StubBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProxyStubScans {
    ProxyStubScan[] value() default {};
}
