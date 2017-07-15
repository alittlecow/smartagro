package com.sywl.interceptor;


import com.sywl.annotation.Login;
import com.sywl.common.enums.Constants;
import com.sywl.exception.BusinessException;
import com.sywl.utils.RedisUtil;
import com.sywl.utils.TokenUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pengxiao
 * @date 2017-07-15
 * 登陆拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!ClassUtils.isAssignable(handler.getClass(), HandlerMethod.class))
            return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (login == null)
            login = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Login.class);
        if (login == null || !login.needLogin()) {
            return true;
        }
        //需要登陆的接口
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            throw new BusinessException("登录失效,请重新登陆");
        }
        // TODO: 2017/7/15
        //校验token是否过期
        boolean isExpiration = TokenUtils.isExpiration(token);
        String userId = (String) redisUtil.get(token);
        if (isExpiration || StringUtils.isBlank(userId)) {
            throw new BusinessException("登录失效,请重新登陆");
        }
        request.setAttribute(Constants.USER_ID, userId);
        return true;
    }


}
