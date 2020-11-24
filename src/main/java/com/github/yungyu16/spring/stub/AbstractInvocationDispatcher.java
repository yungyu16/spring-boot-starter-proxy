package com.github.yungyu16.spring.stub;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
public abstract class AbstractInvocationDispatcher<T extends Annotation> {

    @SuppressWarnings("unchecked")
    public Class<T> getAnnotationClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Object invoke(Object proxy, Method method, Object[] args, T annotation) throws Throwable {
        return invoke(proxy, method, args);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw new UnsupportedOperationException();
    }
}

