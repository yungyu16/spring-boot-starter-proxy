package com.github.yungyu16.spring.proxy.example.service;


import com.github.yungyu16.spring.proxy.annotation.ProxyStub;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@ProxyStub(InvocationDispatcherImpl2.class)
public interface HelloService2 {
    void hello();
}
