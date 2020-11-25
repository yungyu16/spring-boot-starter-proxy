package com.github.yungyu16.spring.stub;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import com.github.yungyu16.spring.stub.proxy.DefaultStubProxyFactory;
import com.github.yungyu16.spring.stub.proxy.StubProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
public class StubBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {
    private BeanFactory beanFactory;
    private final DefaultStubProxyFactory defaultStubProxyFactory;

    public StubBeanPostProcessor(DefaultStubProxyFactory defaultStubProxyFactory) {
        this.defaultStubProxyFactory = defaultStubProxyFactory;
    }

    @Override
    @SuppressWarnings("all")
    public Object postProcessBeforeInstantiation(Class<?> type, String name) throws BeansException {
        ProxyStub proxyStub = AnnotatedElementUtils.getMergedAnnotation(type, ProxyStub.class);
        if (proxyStub == null) {
            return null;
        }
        if (!type.isInterface()) {
            throw new BeanCreationNotAllowedException(name, type.getName() + " 不是Interface");
        }
        StubProxyFactory stubProxyFactory = getStubProxyFactory(proxyStub);
        return stubProxyFactory.proxy(type, proxyStub);
    }

    private StubProxyFactory getStubProxyFactory(ProxyStub proxyStub) {
        Class<? extends StubProxyFactory> factoryType = proxyStub.factoryType();
        if (factoryType == StubProxyFactory.class) {
            return defaultStubProxyFactory;
        }
        return beanFactory.getBean(factoryType);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
