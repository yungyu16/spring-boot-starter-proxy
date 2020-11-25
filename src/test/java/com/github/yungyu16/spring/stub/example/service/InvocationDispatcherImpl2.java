package com.github.yungyu16.spring.stub.example.service;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import com.github.yungyu16.spring.stub.proxy.AbstractInvocationDispatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Component
public class InvocationDispatcherImpl2 extends AbstractInvocationDispatcher<ProxyStub, Void> {
    @Override
    protected Object invoke(StubProxyContext<ProxyStub> stubProxyContext, Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("InvocationDispatcherImpl2");
        return "InvocationDispatcherImpl2";
    }
}
