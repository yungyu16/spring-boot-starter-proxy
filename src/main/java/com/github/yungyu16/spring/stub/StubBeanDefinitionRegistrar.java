package com.github.yungyu16.spring.stub;

import com.github.yungyu16.spring.stub.annotation.ProxyStub;
import com.github.yungyu16.spring.stub.annotation.ProxyStubScan;
import com.github.yungyu16.spring.stub.support.ClassPathStubBeanDefinitionScanner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
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

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ProxyStubScan.class.getName(), true);
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
        String markAnnotation = attrs.getString("markAnnotation");
        if (!StringUtils.isEmpty(markAnnotation)) {
            scanner.resetFilters(false);
            AnnotationTypeFilter proxyStubFilter = new AnnotationTypeFilter(ProxyStub.class, true, false);
            @SuppressWarnings("unchecked")
            AnnotationTypeFilter markAnnotationFilter = new AnnotationTypeFilter((Class<? extends Annotation>) ClassUtils.forName(markAnnotation, null), true, false);
            scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> proxyStubFilter.match(metadataReader, metadataReaderFactory) && markAnnotationFilter.match(metadataReader, metadataReaderFactory));
        }
        int bdCnt = scanner.scan(StringUtils.toStringArray(pkgList));
        log.debug("本轮从{}中扫描到{}个SpringStub", pkgList, bdCnt);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
