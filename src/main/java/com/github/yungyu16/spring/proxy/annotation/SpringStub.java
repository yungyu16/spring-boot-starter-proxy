package com.github.yungyu16.spring.proxy.annotation;

import com.github.yungyu16.spring.proxy.AbstractInvocationDispatcher;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/10/27
 * Author: songjialin
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface SpringStub {
    @SuppressWarnings("all")
    Class<? extends AbstractInvocationDispatcher> dispatcherType() default AbstractInvocationDispatcher.class;

    String dispatcherBean() default "";
}
