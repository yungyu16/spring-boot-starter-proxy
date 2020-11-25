package com.github.yungyu16.spring.stub;

import com.github.yungyu16.spring.stub.proxy.DefaultStubProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Configuration
public class StubBeanAutoConfiguration {
    @Bean
    public StubDefPostProcessor stubBeanFactoryPostProcessor() {
        return new StubDefPostProcessor();
    }

    @Bean
    public StubBeanPostProcessor stubBeanPostProcessor(DefaultStubProxyFactory defaultStubProxyFactory) {
        return new StubBeanPostProcessor(defaultStubProxyFactory);
    }

    @Bean
    public DefaultStubProxyFactory defaultStubProxyFactory() {
        return new DefaultStubProxyFactory();
    }
}
