package com.github.yungyu16.spring.stub.service;

import com.github.yungyu16.spring.stub.annotation.TestClient;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@TestClient("这是一个TestClient注解")
public interface HelloService {
    void hello();
}
