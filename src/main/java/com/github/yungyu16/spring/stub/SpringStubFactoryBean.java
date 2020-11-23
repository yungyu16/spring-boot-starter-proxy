package com.github.yungyu16.spring.stub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * CreatedDate: 2020/9/25
 * Author: songjialin
 */
@Slf4j
public class SpringStubFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public SpringStubFactoryBean(Class<T> clientInterface) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public T getObject() {
        return null;
    }

    @Override
    public Class<T> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
