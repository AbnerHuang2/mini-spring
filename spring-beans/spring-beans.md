## spring-beans模块核心功能分析
1. Bean的创建与管理
2. Bean的定义的加载与管理
3. Bean定义如何转换成bean
5. 工厂bean的实现

### Bean的创建与管理
类图关系总览
![img.png](../img/beanfactory-map.png)

### Bean的定义的加载与管理
类图关系总览
![img.png](../img/beandefinition.png)

### FactoryBean
工厂对象，主要用于创建bean，可以通过实现FactoryBean接口，来自定义bean的创建过程。

## IOC相关类的继承关系

spring的相关继承关系
![img.png](../img/beanfactory-spring.png)

## 测试
在test包下的TestMain中，可以看到测试的代码，可以通过debug的方式，一步一步的看到spring的初始化过程。