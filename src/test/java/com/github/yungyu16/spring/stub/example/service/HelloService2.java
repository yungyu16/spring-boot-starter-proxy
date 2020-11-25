package com.github.yungyu16.spring.stub.example.service;


import com.github.yungyu16.spring.stub.annotation.ProxyStub;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@ProxyStub(InvocationDispatcherImpl2.class)
public interface HelloService2 {
    String hello();
}
