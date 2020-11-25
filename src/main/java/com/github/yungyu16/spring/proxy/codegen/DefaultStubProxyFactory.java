package com.github.yungyu16.spring.proxy.codegen;

import com.github.yungyu16.spring.proxy.annotation.ProxyStub;

/**
 * CreatedDate: 2020/11/25
 * Author: songjialin
 */
public class DefaultStubProxyFactory implements StubProxyFactory {
    public <T> T proxy(Class<T> targetType, ProxyStub annotation) {
        return null;
    }
}
