package com.sywl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengxiao
 * @date 2017/7/15
 * 不需要登录的接口入口方法加此annotation needLogin = false, 默认需要登录
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    boolean needLogin() default false;
}

