package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("userHand_Top/upload/**").addResourceLocations("file:E:\\IDEA_project\\mybilibili\\src\\main\\resources\\static\\userHand_Top\\upload\\");
    }
}
