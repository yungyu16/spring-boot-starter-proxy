package com.github.yungyu16.spring.stub.example.service;


import com.github.yungyu16.spring.stub.example.annotation.TestClient;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@TestClient("helloService")
public interface HelloService1 {
    String hello();
}
