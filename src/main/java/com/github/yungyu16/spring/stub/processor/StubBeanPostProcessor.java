package com.github.yungyu16.spring.stub.processor;

import com.github.yungyu16.spring.stub.AbstractInvocationDispatcher;
import com.github.yungyu16.spring.stub.StubProxyMark;
import com.github.yungyu16.spring.stub.annotation.SpringStub;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
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
        SpringStub springStub = AnnotationUtils.getAnnotation(type, SpringStub.class);
        if (springStub == null) {
            return null;
        }
        if (!type.isInterface()) {
            throw new BeanCreationNotAllowedException(name, type.getName() + " 不是Interface");
        }
        AbstractInvocationDispatcher invocationDispatcher = getInvocationDispatcher(type, springStub);
        Class annotationType = invocationDispatcher.getAnnotationClass();
        Annotation annotation = AnnotationUtils.getAnnotation(type, annotationType);
        return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{type, StubProxyMark.class}, (proxy, method, args) -> invocationDispatcher.invoke(proxy, method, args, annotation));
    }

    @SuppressWarnings("all")
    private AbstractInvocationDispatcher getInvocationDispatcher(@NonNull Class<?> type, @NonNull SpringStub springStub) {
        String beanName = springStub.dispatcherBean();
        Class beanType = springStub.dispatcherType();

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
}
