package com.wangju.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by java_zong on 2017/7/5.
 */
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"com.wangju.controller","com.wangju.service"})
@MapperScan("com.wangju.dao.mapper")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver ben=new InternalResourceViewResolver();
        ben.setViewClass(JstlView.class);
        ben.setPrefix("/WEB-INF");
        ben.setSuffix(".jsp");
        return ben;
    }

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver common=new CommonsMultipartResolver();
        common.setMaxUploadSize(10*1024*1024);
        return common;
    }

}
