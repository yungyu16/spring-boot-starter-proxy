package com.github.yungyu16.spring.stub;

import java.lang.reflect.Method;

/**
 * CreatedDate: 2020/10/27
 * Author: songjialin
 */
public interface InvocationHandler {
    Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable;
}
