package com.groupware.orca.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // CSS와 JS 파일 경로를 제외
        if (requestURI.startsWith("/css/") || requestURI.startsWith("/js/") || requestURI.startsWith("/img/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 세션에서 로그인 정보 가져오기
        Object user = request.getSession().getAttribute("loginUserVo");

        // 로그인 정보가 없으면 로그인 페이지로 리다이렉트
        if (user == null && !requestURI.endsWith("login")) {
            response.sendRedirect(request.getContextPath() + "/orca/user/login");
            return;
        }

        // 다음 필터 또는 서블릿 호출
        filterChain.doFilter(request, response);
    }
}
