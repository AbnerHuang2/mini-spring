## mini-spring
从Spring的基础功能出发，借鉴spring5.3的源码逐步实现Spring的核心功能，从而更好的理解Spring的核心功能。
## 查看效果
1. 下载代码
2. 设置jdk8
3. 运行spring-context下的test包下的TestMain中的main方法

## 说明
从Spring的基础功能出发，借鉴spring5.3的源码逐步实现Spring的核心功能，从而更好的理解Spring的核心功能。
每次提交都有对新增功能的说明，可以从提交记录中查看具体功能的改动实现。
## 目标-实现Spring核心功能
### 1.实现Spring IOC容器
1. Bean定义如何从加载到Spring容器中
2. Bean是如何被创建,注入属性，初始化,销毁的
3. FactoryBean的实现原理
4. BeanFactoryProcessor和BeanPostProcessor的实现原理
5. Aware感知容器对象怎么使用和实现的
6. 事件发布订阅机制实现
7. 类型自动转换实现原理

### 2.实现Spring AOP切面
1. Aop实现原理
2. Aop怎么整合到Spring生命周期

## 核心功能实现细节
### 代码结构
1. spring-core: Spring核心模块
2. spring-beans: Spring Bean模块
3. spring-context: Spring上下文模块
4. spring-aop: Spring Aop模块
### 各个模块的核心功能分析
1. spring-core：[spring-core](./spring-core/spring-core.md)
2. spring-beans：[spring-beans](./spring-beans/spring-beans.md)
3. spring-context：[spring-context](./spring-context/spring-context.md)
4. spring-aop：[spring-aop](./spring-aop/spring-aop.md)