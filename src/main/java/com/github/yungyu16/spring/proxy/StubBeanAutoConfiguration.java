package com.github.yungyu16.spring.proxy;

import com.github.yungyu16.spring.proxy.processor.StubBeanPostProcessor;
import com.github.yungyu16.spring.proxy.processor.StubDefPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Configuration
@ConditionalOnMissingClass
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
