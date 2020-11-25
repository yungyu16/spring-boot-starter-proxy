package com.github.yungyu16.spring.stub.proxy;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.lang.annotation.Annotation;

/**
 * @author Yungyu
 * @description Created by Yungyu on 2020/11/24.
 */
@Getter
@EqualsAndHashCode
@ToString
public class StubProxyContext<T extends Annotation> {
    private final T annotation;
    private final Class<?> stubType;

    private StubProxyContext(@NonNull Class<?> stubType, T annotation) {
        this.annotation = annotation;
        this.stubType = stubType;
    }

    public static <T extends Annotation> StubProxyContext<T> valueOf(Class<?> stubType, T annotation) {
        return new StubProxyContext<>(stubType, annotation);
    }
}
