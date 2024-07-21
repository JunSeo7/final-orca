package com.groupware.orca.filters;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class DeptLoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 세션에서 로그인 정보 가져오기
        DepartmentVo loginDeptVo = (DepartmentVo) request.getSession().getAttribute("loginDeptVo");

        if (loginDeptVo == null) {
            response.sendRedirect(request.getContextPath() + "/orca/user/showDepartmentLogin");
            return;
        }

        if (requestURI.startsWith("/orca/humanResources/") && loginDeptVo.getDeptCode() != 2) {
            response.sendRedirect(request.getContextPath() + "/orca/home");
            return;
        } else if (requestURI.startsWith("/orca/managementSupport/") && loginDeptVo.getDeptCode() != 3) {
            response.sendRedirect(request.getContextPath() + "/orca/home");
            return;
        } else if (requestURI.startsWith("/orca/accountingDivision/") && loginDeptVo.getDeptCode() != 4) {
            response.sendRedirect(request.getContextPath() + "/orca/home");
            return;
        }

        // 다음 필터 또는 서블릿 호출
        filterChain.doFilter(request, response);
    }
}
