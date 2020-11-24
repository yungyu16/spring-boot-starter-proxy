package com.github.yungyu16.spring.proxy;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
public class StubBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @Override
    @SuppressWarnings("all")
    public Object postProcessBeforeInstantiation(Class<?> type, String name) throws BeansException {
        com.github.yungyu16.spring.proxy.annotation.ProxyStub proxyStub = AnnotationUtils.getAnnotation(type, com.github.yungyu16.spring.proxy.annotation.ProxyStub.class);
        if (proxyStub == null) {
            return null;
        }
        if (!type.isInterface()) {
            throw new BeanCreationNotAllowedException(name, type.getName() + " 不是Interface");
        }
        AbstractInvocationDispatcher invocationDispatcher = getInvocationDispatcher(type, proxyStub);
        Class annotationType = invocationDispatcher.getAnnotationType();
        Annotation annotation = AnnotationUtils.getAnnotation(type, annotationType);
        return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{type, ProxyStub.class}, StubInvocationHandler.newInstance(type, annotation, invocationDispatcher));
    }

    @SuppressWarnings("all")
    private AbstractInvocationDispatcher getInvocationDispatcher(@NonNull Class<?> type, @NonNull com.github.yungyu16.spring.proxy.annotation.ProxyStub proxyStub) {
        String beanName = proxyStub.dispatcherBean();
        Class beanType = proxyStub.dispatcherType();

        Object handler = null;
        if (!StringUtils.isEmpty(beanName)) {
            handler = beanFactory.getBean(beanName);
        }
        if (handler == null && beanType != AbstractInvocationDispatcher.class) {
            handler = beanFactory.getBean(beanType);
        }
        if (handler == null) {
            throw new BeanCreationException(type.getName() + " 没有指定InvocationDispatcher");
        }
        return (AbstractInvocationDispatcher) handler;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings("rawtypes")
    static class StubInvocationHandler implements InvocationHandler {

        public static StubInvocationHandler newInstance(Class<?> type, @NonNull Annotation annotation, @NonNull AbstractInvocationDispatcher dispatcher) {
            return new StubInvocationHandler(type, annotation, dispatcher);
        }

        private final Class<?> type;
        private final AbstractInvocationDispatcher dispatcher;
        private final Annotation annotation;

        private StubInvocationHandler(Class<?> type, Annotation annotation, AbstractInvocationDispatcher dispatcher) {
            this.type = type;
            this.dispatcher = dispatcher;
            this.annotation = annotation;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (ReflectionUtils.isToStringMethod(method)) {
                return "ProxyStub:" + ClassUtils.classNamesToString(type) + ":" + annotation;
            }
            if (ReflectionUtils.isEqualsMethod(method)
                    || ReflectionUtils.isHashCodeMethod(method)) {
                return method.invoke(this, args);
            }
            return dispatcher.invoke(proxy, method, args, annotation);
        }
    }
}
