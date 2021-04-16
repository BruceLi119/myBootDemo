package com.asiaInfo.demo.common.aspect;

import java.lang.annotation.*;

/**
 * @description: 自定义注解
 *
 * @author: li.shuGuang
 * @dateTime: 2021/4/15
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    String methodName() default "";
    String methodId() default "";
}


