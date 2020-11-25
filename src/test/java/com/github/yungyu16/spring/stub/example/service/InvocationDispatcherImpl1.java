package com.github.yungyu16.spring.stub.example.service;

import com.github.yungyu16.spring.stub.StubContext;
import com.github.yungyu16.spring.stub.example.annotation.TestClient;
import com.github.yungyu16.spring.stub.proxy.AbstractInvocationDispatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Component
public class InvocationDispatcherImpl1 extends AbstractInvocationDispatcher<TestClient, Void> {
    @Override
    protected Object invoke(StubContext<TestClient> stubContext, Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("InvocationDispatcherImpl1");
        return "InvocationDispatcherImpl1";
    }
}
