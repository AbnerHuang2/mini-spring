# 实现Spring AOP切面
## 核心原理
1. 定义切点，去匹配对应的类和方法 【Pointcut】
2. 定义织入对象【AdvisedSupport】，包含目标类和切面类【MethodInterceptor】，匹配器【AspectJExpressionPointcut】 
3. 通过动态代理，生成代理类。代理类注入织入对象。 
4. 代理类需要重写执行方法逻辑，在执行方法的时候， 通过织入对象的匹配器去匹配，如果匹配到了，就执行切面类的切面逻辑，如果没有匹配到，就执行原来的方法逻辑