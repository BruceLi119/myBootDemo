package com.asiaInfo.demo.filter;

import com.asiaInfo.demo.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @Description: 自定义过滤器
 * @Author: li.shuGuang
 * @dateTime: 2021/4/26
 */
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入自定义过滤器，获取到的请求参数username:{}",servletRequest.getParameter("username"));
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        //如果最后一级路径是（index，online或者login），则需要走登录方法，否则放行；
        //可以将所有请求路径在这里进行过滤
        if (httpServletRequest.getRequestURI().indexOf("/index") != -1 ||
                httpServletRequest.getRequestURI().indexOf("/online") != -1 ||
                httpServletRequest.getRequestURI().indexOf("/login") != -1
        ) {
            //
            httpServletRequest.getRequestDispatcher("/asiaInfo/login").forward(httpServletRequest, httpServletResponse);
        } else {
            throw new MyException("访问的资源不存在，请核实地址是否正确！");
            //下面的是直接放行
            //filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}