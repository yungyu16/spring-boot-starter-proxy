package com.github.yungyu16.spring.proxy;

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
    public StubBeanPostProcessor stubBeanPostProcessor() {
        return new StubBeanPostProcessor();
    }
}
