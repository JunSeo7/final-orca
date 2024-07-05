package com.groupware.orca.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registerLoginFilter(){

        LoggingFilter loginFilter = new LoggingFilter();
        FilterRegistrationBean lf = new FilterRegistrationBean(loginFilter);
        lf.addUrlPatterns("/*");
        lf.setOrder(1);
        return lf;
    }
}
