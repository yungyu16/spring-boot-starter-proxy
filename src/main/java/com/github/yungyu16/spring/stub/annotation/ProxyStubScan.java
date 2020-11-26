package com.github.yungyu16.spring.stub.annotation;

import com.github.yungyu16.spring.stub.StubBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * CreatedDate: 2020/11/24
 * Author: songjialin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(StubBeanDefinitionRegistrar.class)
public @interface ProxyStubScan {
    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    /**
     * 标记注解
     */
    Class<? extends Annotation> markAnnotation() default Annotation.class;
}
