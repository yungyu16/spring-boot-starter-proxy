package com.github.yungyu16.spring.stub.proxy;

import com.github.yungyu16.spring.stub.StubContext;
import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * CreatedDate: 2020/11/25
 * Author: songjialin
 */
public class DefaultStubProxyFactory implements StubProxyFactory, BeanFactoryAware {
    private BeanFactory beanFactory;

    @SuppressWarnings("rawtypes,unchecked")
    @Override
    public <T> T proxy(Class<T> stubInterface, ProxyStub stubAnnotation) {
        AbstractInvocationDispatcher invocationDispatcher = getInvocationDispatcher(stubInterface, stubAnnotation);
        Class annotationType = invocationDispatcher.getAnnotationType();
        Annotation annotation = AnnotationUtils.findAnnotation(stubInterface, annotationType);
        StubContext<?> stubContext = StubContext.valueOf(stubInterface, annotation);
        return (T) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), collectProxyInterface(stubInterface), StubInvocationHandler.newInstance(stubContext, invocationDispatcher));
    }

    @SuppressWarnings("all")
    private AbstractInvocationDispatcher getInvocationDispatcher(@NonNull Class<?> type, @NonNull ProxyStub proxyStub) {
        Class beanType = proxyStub.dispatcherType();

        Object handler = null;
        if (beanType != AbstractInvocationDispatcher.class) {
            handler = beanFactory.getBean(beanType);
        } else {
            throw new BeanCreationException(type.getName() + " 没有指定InvocationDispatcher");
        }
        if (!(handler instanceof AbstractInvocationDispatcher)) {
            throw new BeanCreationException(type.getName() + " InvocationDispatcher类型错误");
        }
        return (AbstractInvocationDispatcher) handler;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings("rawtypes")
    static class StubInvocationHandler implements InvocationHandler {

        private final StubContext stubContext;
        private final AbstractInvocationDispatcher dispatcher;

        private StubInvocationHandler(StubContext stubContext, AbstractInvocationDispatcher dispatcher) {
            this.stubContext = stubContext;
            this.dispatcher = dispatcher;
        }

        public static StubInvocationHandler newInstance(@NonNull StubContext stubContext, @NonNull AbstractInvocationDispatcher dispatcher) {
            return new StubInvocationHandler(stubContext, dispatcher);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (ReflectionUtils.isToStringMethod(method)) {
                return "ProxyStub:" + ClassUtils.classNamesToString(stubContext.getStubType()) + ":" + stubContext.getAnnotation();
            }
            if (ReflectionUtils.isEqualsMethod(method)
                    || ReflectionUtils.isHashCodeMethod(method)) {
                return method.invoke(this, args);
            }
            return dispatcher.invoke(stubContext, proxy, method, args);
        }
    }
}
