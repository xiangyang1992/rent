package com.keith.rent.core.config;

import com.keith.rent.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User:
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html", "/webjars/springfox-swagger-ui/**", "/v2/api-docs",
                        "/swagger-resource/**", "/login","/captcha.jpg","/register");
    }

    //静态文件的访问设置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    //视图的配置
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }
}
