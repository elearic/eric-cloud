package net.eric.zz.provider.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 *  
 *  * @author zz_huns  
 *  @version Id: AricRequestBodyAdviceAdapter.java, v 0.1 2020/6/19 2:32 AM zz_huns Exp $$
 *
 */
@RestControllerAdvice
public class AricRequestBodyAdviceAdapter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("====[AricRequestBodyAdviceAdapter.preHandle 执行了]====");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("====[AricRequestBodyAdviceAdapter.postHandle 执行了]====");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("====[AricRequestBodyAdviceAdapter.afterCompletion 执行了]====");
        super.afterCompletion(request, response, handler, ex);
    }
}
