package com.github.yungyu16.spring.stub.processor;

import com.github.yungyu16.spring.stub.support.BeanDefinitionRegistryPostProcessorAdapter;
import com.github.yungyu16.spring.stub.support.ClassPathStubBeanDefinitionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Slf4j
public class StubDefPostProcessor extends BeanDefinitionRegistryPostProcessorAdapter implements EnvironmentAware, BeanFactoryAware {

    private Environment environment;
    private BeanFactory beanFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        List<String> pkgList = AutoConfigurationPackages.get(beanFactory);
        if (CollectionUtils.isEmpty(pkgList)) {
            log.debug("待扫描的包路径为空...跳过...");
            return;
        }
        ClassPathBeanDefinitionScanner scanner = new ClassPathStubBeanDefinitionScanner(registry, environment);
        int bdCnt = scanner.scan(StringUtils.toStringArray(pkgList));
        log.debug("本轮从 {} 中扫描到 {} 个SpringStub", pkgList, bdCnt);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
