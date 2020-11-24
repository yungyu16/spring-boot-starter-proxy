package com.github.yungyu16.spring.proxy.service;

import com.github.yungyu16.spring.proxy.annotation.TestClient;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@TestClient("这是一个TestClient注解")
public interface HelloService {
    void hello();
}
