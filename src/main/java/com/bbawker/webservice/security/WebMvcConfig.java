package com.bbawker.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 머스터치에선 바로 Csrf를 사용할 수 없기 때문에 추가
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
    @Autowired
    CsrfInterceptor csrfInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(csrfInterceptor);
    }
}
