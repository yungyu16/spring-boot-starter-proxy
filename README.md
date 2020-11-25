![spring-boot-starter-proxy](https://socialify.git.ci/yungyu16/spring-boot-starter-proxy/image?description=1&descriptionEditable=%E5%9F%BA%E4%BA%8ESpringBoot%E6%A0%B8%E5%BF%83API%E5%B0%81%E8%A3%85%E7%9A%84%E7%94%A8%E4%BA%8E%E4%BE%BF%E6%8D%B7%E7%94%9F%E6%88%90Stub%20Proxy%E7%9A%84Starter&language=1&logo=https%3A%2F%2Fraw.githubusercontent.com%2Fyungyu16%2Fcdn%2Fmaster%2Favatar.png&owner=1&pattern=Circuit%20Board&theme=Light)

<p align="center">
    <br/>
    <br/>
    <b>将不可实例化的Interface动态代理后注册到Spring容器以便IOC,用于便捷的生成Local Stub</b>
    <br/>
    <br/>
</p>

在业务中整合中间件或三方依赖时，常借助Client/SDK进行命令式过程式的调用,相关代码散落各处,既不OOP也不方便追溯和维护。本项目通过对Interface进行便捷透明的代理并注册到Spring容器，方便业务中使用Interface和Annotation作为DSL封装各类简洁易用易维护的本地存根。

# 要求
- Spring-boot 2.x
> 本项目依赖 `Spring-Boot 2.0.8` 核心基础API开发测试，且没有传递Spring-Boot依赖，理论上兼容所有Spring-Boot版本。
- Java8

# 集成
已发布到中央仓库，GAV坐标如下：
```xml
<plugin>
    <groupId>com.github.yungyu16.spring</groupId>
    <artifactId>spring-boot-starter-proxy</artifactId>
    <version>1.0.0</version>
</plugin>
```
> 查看[release-tag](https://github.com/yungyu16/spring-boot-starter-proxy/releases)

本项目借助AutoConfiguration实现了零配置，开箱即用；默认扫描的basePackages为 `@SpringBootApplication` 入口类所在包。     
如需添加自定义扫描路径，请使用 `@ProxyStubScan` 注解配置，该注解使用方式和 `@ComponentScan` 类似。

# 使用
本项目有两种使用方式：
1. **最小化原生配置**，直接使用本项目提供的标记注解，简单快捷。
1. **语义化定制配置**，基于Spring元注解模型定制语义化标记注解，提高可读性并可以传递额外上下文，[查看](./src/test/java/com/github/yungyu16/spring/proxy/example)示例。

## 最小化原生配置
1. 继承 `AbstractInvocationDispatcher<ANNOTATION_TYPE extends Annotation, ATTACHMENT>` 按需重载 `invoke` 方法。
```java
@Component
public class InvocationDispatcherImpl extends AbstractInvocationDispatcher<ProxyStub, Void> {
    @Override
    protected Object invoke(StubContext<ProxyStub> stubContext, Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(stubContext.getAnnotation());
        System.out.println("InvocationDispatcherImpl");
        return null;
    }
}
```
2. 定义Interface并添加标记注解指定InvocationDispatcher
```java
@ProxyStub(InvocationDispatcherImpl.class)
public interface HelloService {
    void hello();
}
```
3. IOC注入使用
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void testHello() {
        helloService.hello();
    }
}
```

## 2、语义化配置(推荐)
1. 继承 `AbstractInvocationDispatcher<ANNOTATION_TYPE extends Annotation, ATTACHMENT>` 按需重载 `invoke` 方法。
```java
@Component
public class InvocationDispatcherImpl extends AbstractInvocationDispatcher<TestClient, Void> {
    @Override
    protected Object invoke(StubContext<TestClient> stubContext, Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(stubContext.getAnnotation());
        System.out.println("InvocationDispatcherImpl");
        return null;
    }
}
```
2. 定制语义化注解
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ProxyStub(InvocationDispatcherImpl.class)
public @interface TestClient {
    @AliasFor(annotation = ProxyStub.class, attribute = "beanName")
    String value() default "";
}
```
3. 定义Interface并添加标记注解指定InvocationDispatcher
```java
@TestClient("helloService")
public interface HelloService {
    void hello();
}
```
4. IOC注入使用
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void testHello() {
        helloService.hello();
    }
}
```
