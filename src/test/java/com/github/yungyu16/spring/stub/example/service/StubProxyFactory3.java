package com.github.yungyu16.spring.stub.example.service;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import com.github.yungyu16.spring.stub.proxy.StubProxyFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * CreatedDate: 2020/11/25
 * Author: songjialin
 */
@Component
public class StubProxyFactory3 implements StubProxyFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> T proxy(Class<T> stubInterface, ProxyStub stubAnnotation) {
        return (T) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), collectProxyInterface(stubInterface), (proxy, method, args) -> {
            if (ReflectionUtils.isToStringMethod(method)) {
                return "ProxyStub:" + ClassUtils.classNamesToString(stubInterface) + ":" + stubAnnotation;
            }
            if (ReflectionUtils.isEqualsMethod(method)
                    || ReflectionUtils.isHashCodeMethod(method)) {
                return method.invoke(this, args);
            }
            System.out.println("StubProxyFactory3");
            return "StubProxyFactory3";
        });
    }
}
