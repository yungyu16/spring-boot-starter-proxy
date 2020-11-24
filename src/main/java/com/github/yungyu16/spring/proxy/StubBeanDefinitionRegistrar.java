package com.github.yungyu16.spring.proxy;

import com.github.yungyu16.spring.proxy.annotation.SpringStubScan;
import com.github.yungyu16.spring.proxy.support.ClassPathStubBeanDefinitionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * CreatedDate: 2020/9/25
 * Author: songjialin
 */
@Slf4j
public class StubBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(SpringStubScan.class.getName(), true);
        AnnotationAttributes attrs = AnnotationAttributes.fromMap(annotationAttributes);
        if (attrs == null) {
            return;
        }
        ArrayList<String> pkgList = new ArrayList<>(2);
        String[] pkg1 = attrs.getStringArray("basePackages");
        String[] pkg2 = attrs.getStringArray("basePackageClasses");
        if (pkg1.length > 0) {
            pkgList.addAll(Arrays.asList(pkg1));
        }
        if (pkg2.length > 0) {
            pkgList.addAll(Arrays.asList(pkg2));
        }
        if (pkgList.isEmpty()) {
            pkgList.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        ClassPathBeanDefinitionScanner scanner = new ClassPathStubBeanDefinitionScanner(registry, environment);
        int bdCnt = scanner.scan(StringUtils.toStringArray(pkgList));
        log.debug("本轮从{}中扫描到{}个SpringStub", pkgList, bdCnt);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
