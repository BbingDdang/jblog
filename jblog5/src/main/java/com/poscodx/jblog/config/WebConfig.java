package com.poscodx.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscodx.jblog.config.web.FileuploadConfig;
import com.poscodx.jblog.config.web.LocaleConfig;
import com.poscodx.jblog.config.web.MvcConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({ MvcConfig.class, LocaleConfig.class, FileuploadConfig.class })
@ComponentScan({ "com.poscodx.jblog.controller", "com.poscodx.jblog.exception" })
public class WebConfig implements WebMvcConfigurer {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/jblog/**").addResourceLocations("/jblog/");
//	}
	
}
