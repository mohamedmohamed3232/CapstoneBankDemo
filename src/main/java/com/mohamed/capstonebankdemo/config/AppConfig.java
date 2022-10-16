package com.mohamed.capstonebankdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
@ComponentScan(basePackages = {"com.mohamed.capstonebankdemo"})
public class AppConfig extends WebMvcConfigurationSupport {
}
