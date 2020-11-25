package com.github.yungyu16.spring.proxy;

import com.github.yungyu16.spring.proxy.annotation.ProxyStub;
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
        ProxyStub proxyStub = AnnotationUtils.getAnnotation(type, ProxyStub.class);
        if (proxyStub == null) {
            return null;
        }
        if (!type.isInterface()) {
            throw new BeanCreationNotAllowedException(name, type.getName() + " 不是Interface");
        }
        AbstractInvocationDispatcher invocationDispatcher = getInvocationDispatcher(type, proxyStub);
        Class annotationType = invocationDispatcher.getAnnotationType();
        Annotation annotation = AnnotationUtils.getAnnotation(type, annotationType);
        StubContext stubContext = StubContext.valueOf(type, annotation);
        return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{type, StubLabel.class}, StubInvocationHandler.newInstance(stubContext, invocationDispatcher));
    }

    @SuppressWarnings("all")
    private AbstractInvocationDispatcher getInvocationDispatcher(@NonNull Class<?> type, @NonNull ProxyStub proxyStub) {
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

        public static StubInvocationHandler newInstance(@NonNull StubContext stubContext, @NonNull AbstractInvocationDispatcher dispatcher) {
            return new StubInvocationHandler(stubContext, dispatcher);
        }

        private final StubContext stubContext;
        private final AbstractInvocationDispatcher dispatcher;

        private StubInvocationHandler(StubContext stubContext, AbstractInvocationDispatcher dispatcher) {
            this.stubContext = stubContext;
            this.dispatcher = dispatcher;
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
