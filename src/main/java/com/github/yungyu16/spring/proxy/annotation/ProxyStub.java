package com.github.yungyu16.spring.proxy.annotation;

import com.github.yungyu16.spring.proxy.AbstractInvocationDispatcher;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/10/27
 * Author: songjialin
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface ProxyStub {
    /**
     * 指定beanName
     *
     * @return
     */
    @AliasFor(annotation = Component.class, attribute = "value")
    String beanName() default "";

    /**
     * 指定动态代理调用拦截器BeanType,用于从BeanFactory中按类型获取bean
     *
     * @return
     */
    @AliasFor("dispatcherType")
    Class<? extends AbstractInvocationDispatcher> value() default AbstractInvocationDispatcher.class;

    /**
     * 指定动态代理调用拦截器BeanType,用于从BeanFactory中按类型获取bean
     *
     * @return
     */
    @AliasFor("value")
    Class<? extends AbstractInvocationDispatcher> dispatcherType() default AbstractInvocationDispatcher.class;
}
