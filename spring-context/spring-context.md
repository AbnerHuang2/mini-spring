## 职责
spring-context模块主要负责提供应用上下文的支持，包括bean的加载、生命周期管理、事件发布订阅机制、资源加载、国际化等功能。
## spring-context模块核心功能分析
1. 如何将所有配置的bean加载到容器中
2. 如何处理生命周期过程中的前置后置处理器
3. 事件发布订阅机制实现
4. 如何通过注解自动扫描注册bean 
5. 感知对象Aware含义与实现

## 核心类及其继承关系
![img.png](../img/spring-context.png)
## 将配置的bean加载到容器中
1. 加载bean的定义
   1. 
2. 通过beanfactory创建bean