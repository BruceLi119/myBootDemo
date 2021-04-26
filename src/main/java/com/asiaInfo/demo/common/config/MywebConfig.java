package com.asiaInfo.demo.common.config;

import com.asiaInfo.demo.filter.MyFilter;
import com.asiaInfo.demo.inteceptor.MyInterceptor;
import com.asiaInfo.demo.listener.MyListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: spring三大器的配置类
 * @Author: li.shuGuang
 * @dateTime: 2021/4/26
 */
@Slf4j
@Configuration
public class MywebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/asd/**");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new MyFilter());
        //设置拦截模式（可配置多个模式，如："/asiaInfo/*"，"/audit/*"）
        frBean.addUrlPatterns("/asiaInfo/*");
        log.info("初始化自定义过滤器完成！");
        return frBean;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyListener());
        System.out.println("listener");
        return srb;
    }
}