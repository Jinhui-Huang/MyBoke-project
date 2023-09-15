package com.itstudy.controller.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/img/**").
                addResourceLocations("classpath:/static/img/", "file:/home/huian/IdeaProjects/SpringBoot-Demo01/src/main/resources/static/img/");

        registry.addResourceHandler("/static/img/user_img/**").
                addResourceLocations("classpath:/static/img/user_img/", "file:/home/huian/IdeaProjects/SpringBoot-Demo01/src/main/resources/static/img/user_img/");

        registry.addResourceHandler("/static/js/**").
                addResourceLocations("classpath:/static/js/", "file:/home/huian/IdeaProjects/SpringBoot-Demo01/src/main/resources/static/js/");
        registry.addResourceHandler("/static/css/**").
                addResourceLocations("classpath:/static/css/", "file:/home/huian/IdeaProjects/SpringBoot-Demo01/src/main/resources/static/css/");
    }
}
