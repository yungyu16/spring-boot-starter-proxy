package com.github.yungyu16.spring.stub;

import com.sun.tools.javac.comp.Annotate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

/**
 * CreatedDate: 2020/9/25
 * Author: songjialin
 */
public abstract class DefaultStubProxyFactory<A extends Annotate, T> implements FactoryBean<T>, ApplicationContextAware {
    protected Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private Class<T> stubType;
    private T annotation;

    public DefaultStubProxyFactory(Class<T> stubType, AnnotationMetadata metadata) {
        Assert.notNull(stubType, "stubType");
        this.stubType = stubType;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Class<T> getObjectType() {
        return stubType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
