package com.groupware.orca.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.groupware.orca.filters.LoginFilter;
@Component
public class FilterConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 대해 필터 적용
        registrationBean.setOrder(1); // 필터 순서 설정 (낮을수록 먼저 실행)

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<DeptLoginFilter> deptLoginFilter() {
        FilterRegistrationBean<DeptLoginFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new DeptLoginFilter());
        registrationBean.addUrlPatterns("/orca/humanResources/*", "/orca/managementSupport/*", "/orca/accountingDivision/*");
        registrationBean.setOrder(2); // 필터 순서 설정 (낮을수록 먼저 실행)

        return registrationBean;
    }
}
