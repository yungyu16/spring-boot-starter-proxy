package com.github.yungyu16.spring.proxy;

import lombok.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 多个Proxy实例共享的单例 需要自行保证状态的线程安全
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
public abstract class AbstractInvocationDispatcher<ANNOTATION_TYPE extends Annotation, ATTACHMENT> {
    //TODO 弱引用？
    private volatile Map<Method, ATTACHMENT> methodAttachments;

    protected final Optional<ATTACHMENT> get(@NonNull Method key) {
        return Optional.ofNullable(getMethodAttachments().get(key));
    }

    protected final ATTACHMENT getOrCompute(@NonNull Method key, @NonNull Function<Method, ATTACHMENT> function) {
        return getMethodAttachments().computeIfAbsent(key, function);
    }

    private Map<Method, ATTACHMENT> getMethodAttachments() {
        if (methodAttachments == null) {
            synchronized (this) {
                if (methodAttachments == null) {
                    methodAttachments = new ConcurrentHashMap<>();
                }
            }
        }
        return methodAttachments;
    }

    @SuppressWarnings("unchecked")
    Class<ANNOTATION_TYPE> getAnnotationType() {
        return (Class<ANNOTATION_TYPE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Object invoke(StubContext<ANNOTATION_TYPE> stubContext, Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(proxy, method, args);
    }

    protected Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw new UnsupportedOperationException();
    }
}

