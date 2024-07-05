package com.groupware.orca.filters;

import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // 로그인 체크를 위한 로직
        if (!path.equals("/orca/user/login") && !path.startsWith("/resources")) { // 로그인 페이지 및 정적 자원은 예외 처리
            UserVo loggedInUser = (UserVo) httpRequest.getSession().getAttribute("loginUserVo");
            System.out.println(loggedInUser);
            if (loggedInUser == null) {
                httpResponse.sendRedirect(contextPath + "/orca/user/login");
                return;
            }
        }

        chain.doFilter(request, response); // 다음 필터로 요청 전달
    }

    @Override
    public void destroy() {
        // 필터 종료시 처리할 작업 (필요시)
    }
}
