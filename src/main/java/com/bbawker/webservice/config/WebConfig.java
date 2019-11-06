package com.bbawker.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private final String uploadImagesPath;

    public WebConfig(@Value("${upload.path}") String uploadImagesPath) {
        this.uploadImagesPath = uploadImagesPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/static/image/**")
                .addResourceLocations("file:///" + uploadImagesPath +"/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

//        List<String> imageFolders = Arrays.asList("email");
//        for(String imageFolder : imageFolders) {
//            registry.addResourceHandler("/static/img/" +imageFolder +"/**")
//                    .addResourceLocations("file:///" + uploadImagesPath + imageFolder +"/")
//                    .setCachePeriod(3600)
//                    .resourceChain(true)
//                    .addResolver(new PathResourceResolver());
//        }
    }
}
