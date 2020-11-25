package com.github.yungyu16.spring.proxy.example.service;


import com.github.yungyu16.spring.proxy.example.annotation.TestClient;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@TestClient("这是一个TestClient注解 HelloService1")
public interface HelloService1 {
    void hello();
}
