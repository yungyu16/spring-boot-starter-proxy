package com.github.yungyu16.spring.stub.stub;

import org.springframework.beans.factory.FactoryBean;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/10/27
 * Author: songjialin
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringStub {
    Class<InvocationDispatcher> dispatcherClass() default InvocationDispatcher.class;

    String dispatcherBeanId() default "";

    Class<FactoryBean> factoryBeanClass() default FactoryBean.class;
}
