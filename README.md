![spring-boot-starter-proxy](https://socialify.git.ci/yungyu16/spring-boot-starter-proxy/image?description=1&descriptionEditable=%E5%9F%BA%E4%BA%8ESpringBoot%E6%A0%B8%E5%BF%83API%E5%B0%81%E8%A3%85%E7%9A%84%E7%94%A8%E4%BA%8E%E4%BE%BF%E6%8D%B7%E7%94%9F%E6%88%90Stub%20Proxy%E7%9A%84Starter&language=1&logo=https%3A%2F%2Fraw.githubusercontent.com%2Fyungyu16%2Fcdn%2Fmaster%2Favatar.png&owner=1&pattern=Circuit%20Board&theme=Light)

<p align="center">
    <br/>
    <br/>
    <b>将不可实例化的Interface动态代理后注册到Spring容器以便IOC,用于便捷的生成Local Stub</b>
    <br/>
    <br/>
</p>

在业务开发中整合中间件或三方依赖时，常借助Client/SDK进行命令式过程式的调用,相关代码散落各处,既不OOP也不方便追溯和维护。本项目通过对Interface进行便捷透明的代理并注册到Spring容器，方便业务中利用Interface和Annotation作为DSL封装各类简洁易用易维护的本地存根。

# 要求
- Spring-boot 2.x
> 本项目依赖 `Spring-Boot 2.0.8` Core API开发测试，且没有传递Spring-Boot依赖，理论上兼容所有Spring-Boot版本。
- Java8

# 集成
已发布到中央仓库，依赖坐标如下：
```xml
<plugin>
    <groupId>com.github.yungyu16.spring</groupId>
    <artifactId>spring-boot-starter-proxy</artifactId>
    <version>release-tag</version>
</plugin>
```
> 查看[release-tag](https://github.com/yungyu16/spring-boot-starter-proxy/releases)
# 使用
## 1、最小化配置

## 2、语义化配置(推荐)