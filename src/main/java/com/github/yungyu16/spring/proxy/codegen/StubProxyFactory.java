package com.github.yungyu16.spring.proxy.codegen;

import com.github.yungyu16.spring.proxy.annotation.ProxyStub;

/**
 * CreatedDate: 2020/11/25
 * Author: songjialin
 */
public interface StubProxyFactory {
    <T> T proxy(Class<T> targetType, ProxyStub annotation);
}
