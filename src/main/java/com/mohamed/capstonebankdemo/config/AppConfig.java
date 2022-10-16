package com.mohamed.capstonebankdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
This configuration is to tell the application where the resources are inside this is to ensure
the application works smoothly
 */
@Configuration
@ComponentScan(basePackages = {"com.mohamed.capstonebankdemo"})
public class AppConfig extends WebMvcConfigurationSupport {
    //Telling the application where to find the pages
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
        jspViewResolver.setPrefix("/WEB-INF/jsp/");
        jspViewResolver.setSuffix(".jsp");
        jspViewResolver.setViewClass(JstlView.class);
        return jspViewResolver;
    }
}
