package com.github.yungyu16.spring.stub.proxy;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;

/**
 * CreatedDate: 2020/11/25
 * Author: songjialin
 */
public interface StubProxyFactory {

    default Class<?>[] collectProxyInterface(Class<?> interfaceType) {
        return new Class[]{interfaceType, StubProxyLabel.class};
    }

    <T> T createProxy(Class<T> stubInterface, ProxyStub stubAnnotation);
}
