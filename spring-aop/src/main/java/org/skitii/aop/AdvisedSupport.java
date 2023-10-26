package org.skitii.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author skitii
 * @since 2023/10/26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisedSupport {
    // 目标对象
    private TargetSource targetSource;
    // 方法拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器
    private MethodMatcher methodMatcher;

}
