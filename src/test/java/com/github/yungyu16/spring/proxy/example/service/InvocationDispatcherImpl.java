package com.github.yungyu16.spring.proxy.example.service;

import com.github.yungyu16.spring.proxy.AbstractInvocationDispatcher;
import com.github.yungyu16.spring.proxy.StubContext;
import com.github.yungyu16.spring.proxy.example.annotation.TestClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Component
public class InvocationDispatcherImpl extends AbstractInvocationDispatcher<TestClient, Void> {
    @Override
    protected Object invoke(StubContext<TestClient> stubContext, Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(stubContext.getAnnotation());
        System.out.println("hahahahhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println("hahahahhhhhhhhhhhhhhhhhhhhhhhhhh");
        return null;
    }
}
